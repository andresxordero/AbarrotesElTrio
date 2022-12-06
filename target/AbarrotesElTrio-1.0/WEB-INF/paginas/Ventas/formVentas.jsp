<%@page import="java.io.PrintWriter"%>
<%@page import="datos.DetalleVentaDAO"%>
<%@page import="dominio.DetalleVenta"%>
<%@page import="java.util.List"%>
<%@page import="datos.VentaDAO"%>
<%@page import="dominio.Venta"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--FORMULARIO-->

<div class="general">

    <form action="${pageContext.request.contextPath}/ServletMenu?accion=agregarVenta" method="POST">
        <button type="submit" id="empezar">Empezar</button>
    </form>
    <div class="box">
        <form action="${pageContext.request.contextPath}/ServletMenu?accion=agregarProductoDeVenta" method="POST">
            <button id="agregar" type="submit">Agregar</button>
            <p id="puno" >ID del producto</p>

            <p id="pdos" >Cantidad</p>
            <section id = "ventas"> 
                <div class="texbox1">
                    <select name="idProductos" id="idProductos">
                        <c:forEach var="producto" items="${productos}" varStatus="status">
                            <option value="${producto.idProducto}">${producto.idProducto} (${producto.nombre})</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="texbox2">
                    <input id="dos"  type="number" placeholder="" min="1" value="1" name="cantidad" required/>
                </div>
            </section>
        </form>


        <form style="margin-top: 5rem" action="${pageContext.request.contextPath}/ServletInventario" method="POST">
            <button type="submit" id="terminar">Finalizar</button>
        </form>

        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>IDDetalleVenta</th>
                        <th>IDVenta</th>
                        <th>IDProducto</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                <section id = "detalleVentas">  
                    <%
                        Venta venta2 = new VentaDAO().encontrarUltimo();
                        List<DetalleVenta> detalleVentas = new DetalleVentaDAO().listar();
                        for (DetalleVenta detalleVenta : detalleVentas) {
                            if (detalleVenta.getIdVenta() == venta2.getIdVenta()) {
                    %>

                    <tr>
                        <td><%out.print(detalleVenta.getIdDetalleVenta());%></td>
                        <td><%out.print(detalleVenta.getIdVenta());%></td>                            
                        <td><%out.print(detalleVenta.getIdProducto());%></td>
                        <td><%out.print(detalleVenta.getCantidad());%></td>
                        <td><%out.print(detalleVenta.getTotal());%></td>
                        <td><a href="${pageContext.request.contextPath}/ServletMenu?accion=eliminarProductoDeVenta&idDetalleVenta=<%out.print(detalleVenta.getIdDetalleVenta());%>"><span class="material-symbols-outlined eliminar">
                                    delete
                                </span></a></td>
                    </tr>
                    <%
                            }
                        }
                    %>

                </section>
                </tbody>
            </table>
        </div>
    </div>
</div>