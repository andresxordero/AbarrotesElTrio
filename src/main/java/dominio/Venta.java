package dominio;

public class Venta {
    private int idVenta;
    private int idVendedor;
    private String fecha;
    private double costoTotal;

    public Venta() {
    }

    public Venta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Venta(int idVendedor, String fecha, double costoTotal) {
        this.idVendedor = idVendedor;
        this.fecha = fecha;
        this.costoTotal = costoTotal;
    }

    public Venta(int idVenta, int idVendedor, String fecha, double costoTotal) {
        this.idVenta = idVenta;
        this.idVendedor = idVendedor;
        this.fecha = fecha;
        this.costoTotal = costoTotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    
}
