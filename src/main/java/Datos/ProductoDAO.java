package datos;

import dominio.Producto ;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {

    private static final String SQL_SELECT = "SELECT IDProducto, Nombre, Descripcion, Existencias, PrecioVenta, PrecioCompra "
            + " FROM producto";

    private static final String SQL_SELECT_BY_ID = "SELECT IDProducto, Nombre, Descripcion, Existencias, PrecioVenta, PrecioCompra "
            + " FROM producto WHERE IDProducto = ?";

    private static final String SQL_INSERT = "INSERT INTO producto(Nombre, Descripcion, Existencias, PrecioVenta, PrecioCompra) "
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE producto "
            + " SET Nombre=?, Descripcion=?, Existencias=?, PrecioVenta=?, PrecioCompra=? WHERE IDProducto=?";

    private static final String SQL_DELETE = "DELETE FROM producto WHERE IDProducto = ?";

    public List<Producto> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Producto producto = null;
        List<Producto> productos = new ArrayList<>();
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int IDProducto = rs.getInt("IDProducto");
                String Nombre = rs.getString("Nombre");
                String Descripcion = rs.getString("Descripcion");
                int Existencias = rs.getInt("Existencias");
                double PrecioVenta = rs.getDouble("PrecioVenta");
                double PrecioCompra = rs.getDouble("PrecioCompra");

                producto = new Producto(IDProducto, Nombre, Descripcion, Existencias, PrecioVenta, PrecioCompra);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return productos;
    }

    public Producto encontrar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, producto.getIdProducto());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

                String Nombre = rs.getString("Nombre");
                String Descripcion = rs.getString("Descripcion");
                int Existencias = rs.getInt("Existencias");
                double PrecioVenta = rs.getDouble("PrecioVenta");
                double PrecioCompra = rs.getDouble("PrecioCompra");

            producto.setNombre(Nombre);
            producto.setDescripcion(Descripcion);
            producto.setExistencias(Existencias);
            producto.setPrecioVenta(PrecioVenta);
            producto.setPrecioCompra(PrecioCompra);
                    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return producto;
    }

    public int insertar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
                
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getExistencias());
            stmt.setDouble(4, producto.getPrecioVenta());
            stmt.setDouble(5, producto.getPrecioCompra());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int actualizar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setInt(3, producto.getExistencias());
            stmt.setDouble(4, producto.getPrecioVenta());
            stmt.setDouble(5, producto.getPrecioCompra());
            stmt.setInt(6, producto.getIdProducto());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int eliminar(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getIdProducto());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

}
