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
                case "registrarVendedor":
                    this.registrarVendedor(request, response);
                    break;
                case "editarVendedor":
                    this.editarVendedor(request, response);
                    break;
                case "modificarVendedor":
                    this.modificarVendedor(request, response);
                    break;
                case "eliminarVendedor":
                    this.eliminarVendedor(request, response);
                    break;
                case "registrarProveedorForm":
                    this.registrarProveedorForm(request, response);
                    break;
                case "registrarProveedor":
                    this.registrarProveedor(request, response);
                    break;
                case "editarProveedor":
                    this.editarProveedor(request, response);
                    break;
                case "modificarProveedor":
                    this.modificarProveedor(request, response);
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
                case "registrarVendedor":
                    this.registrarVendedor(request, response);
                    break;
                case "editarVendedor":
                    this.editarVendedor(request, response);
                    break;
                case "modificarVendedor":
                    this.modificarVendedor(request, response);
                    break;
                case "eliminarVendedor":
                    this.eliminarVendedor(request, response);
                    break;
                case "registrarProveedorForm":
                    this.registrarProveedorForm(request, response);
                    break;
                case "registrarProveedor":
                    this.registrarProveedor(request, response);
                    break;
                case "editarProveedor":
                    this.editarProveedor(request, response);
                    break;
                case "modificarProveedor":
                    this.modificarProveedor(request, response);
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

    private void registrarVendedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        //Creamos el objeto de cliente (modelo)
        Vendedor vendedor = new Vendedor(nombre, apellido, telefono, usuario, password, rol);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new VendedorDAO().insertar(vendedor);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void registrarProveedorForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jspRegistrar = "/WEB-INF/paginas/proveedores/formProveedores.jsp";
        request.getRequestDispatcher(jspRegistrar).forward(request, response);
    }

    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        //Creamos el objeto de cliente (modelo)
        Proveedor proveedor = new Proveedor(nombre, telefono, correo);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new ProveedorDAO().insertar(proveedor);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void editarVendedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idProducto
        int idVendedor = Integer.parseInt(request.getParameter("idVendedor"));
        Vendedor vendedor = new VendedorDAO().encontrar(new Vendedor(idVendedor));
        request.setAttribute("vendedor", vendedor);
        String jspEditar = "/WEB-INF/paginas/vendedores/formVendedoresUpdate.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void modificarVendedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idVendedor = Integer.parseInt(request.getParameter("idVendedor"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        //Creamos el objeto de cliente (modelo)
        Vendedor vendedor = new Vendedor(idVendedor, nombre, apellido, telefono, usuario, password, rol);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new VendedorDAO().actualizar(vendedor);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void editarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idProducto
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        Proveedor proveedor = new ProveedorDAO().encontrar(new Proveedor(idProveedor));
        request.setAttribute("proveedor", proveedor);
        String jspEditar = "/WEB-INF/paginas/proveedores/formProveedoresUpdate.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    private void modificarProveedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos los valores del formulario editarCliente
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        //Creamos el objeto de cliente (modelo)
        Proveedor proveedor = new Proveedor(idProveedor, nombre, telefono, correo);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new ProveedorDAO().actualizar(proveedor);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
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
