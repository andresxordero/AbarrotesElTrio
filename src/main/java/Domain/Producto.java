package Domain;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int existencias;
    private double precioVenta;
    private double precioCompra;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, int existencias, double precioVenta, double precioCompra) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
    }

    public Producto(int idProducto, String nombre, String descripcion, int existencias, double precioVenta, double precioCompra) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }    
}
