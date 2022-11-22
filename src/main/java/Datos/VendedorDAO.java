package datos;

import dominio.Vendedor;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendedorDAO {

    private static final String SQL_SELECT = "SELECT IDVendedor, Nombre, Apellido, Telefono, Usuario, Password "
            + " FROM vendedor";

    private static final String SQL_SELECT_BY_ID = "SELECT IDVendedor, Nombre, Apellido, Telefono, Usuario, Password "
            + " FROM vendedor WHERE IDVendedor = ?";

    private static final String SQL_INSERT = "INSERT INTO vendedor(Nombre, Apellido, Telefono, Usuario, Password) "
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE vendedor "
            + " SET Nombre=?, Apellido=?, Telefono=?, Usuario=?, Password=? WHERE IDVendedor=?";

    private static final String SQL_DELETE = "DELETE FROM vendedor WHERE IDVendedor = ?";

    public List<Vendedor> listar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vendedor vendedor = null;
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int IDVendedor = rs.getInt("IDVendedor");
                String Nombre = rs.getString("Nombre");
                String Apellido = rs.getString("Apellido");
                String Telefono = rs.getString("Telefono");
                String Usuario = rs.getString("Usuario");
                String Password = rs.getString("Password");

                vendedor = new Vendedor(IDVendedor, Nombre, Apellido, Telefono, Usuario, Password);
                vendedores.add(vendedor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return vendedores;
    }

    public Vendedor encontrar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, vendedor.getIdVendedor());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

                String Nombre = rs.getString("Nombre");
                String Apellido = rs.getString("Apellido");
                String Telefono = rs.getString("Telefono");
                String Usuario = rs.getString("Usuario");
                String Password = rs.getString("Password");

            vendedor.setNombre(Nombre);
            vendedor.setApellido(Apellido);
            vendedor.setTelefono(Telefono);
            vendedor.setUsuario(Usuario);
            vendedor.setPassword(Password);
                    
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(rs);
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return vendedor;
    }

    public int insertar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getApellido());
            stmt.setString(3, vendedor.getTelefono());
            stmt.setString(4, vendedor.getUsuario());
            stmt.setString(5, vendedor.getPassword());


            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int actualizar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, vendedor.getNombre());
            stmt.setString(2, vendedor.getApellido());
            stmt.setString(3, vendedor.getTelefono());
            stmt.setString(4, vendedor.getUsuario());
            stmt.setString(5, vendedor.getPassword());
            stmt.setInt(6, vendedor.getIdVendedor());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

    public int eliminar(Vendedor vendedor) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConexionBD.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, vendedor.getIdVendedor());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexionBD.close(stmt);
            ConexionBD.close(conn);
        }
        return rows;
    }

}
