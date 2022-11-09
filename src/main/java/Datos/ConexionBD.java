
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ConexionBD {
    
    public Connection conexion;
    public Statement sentencia;
    public ResultSet resultado;
    private static final String url_bd="jdbc:mysql://localhost:3306/sistema?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String user_bd="root";
    private static final String pass_bd="Andres7Cordero";
    
  public void ConectarBD(){
  try {
     final String Controlador="com.mysql.jdbc.Driver";
     Class.forName(Controlador);
     conexion=DriverManager.getConnection(url_bd,user_bd,pass_bd);
     sentencia=conexion.createStatement();
    }
    catch(ClassNotFoundException | SQLException ex){
     System.out.print(ex);
    }
  }
  
  public  void DesconectarBD(){
  try{
      if(conexion!=null){
        if(sentencia!=null){
          sentencia.close();
        }
        conexion.close();
      }
    }catch(SQLException ex){
      JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
  }
  public static Connection getConnection() throws SQLException{
   return DriverManager.getConnection(url_bd,user_bd,pass_bd);
  }
      public static void close(ResultSet rs)throws SQLException{
    rs.close();
    }
    //Ejecuta la sentencia
    public static void close(Statement smtm)throws SQLException{
        smtm.close();
    }
    //Guarda la instruccion 
    public static void close(PreparedStatement smtm)throws SQLException{
        smtm.close();
    }
    //Cerrar la conexion 
    public static void close(Connection conn)throws SQLException{
        conn.close();
    }
  
}


