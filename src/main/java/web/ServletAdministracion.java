package web;

import datos.*;
import dominio.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletAdministracion")
public class ServletAdministracion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "registrarVendedorForm":
                    this.registrarVendedorForm(request, response);
                    break;
                case "editarVendedor":
                    this.editarVendedor(request, response);
                    break;
                case "eliminarVendedor":
                    this.eliminarVendedor(request, response);
                    break;
                case "registrarProveedorForm":
                    this.registrarProveedorForm(request, response);
                    break;
                case "editarProveedor":
                    this.editarProveedor(request, response);
                    break;
                case "eliminarProveedor":
                    this.eliminarProveedor(request, response);
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
        
        //request.getRequestDispatcher("administracion.jsp").forward(request, response);
        response.sendRedirect("administracion.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "registrarVendedorForm":
                    this.registrarVendedorForm(request, response);
                    break;
                case "editarVendedor":
                    this.editarVendedor(request, response);
                    break;
                case "eliminarVendedor":
                    this.eliminarVendedor(request, response);
                    break;
                case "registrarProveedorForm":
                    this.registrarProveedorForm(request, response);
                    break;
                case "editarProveedor":
                    this.editarProveedor(request, response);
                    break;
                case "eliminarProveedor":
                    this.eliminarProveedor(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void registrarVendedorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/vendedores/formVendedores.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }
    
    private void registrarProveedorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/proveedores/formProveedores.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }
    
    private void editarVendedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idProducto
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new ProductoDAO().encontrar(new Producto(idProducto));
        request.setAttribute("producto", producto);
        String jspEditar = "/WEB-INF/paginas/productos/formProductos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    private void editarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idProducto
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new ProductoDAO().encontrar(new Producto(idProducto));
        request.setAttribute("producto", producto);
        String jspEditar = "/WEB-INF/paginas/productos/formProductos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    private void eliminarVendedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idVendedor = Integer.parseInt(request.getParameter("idVendedor"));
        Vendedor vendedor = new Vendedor(idVendedor);

        int b = new VendedorDAO().eliminar(vendedor);
        
        

        this.accionDefault(request, response);
    }
    
    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        Proveedor proveedor = new Proveedor(idProveedor);

        int b = new ProveedorDAO().eliminar(proveedor);
        
        

        this.accionDefault(request, response);
    }
}
