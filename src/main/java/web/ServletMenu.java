package web;

import datos.*;
import dominio.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                case "eliminarVenta":
                    this.eliminarVenta(request, response);
                    break;
                case "agregarVenta":
                    this.agregarVenta(request, response);
                    break;
                case "eliminarProductoDeVenta":
                    //this.eliminarProductoDeVenta(request, response);
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

        //request.getRequestDispatcher("menu.jsp").forward(request,response);
        response.sendRedirect("menu.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "eliminarVenta":
                    this.eliminarVenta(request, response);
                    break;
                case "agregarVenta":
                    this.agregarVenta(request, response);
                    break;
                case "eliminarProductoDeVenta":
                    //this.eliminarProductoDeVenta(request, response);
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

    private void agregarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Vendedor usuario = (Vendedor) sesion.getAttribute("usuario");

        //Obtenemos la fecha actual
        DateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String date = dateFormat.format(new Date());

        Venta venta = new Venta(usuario.getIdVendedor(), date, 0.0);
        int a = new VentaDAO().insertar(venta);

        this.accionDefault(request, response);
    }
    
    private void eliminarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idVenta = Integer.parseInt(request.getParameter("idVenta"));
        Venta venta = new Venta(idVenta);

        int b = new VentaDAO().eliminar(venta);
        
        

        this.accionDefault(request, response);
    }

    private void agregarProductoDeVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        List<Producto> productos = (List<Producto>) sesion.getAttribute("productos");

        int idVenta = Integer.parseInt(request.getParameter("idVentas"));
        int idProducto = Integer.parseInt(request.getParameter("idProductos"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (this.validarCantidades(productos, idProducto, cantidad)) {
            int nuevaCantidad = this.reducirProducto(productos, idProducto, cantidad);
            double subtotal = this.calculoSubtotal(productos, idProducto, cantidad);
            DetalleVenta detalleVenta = new DetalleVenta(idVenta, idProducto, cantidad, subtotal);
            
            int a = new DetalleVentaDAO().reducirProducto(idProducto, nuevaCantidad);
            int b = new DetalleVentaDAO().insertar(detalleVenta);
            //int c = new VentaDAO().actualizarCosto(idVenta, subtotal);
        } else {
            PrintWriter out = response.getWriter();
        out.print("<script>"
                + "alert('alerta');"
                + "</script>");
        }

        this.accionDefault(request, response);
    }


    private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        List<Producto> productos = (List<Producto>) sesion.getAttribute("productos");

        int idVenta = Integer.parseInt(request.getParameter("idVenta"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (this.validarCantidades(productos, idProducto, cantidad)) {
            int nuevaCantidad = this.reducirProducto(productos, idProducto, cantidad);
            int a = new DetalleVentaDAO().reducirProducto(idProducto, nuevaCantidad);
            double subtotal = this.calculoSubtotal(productos, idProducto, cantidad);
            DetalleVenta detalleVenta = new DetalleVenta(idVenta, idProducto, cantidad, 0.0);
            int b = new DetalleVentaDAO().insertar(detalleVenta);
        } else {
            //Mostrar error
        }

        this.accionDefault(request, response);
    }

    private double calculoSubtotal(List<Producto> productos, int idProducto, int cantidad) {
        double total = 0;
        for (Producto producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                total = cantidad * producto.getPrecioVenta();
            }
        }
        return total;
    }

    private boolean validarCantidades(List<Producto> productos, int idProducto, int cantidad) {
        Producto product = new Producto();
        for (Producto producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                product = new ProductoDAO().encontrar(producto);
                if (product.getExistencias() >= cantidad) {
                    return true;
                }
            }
        }
        return false;
    }

    private int reducirProducto(List<Producto> productos, int idProducto, int cantidad) {
        int nuevaCantidad = 0;
        Producto product = new Producto();
        for (Producto producto : productos) {
            if (producto.getIdProducto() == idProducto) {
                product = new ProductoDAO().encontrar(producto);
                return product.getExistencias() - cantidad;
            }
        }
        return nuevaCantidad;
    }

}
