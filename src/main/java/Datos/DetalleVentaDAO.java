package datos;

import dominio.DetalleVenta;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalleVentaDAO {

    private static final String SQL_SELECT = "SELECT IDDetalleVenta, IDVenta, IDProducto, Cantidad, Total "
            + " FROM detalle_venta";

    private static final String SQL_SELECT_BY_ID = "SELECT IDDetalleVenta, IDVenta, IDProducto, Cantidad, Total "
            + " FROM detalle_venta WHERE IDDetalleVenta = ?";

    private static final String SQL_INSERT = "INSERT INTO detalle_venta(IDVenta, IDProducto, Cantidad, Total) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE detalle_venta "
            + " SET IDVenta=?, IDProducto=?, Cantidad=?, Total=? WHERE IDDetalleVenta=?";

    private static final String SQL_DELETE = "DELETE FROM detalle_venta WHERE IDDetalleVenta = ?";
    
    private static final String SQL_VENTA_PRODUCTO = "UPDATE producto VALUE SET Existencias = ? WHERE IDProducto = ?";
    
    private static final String SQL_LISTAR_POR_IDVENTA = "SELECT IDDetalleVenta, IDVenta, IDProducto, Cantidad, Total "
            + " FROM detalle_venta WHERE IDVenta = ?";
    
    

    public List<DetalleVenta> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DetalleVenta detalleVenta = null;
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int IDDetalleVenta = rs.getInt("IDDetalleVenta");
                int IDVenta = rs.getInt("IDVenta");
                int IDProducto = rs.getInt("IDProducto");
                int Cantidad = rs.getInt("Cantidad");
                double Total = rs.getDouble("Total");

                detalleVenta = new DetalleVenta(IDDetalleVenta, IDVenta, IDProducto, Cantidad, Total);
                detalleVentas.add(detalleVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return detalleVentas;
    }
    
    public List<DetalleVenta> listarPorIdVenta(DetalleVenta detalleVenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            conn = ConexionBD.getConnection();
            stmt.setInt(1, detalleVenta.getIdDetalleVenta());
            stmt = conn.prepareStatement(SQL_LISTAR_POR_IDVENTA);
            rs = stmt.executeQuery();
            
            
            while (rs.next()) {
                int IDDetalleVenta = rs.getInt("IDDetalleVenta");
                int IDVenta = rs.getInt("IDVenta");
                int IDProducto = rs.getInt("IDProducto");
                int Cantidad = rs.getInt("Cantidad");
                double Total = rs.getDouble("Total");

                detalleVenta = new DetalleVenta(IDDetalleVenta, IDVenta, IDProducto, Cantidad, Total);
                detalleVentas.add(detalleVenta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return detalleVentas;
    }

    public DetalleVenta encontrar(DetalleVenta detalleVenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, detalleVenta.getIdDetalleVenta());
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto

            while (rs.next()) {

                int IDVenta = rs.getInt("IDVenta");
                int IDProducto = rs.getInt("IDProducto");
                int Cantidad = rs.getInt("Cantidad");
                double Total = rs.getDouble("Total");

                detalleVenta.setIdVenta(IDVenta);
                detalleVenta.setIdProducto(IDProducto);
                detalleVenta.setCantidad(Cantidad);
                detalleVenta.setTotal(Total);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return detalleVenta;
    }

    public int insertar(DetalleVenta detalleVenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setInt(1, detalleVenta.getIdVenta());
            stmt.setInt(2, detalleVenta.getIdProducto());
            stmt.setInt(3, detalleVenta.getCantidad());
            stmt.setDouble(4, detalleVenta.getTotal());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int actualizar(DetalleVenta detalleVenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, detalleVenta.getIdVenta());
            stmt.setInt(2, detalleVenta.getIdProducto());
            stmt.setInt(4, detalleVenta.getCantidad());
            stmt.setDouble(5, detalleVenta.getTotal());
            stmt.setInt(6, detalleVenta.getIdDetalleVenta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int eliminar(DetalleVenta detalleVenta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, detalleVenta.getIdDetalleVenta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DetalleVentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }
    
    public int modificarProducto(int idProducto, int nuevaExistencia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_VENTA_PRODUCTO);
            stmt.setInt(1, nuevaExistencia);
            stmt.setInt(2, idProducto);

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

}
