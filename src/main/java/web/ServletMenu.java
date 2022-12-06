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

        HttpSession sesion = request.getSession();

        //Agregar nueva Venta
        Vendedor usuario = (Vendedor) sesion.getAttribute("usuario");

        //Obtenemos la fecha actual
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = dateFormat.format(new Date());
//
//        Venta venta = new Venta(usuario.getIdVendedor(), date, 0.0);
//        int a = new VentaDAO().insertar(venta);

        List<Vendedor> vendedores = new VendedorDAO().listar();
        List<Alta> altas = new AltaDAO().listar();
        List<Producto> productos = new ProductoDAO().listar();
        List<Proveedor> proveedores = new ProveedorDAO().listar();
        List<Venta> ventas = new VentaDAO().listar();
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        detalleVentas = new DetalleVentaDAO().listar();
        Venta venta2 = new VentaDAO().encontrarUltimo();
        //venta = new VentaDAO().encontrarUltimo();

        sesion.setAttribute("vendedores", vendedores);
        sesion.setAttribute("altas", altas);
        sesion.setAttribute("productos", productos);
        sesion.setAttribute("proveedores", proveedores);
        sesion.setAttribute("ventas", ventas);
        sesion.setAttribute("detalleVentas", detalleVentas);
        sesion.setAttribute("ventaActual", venta2);

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

    private void agregarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Vendedor usuario = (Vendedor) sesion.getAttribute("usuario");

        //Obtenemos la fecha actual
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    private void eliminarProductoDeVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idDetalleVenta = Integer.parseInt(request.getParameter("idDetalleVenta"));

        DetalleVenta detalleVenta = new DetalleVentaDAO().encontrar(new DetalleVenta(idDetalleVenta));
        int cantidad = detalleVenta.getCantidad();

        Venta venta = new VentaDAO().encontrar(new Venta(detalleVenta.getIdVenta()));
        double subtotal = detalleVenta.getTotal();
        int idVenta = venta.getIdVenta();

        int nuevaCantidad = this.aumentarProducto(detalleVenta.getIdProducto(), cantidad);
        double total = this.ReducirCosto(idVenta, subtotal);

        int a = new DetalleVentaDAO().modificarProducto(detalleVenta.getIdProducto(), nuevaCantidad);
        int b = new DetalleVentaDAO().eliminar(detalleVenta);
        int c = new VentaDAO().actualizarCosto(idVenta, total);

        this.accionDefault(request, response);
    }

    private void agregarProductoDeVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        Venta venta = (Venta) sesion.getAttribute("ventaActual");
        int idVenta = venta.getIdVenta();
        int idProducto = Integer.parseInt(request.getParameter("idProductos"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (this.validarCantidades(idProducto, cantidad)) {
            int nuevaCantidad = this.reducirProducto(idProducto, cantidad);
            double subtotal = this.calculoSubtotal(idProducto, cantidad);
            double total = this.actualizarCosto(idVenta, subtotal);

            DetalleVenta detalleVenta = new DetalleVenta(idVenta, idProducto, cantidad, subtotal);

            int a = new DetalleVentaDAO().modificarProducto(idProducto, nuevaCantidad);
            int b = new DetalleVentaDAO().insertar(detalleVenta);
            int c = new VentaDAO().actualizarCosto(idVenta, total);
            this.accionDefault(request, response);
        } else {
            this.accionDefault(request, response);
        }

    }

    private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();

        Venta venta = (Venta) sesion.getAttribute("ventaActual");
        int idVenta = venta.getIdVenta();
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        if (this.validarCantidades(idProducto, cantidad)) {
            int nuevaCantidad = this.reducirProducto(idProducto, cantidad);
            int a = new DetalleVentaDAO().modificarProducto(idProducto, nuevaCantidad);
            double subtotal = this.calculoSubtotal(idProducto, cantidad);
            DetalleVenta detalleVenta = new DetalleVenta(idVenta, idProducto, cantidad, subtotal);
            int b = new DetalleVentaDAO().insertar(detalleVenta);
        } else {
            this.accionDefault(request, response);
        }

        this.accionDefault(request, response);
    }

    private double calculoSubtotal(int idProducto, int cantidad) {
        Producto product = new ProductoDAO().encontrar(new Producto(idProducto));
        return product.getPrecioVenta() * cantidad;
    }

    private boolean validarCantidades(int idProducto, int cantidad) {
        Producto product = new ProductoDAO().encontrar(new Producto(idProducto));
        if (product.getExistencias() >= cantidad) {
            return true;
        }

        return false;
    }

    private int reducirProducto(int idProducto, int cantidad) {
        Producto product = new ProductoDAO().encontrar(new Producto(idProducto));
        return product.getExistencias() - cantidad;
    }

    private double actualizarCosto(int idVenta, double subtotal) {
        Venta venta = new VentaDAO().encontrar(new Venta(idVenta));
        return venta.getCostoTotal() + subtotal;
    }

    private int aumentarProducto(int idProducto, int cantidad) {
        Producto product = new ProductoDAO().encontrar(new Producto(idProducto));
        return product.getExistencias() + cantidad;
    }

    private double ReducirCosto(int idVenta, double subtotal) {
        Venta venta = new VentaDAO().encontrar(new Venta(idVenta));
        return venta.getCostoTotal() - subtotal;
    }

}
