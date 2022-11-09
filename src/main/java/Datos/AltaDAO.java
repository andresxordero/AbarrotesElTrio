package Datos;

import static Datos.ConexionBD.close;
import static Datos.ConexionBD.getConnection;
import Domain.Alta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AltaDAO {
    private static final String SQL_SELECT ="SELECT * FROM alta";
    private static final String SQL_SELECTXID ="SELECT alta_id,producto_id,fecha,cantidad FROM cliente where alta_id=";
    private static final String SQL_INSERT = "INSERT INTO alta(producto_id,fecha,cantidad)VALUES";
    private static final String SQL_DELETE = "DELETE FROM alta WHERE alta_id=";
    
    public List<Alta> seleccionarAltas(){
        Connection conn=null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alta alta = null;
        List<Alta> altaList = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT); 
            rs = stmt.executeQuery();
            
            while(rs.next()){
            int idCliente = rs.getInt("cliente_id");
            String nombre = rs.getString("nombre");
            String apellido1 = rs.getString("primer_apellido");
            String apellido2 = rs.getString("segundo_apellido");
            
            alta = new Alta(idCliente,nombre,apellido1,apellido2);
            altaList.add(alta);
            
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
        finally{
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                 ex.printStackTrace(System.out);
            }
        
        }
        
        return altaList;
    }
    
    public String insertar (Alta alta){
        String sms ="";
       try { 
           ConexionBD Con = new ConexionBD();
             Con.ConectarBD();
             Con.sentencia.execute(SQL_INSERT +"('"+alta.getNombre()+"','"+alta.getPrimer_apellido()+"','"+alta.getSegundo_apellido()+"')");
             sms="Los Datos fueron Insertados con exito";
             Con.DesconectarBD();
         } catch (SQLException ex) {
            sms=ex.toString();
          }
       return sms;
    }
    
     public String eliminar(Alta alta){
           String sms ="";
       try{
            
            ConexionBD Con=new ConexionBD();
            Con.ConectarBD();
            Con.sentencia.execute(SQL_DELETE + alta.getIdAlta());
             sms="El registro fue eliminado";

            Con.DesconectarBD();
        }catch(SQLException ex){
            sms=ex.toString();
        }
       return sms;
    }
     
     public Alta seleccionarAlta(int id){
        Connection conn=null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Alta alta = null;
         try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECTXID+id); 
            rs = stmt.executeQuery();
            
            while(rs.next()){
            int idCliente = rs.getInt("cliente_id");
            String nombre = rs.getString("nombre");
            String apellido1 = rs.getString("primer_apellido");
            String apellido2 = rs.getString("segundo_apellido");
            
            alta = new Alta(idCliente,nombre,apellido1,apellido2);
             }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);//Error desde la BD
            
        }
        finally{
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                 ex.printStackTrace(System.out);//Error desde la BD
            }
        
        }
        return alta;
     }
     
     public String actualizar (Alta alta){
       String sms ="";
       String SQL_UPDATE ="UPDATE cliente SET nombre='"+cliente.getNombre()+"',primer_apellido='"+cliente.getPrimer_apellido()+"',segundo_apellido='"+cliente.getSegundo_apellido()+"'  WHERE cliente_id= "+cliente.getIdPersona();
     try { 
            ConexionBD Con=new ConexionBD();
            Con.ConectarBD();
            Con.sentencia.execute(SQL_UPDATE);
            sms="Registro Actualizado";
            Con.DesconectarBD();
        }catch (SQLException ex) {
           sms=ex.toString();
        }
       return sms;
    }
 
}
