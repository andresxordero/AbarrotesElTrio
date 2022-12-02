package web;

import datos.DetalleVentaDAO;
import datos.VentaDAO;
import dominio.DetalleVenta;
import dominio.Producto;
import dominio.Venta;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletMenu")
public class ServletMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "eliminarProductoDeVenta":
                    this.eliminarProductoDeVenta(request, response);
                    break;
                case "agregarProductoDeVenta":
                    this.agregarProductoDeVenta(request, response);
                    break;
                case "registrarVenta":
                    this.registrarVenta(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
        //accionDefault(request,response);
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("menu.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "eliminarProductoDeVenta":
                    this.eliminarProductoDeVenta(request, response);
                    break;
                case "agregarProductoDeVenta":
                    this.agregarProductoDeVenta(request, response);
                    break;
                case "registrarVenta":
                    this.registrarVenta(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }
    
    private void eliminarProductoDeVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idDetalleVenta = Integer.parseInt(request.getParameter("idDetalleVenta"));
     

        //Creamos el objeto de cliente (modelo)
        DetalleVenta detalleVenta = new DetalleVenta(idDetalleVenta);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new DetalleVentaDAO().eliminar(detalleVenta);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
    private void agregarProductoDeVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario de Venta
        String idProducto = request.getParameter("idProducto");
        String cantidad = request.getParameter("cantidad");
           
        //Creamos el objeto de cliente (modelo)
        //DetalleVenta detalleVenta = new DetalleVenta(idProducto, cantidad, );

        //Insertamos el nuevo objeto en la base de datos
        //int registrosModificados = new DetalleVentaDAO().insertar(detalleVenta);
        //System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
    private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {           
        //Creamos el objeto de cliente (modelo)
        //DetalleVenta detalleVenta = new DetalleVenta(idProducto, cantidad, );

        //Insertamos el nuevo objeto en la base de datos
        //int registrosModificados = new DetalleVentaDAO().insertar(detalleVenta);
        //System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
