package web;

import datos.VendedorDAO;
import dominio.Vendedor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletInicio")
public class ServletInicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        accionDefault(request, response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        response.sendRedirect("inicio.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "login":
                    this.checarCredenciales(request, response);
                    break;
                case "modificar":
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void checarCredenciales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario login
        boolean existe = false;
        List<Vendedor> vendedores = new ArrayList<>();
        VendedorDAO vendedorDAO = new VendedorDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Vendedor usuario = new Vendedor();
        
        HttpSession sesion = request.getSession();
        
        vendedores = vendedorDAO.listar();
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getUsuario().equals(username) && vendedor.getPassword().equals(password)) {
                existe = true;
                break;
            }
        }
        if (existe) {
            usuario.setUsuario(username);
            usuario.setPassword(password);
            sesion.setAttribute("usuario", usuario);
            response.sendRedirect("menu.jsp");

        } else {
            response.sendRedirect("inicio.jsp");
        }

    }

}
