package Datos;

public class AltaDAO {
    private static final String SQL_SELECT ="SELECT * FROM alta";
    private static final String SQL_SELECTXID ="SELECT alta_id,producto_id,fecha,cantidad FROM cliente where alta_id=";
    private static final String SQL_INSERT = "INSERT INTO alta(producto_id,fecha,cantidad)VALUES";
    private static final String SQL_DELETE = "DELETE FROM alta WHERE alta_id=";
    
    
    
}
