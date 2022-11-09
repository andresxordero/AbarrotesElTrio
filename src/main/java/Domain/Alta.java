package Domain;

public class Alta {
    private int idAlta;
    private int idProducto;
    private String fecha;
    private int cantidad;

    public Alta() {
    }

    public Alta(int idAlta) {
        this.idAlta = idAlta;
    }

    public Alta(int idProducto, String fecha, int cantidad) {
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public Alta(int idAlta, int idProducto, String fecha, int cantidad) {
        this.idAlta = idAlta;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getIdAlta() {
        return idAlta;
    }

    public void setIdAlta(int idAlta) {
        this.idAlta = idAlta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }   
}
