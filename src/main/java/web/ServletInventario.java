package web;

import datos.*;
import dominio.*;
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
                case "registrarVentaForm":
                    this.registrarVentaForm(request, response);
                    break;
                case "registrarProductoForm":
                    this.registrarProductoForm(request, response);
                    break;
                case "registrarProducto":
                    this.registrarProducto(request, response);
                    break;
                case "editarProducto":
                    this.editarProducto(request, response);
                    break;
                case "modificarProducto":
                    this.modificarProducto(request, response);
                    break;
                case "eliminarProducto":
                    this.eliminarProducto(request, response);
                    break;
                case "registrarAltaForm":
                    this.registrarAltaForm(request, response);
                    break;
                case "registrarAlta":
                    this.registrarAlta(request, response);
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

        //request.getRequestDispatcher("inventario.jsp").forward(request, response);
        response.sendRedirect("inventario.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "registrarVentaForm":
                    this.registrarVentaForm(request, response);
                    break;
                case "registrarProductoForm":
                    this.registrarProductoForm(request, response);
                    break;
                case "registrarProducto":
                    this.registrarProducto(request, response);
                    break;
                case "editarProducto":
                    this.editarProducto(request, response);
                    break;
                case "modificarProducto":
                    this.modificarProducto(request, response);
                    break;               
                case "eliminarProducto":
                    this.eliminarProducto(request, response);
                    break;
                case "registrarAltaForm":
                    this.registrarAltaForm(request, response);
                    break;
                case "registrarAlta":
                    this.registrarAlta(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void registrarVentaForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }

    private void registrarProductoForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/productos/formProductos.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int existencias = 0;
        Double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        Double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));

        //Creamos el objeto de cliente (modelo)
        Producto producto = new Producto(nombre, descripcion, existencias, precioVenta, precioCompra);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new ProductoDAO().insertar(producto);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void editarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idProducto
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new ProductoDAO().encontrar(new Producto(idProducto));
        request.setAttribute("producto", producto);
        String jspEditar = "/WEB-INF/paginas/productos/formProductosUpdate.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    private void modificarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int existencias = Integer.parseInt(request.getParameter("existencias"));
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        double precioCompra = Double.parseDouble(request.getParameter("precioCompra"));

        //Creamos el objeto de cliente (modelo)
        Producto producto = new Producto(idProducto, nombre, descripcion, existencias, precioVenta, precioCompra);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new ProductoDAO().actualizar(producto);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new Producto(idProducto);

        int b = new ProductoDAO().eliminar(producto);
        this.accionDefault(request, response);
    }

    private void registrarAltaForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/altas/formAltas.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }
    
    private void registrarAlta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProductos = Integer.parseInt(request.getParameter("idProductos"));
        int idProveedores = Integer.parseInt(request.getParameter("idProveedores"));
        String fecha = request.getParameter("fecha");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = new ProductoDAO().encontrar(new Producto(idProductos));

        Alta alta = new AltaDAO().encontrar(new Alta(producto.getIdProducto()));
        int idAlta = alta.getIdAlta();

        int nuevaCantidad = this.aumentarProducto(producto.getIdProducto(), cantidad);

        int a = new ProductoDAO().modificarProducto(producto.getIdProducto(), nuevaCantidad);

        //Creamos el objeto de cliente (modelo)
        Alta altaNueva = new Alta(idProductos, idProveedores, fecha, cantidad);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new AltaDAO().insertar(altaNueva);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
    private boolean validarCantidades(int idProducto, int cantidad) {
        Producto product = new ProductoDAO().encontrar(new Producto(idProducto));
        if (product.getExistencias() >= cantidad) {
            return true;
        }

        return false;
    }
    
    private int aumentarProducto(int idProducto, int cantidad) {
        Producto product = new ProductoDAO().encontrar(new Producto(idProducto));
        return product.getExistencias() + cantidad;
    }

}
