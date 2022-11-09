package Domain;

public class DetalleVenta {
    private int idDetalleVenta;
    private int idVenta;
    private int idProducto;
    private String fecha;
    private int cantidad;
    private double total;

    public DetalleVenta() {
    }

    public DetalleVenta(int idVenta, int idProducto, String fecha, int cantidad, double total) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    public DetalleVenta(int idDetalleVenta, int idVenta, int idProducto, String fecha, int cantidad, double total) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
