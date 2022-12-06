package datos;

import dominio.Venta;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentaDAO {

    private static final String SQL_SELECT = "SELECT IDVenta, IDVendedor, Fecha, CostoTotal "
            + " FROM venta";

    private static final String SQL_SELECT_BY_ID = "SELECT IDVenta, IDVendedor, Fecha, CostoTotal "
            + " FROM venta WHERE IDVenta = ?";

    private static final String SQL_INSERT = "INSERT INTO venta(IDVendedor, Fecha, CostoTotal) "
            + " VALUES(?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE venta "
            + " SET IDVendedor=?, Fecha=?, CostoTotal=? WHERE IDVenta=?";

    private static final String SQL_DELETE = "DELETE FROM venta WHERE IDVenta = ?";
    
    private static final String SQL_COSTO = "UPDATE venta VALUE SET CostoTotal = ? WHERE IDVenta = ?";
    
    private static final String SQL_SELECT_LAST = "SELECT * FROM venta ORDER BY IDVenta DESC LIMIT 1;";

    public List<Venta> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venta venta = null;
        List<Venta> ventas = new ArrayList<>();
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int IDVenta = rs.getInt("IDVenta");
                int IDVendedor = rs.getInt("IDVendedor");
                String Fecha = rs.getString("Fecha");
                double CostoTotal = rs.getDouble("CostoTotal");

                venta = new Venta(IDVenta, IDVendedor, Fecha, CostoTotal);
                ventas.add(venta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return ventas;
    }

    public Venta encontrar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, venta.getIdVenta());
            rs = stmt.executeQuery();
            //rs.absolute(1);

            while (rs.next()) {

                int IDVendedor = rs.getInt("IDVendedor");
                String Fecha = rs.getString("Fecha");
                double CostoTotal = rs.getDouble("CostoTotal");

                venta.setIdVendedor(IDVendedor);
                venta.setFecha(Fecha);
                venta.setCostoTotal(CostoTotal);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return venta;
    }
    
    public Venta encontrarUltimo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Venta venta = new Venta();
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_LAST);
            rs = stmt.executeQuery();
            //rs.absolute(1);

            while (rs.next()) {
                int IDVenta = rs.getInt("IDVenta");
                int IDVendedor = rs.getInt("IDVendedor");
                String Fecha = rs.getString("Fecha");
                double CostoTotal = rs.getDouble("CostoTotal");

                venta.setIdVenta(IDVenta);
                venta.setIdVendedor(IDVendedor);
                venta.setFecha(Fecha);
                venta.setCostoTotal(CostoTotal);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return venta;
    }

    public int insertar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setInt(1, venta.getIdVendedor());
            stmt.setString(2, venta.getFecha());
            stmt.setDouble(3, venta.getCostoTotal());

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

    public int actualizar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, venta.getIdVendedor());
            stmt.setString(2, venta.getFecha());
            stmt.setDouble(3, venta.getCostoTotal());
            stmt.setInt(4, venta.getIdVenta());

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

    public int eliminar(Venta venta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, venta.getIdVenta());

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
    
    public int actualizarCosto(int idVenta, double nuevoCosto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_COSTO);
            stmt.setDouble(1, nuevoCosto);
            stmt.setInt(2, idVenta);

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
