package datos;

import dominio.Alta;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AltaDAO {

    private static final String SQL_SELECT = "SELECT IDAlta, IDProducto, IDProveedor, Fecha, Cantidad "
            + " FROM alta";

    private static final String SQL_SELECT_BY_ID = "SELECT IDAlta, IDProducto, IDProveedor, Fecha, Cantidad "
            + " FROM alta WHERE IDAlta = ?";

    private static final String SQL_INSERT = "INSERT INTO alta(IDProducto, IDProveedor, Fecha, Cantidad) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE alta "
            + " SET IDProducto=?, IDProveedor=?, Fecha=?, Cantidad=? WHERE IDAlta=?";

    private static final String SQL_DELETE = "DELETE FROM alta WHERE IDAlta = ?";
    
    private static final String SQL_ALTA_PRODUCTO = "UPDATE FROM producto VALUE SET Existencias = ? WHERE IDProducto = ?";

    public List<Alta> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alta alta = null;
        List<Alta> altas = new ArrayList<>();
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int IDAlta = rs.getInt("IDAlta");
                int IDProducto = rs.getInt("IDProducto");
                int IDProveedor = rs.getInt("IDProveedor");
                String Fecha = rs.getString("Fecha");
                int Cantidad = rs.getInt("Cantidad");

                alta = new Alta(IDAlta, IDProducto, IDProveedor, Fecha, Cantidad);
                altas.add(alta);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return altas;
    }

    public Alta encontrar(Alta alta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, alta.getIdAlta());
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto

            while (rs.next()) {

                int IDProducto = rs.getInt("IDProducto");
                int IDProveedor = rs.getInt("IDProveedor");
                String Fecha = rs.getString("Fecha");
                int Cantidad = rs.getInt("Cantidad");

                alta.setIdProducto(IDProducto);
                alta.setIdProveedor(IDProveedor);
                alta.setFecha(Fecha);
                alta.setCantidad(Cantidad);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return alta;
    }

    public int insertar(Alta alta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, alta.getIdProducto());
            stmt.setInt(2, alta.getIdProveedor());
            stmt.setString(3, alta.getFecha());
            stmt.setInt(4, alta.getCantidad());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int actualizar(Alta alta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, alta.getIdProducto());
            stmt.setInt(2, alta.getIdProveedor());
            stmt.setString(3, alta.getFecha());
            stmt.setInt(4, alta.getCantidad());
            stmt.setInt(5, alta.getIdAlta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int eliminar(Alta alta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, alta.getIdAlta());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }
    
    public int aumentarProducto(int idProducto, int nuevaExistencia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_ALTA_PRODUCTO);
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
