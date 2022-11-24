package web;

import datos.AltaDAO;
import dominio.Alta;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletInventario")
public class ServletInventario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarAlta(request, response);
                    break;
                case "eliminar":
                    this.eliminarAlta(request, response);
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
        List<Alta> altas = new AltaDAO().listar();
        System.out.println("altas = " + altas);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("altas", altas);
        sesion.setAttribute("totalAltas", altas.size());
        response.sendRedirect("altas.jsp");
    }
    
    
    private void editarAlta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCliente
        int idAlta = Integer.parseInt(request.getParameter("IDAlta"));
        Alta alta = new AltaDAO().encontrar(new Alta(idAlta));
        request.setAttribute("alta", alta);
        String jspEditar = "/WEB-INF/paginas/alta/editarAlta.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarAlta(request, response);
                    break;
                case "modificar":
                    this.modificarAlta(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarAlta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario agregarCliente
        int IDProducto = Integer.parseInt(request.getParameter("IDProducto"));
        int IDProveedor = Integer.parseInt(request.getParameter("IDProveedor"));
        String Fecha = request.getParameter("Fecha");
        int Cantidad = Integer.parseInt(request.getParameter("Cantidad"));

        //Creamos el objeto de cliente (modelo)
        Alta alta = new Alta(IDProducto, IDProveedor, Fecha, Cantidad);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new AltaDAO().insertar(alta);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarAlta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int IDAlta = Integer.parseInt(request.getParameter("IDAlta"));
        int IDProducto = Integer.parseInt(request.getParameter("IDProducto"));
        int IDProveedor = Integer.parseInt(request.getParameter("IDProveedor"));
        String Fecha = request.getParameter("Fecha");
        int Cantidad = Integer.parseInt(request.getParameter("Cantidad"));

        //Creamos el objeto de cliente (modelo)
        Alta alta = new Alta(IDAlta, IDProducto, IDProveedor, Fecha, Cantidad);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new AltaDAO().actualizar(alta);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarAlta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int IDAlta = Integer.parseInt(request.getParameter("IDAlta"));
     

        //Creamos el objeto de cliente (modelo)
        Alta alta = new Alta(IDAlta);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new AltaDAO().eliminar(alta);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
