package web;

import datos.*;
import dominio.*;
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

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
        
        List<Vendedor> vendedores = new VendedorDAO().listar();
        List<Alta> altas = new AltaDAO().listar();
        List<Producto> productos = new ProductoDAO().listar();
        List<Proveedor> proveedores = new ProveedorDAO().listar();
        List<Venta> ventas = new VentaDAO().listar();
        List<DetalleVenta> detalleVentas = new DetalleVentaDAO().listar();
        
        sesion.setAttribute("vendedores", vendedores);
        sesion.setAttribute("altas", altas);
        sesion.setAttribute("productos", productos);
        sesion.setAttribute("proveedores", proveedores);
        sesion.setAttribute("ventas", ventas);
        sesion.setAttribute("detalleVentas", detalleVentas);
        
        response.sendRedirect("inicio.jsp");
        //request.getRequestDispatcher("inicio.jsp").forward(request, response);
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
        List<Venta> ventas = new VentaDAO().listar();
        List<DetalleVenta> detalleVentas = new DetalleVentaDAO().listar();
        VendedorDAO vendedorDAO = new VendedorDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        HttpSession sesion = request.getSession();
        sesion.setAttribute("ventas", ventas);
        sesion.setAttribute("detalleVentas", detalleVentas);
        
        vendedores = vendedorDAO.listar();
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getUsuario().equals(username) && vendedor.getPassword().equals(password)) {
                existe = true;
                sesion.setAttribute("usuario", vendedor);
                break;
            }
        }
        if (existe) {
            //usuario.setUsuario(username);
            //usuario.setPassword(password);
            //sesion.setAttribute("usuario", usuario);
            response.sendRedirect("menu.jsp");
            //request.getRequestDispatcher("/menu.jsp").forward(request, response);

        } else {
            //request.getRequestDispatcher("/inicio.jsp").forward(request, response);
            response.sendRedirect("inicio.jsp");
        }

    }

}
