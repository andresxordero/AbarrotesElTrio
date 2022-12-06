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
                    this.registrarVenta(request, response);
                    break;
                case "registrarProductoForm":
                    this.registrarProductoForm(request, response);
                    break;
                case "editarProducto":
                    this.editarProducto(request, response);
                    break;
                case "eliminarProducto":
                    this.eliminarProducto(request, response);
                    break;
                case "registrarAltaForm":
                    this.registrarAltaForm(request, response);
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
                    this.registrarVenta(request, response);
                    break;
                case "registrarProductoForm":
                    this.registrarProductoForm(request, response);
                    break;
                case "editarProducto":
                    this.editarProducto(request, response);
                    break;
                case "eliminarProducto":
                    this.eliminarProducto(request, response);
                    break;
                case "registrarAltaForm":
                    this.registrarAltaForm(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }
    
    private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
    
    private void registrarProductoForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/productos/formProductos.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }
    
    private void editarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idProducto
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        Producto producto = new ProductoDAO().encontrar(new Producto(idProducto));
        request.setAttribute("producto", producto);
        String jspEditar = "/WEB-INF/paginas/productos/formProductos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idProducto = Integer.parseInt(request.getParameter("idPrducto"));
        //Creamos el objeto de cliente (modelo)
        Producto producto = new Producto(idProducto);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new ProductoDAO().eliminar(producto);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void registrarAltaForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/altas/formAltas.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }   

}
