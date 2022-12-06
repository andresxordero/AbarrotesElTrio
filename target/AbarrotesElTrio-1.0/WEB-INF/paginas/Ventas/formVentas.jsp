<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--FORMULARIO-->

<div class="general">
    <div class="box">
        <h2>Venta</h2>
        <!<!-- Aqui va el método para calcular el precio -->
        <h3>TOTAL: $55</h3>

        <form action="${pageContext.request.contextPath}/ServletMenu?accion=agregarVenta"
              method="POST">
            <button id="agregar" type="submit">Agregar</button>

        </form>

        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>IDVenta</th>
                        <th>IDVendedor</th>
                        <th>Fecha</th>
                        <th>CostoTotal</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="venta" items="${ventas}" varStatus="status" >
                    <a href="${pageContext.request.contextPath}/ServletMenu?accion=eliminarVenta&idVenta=${venta.idVenta}">

                        <tr>
                            <td>${venta.idVenta}</td>
                            <td>${venta.idVendedor}</td>
                            <td>${venta.fecha}</td>
                            <td>${venta.costoTotal}</td>


                            <td><a href="${pageContext.request.contextPath}/ServletMenu?accion=eliminarVenta&idVenta=${venta.idVenta}"><span class="material-symbols-outlined eliminar">
                                        delete
                                    </span></a></td>


                        </tr>
                    </a>
                </c:forEach>

                </tbody>
            </table>
        </div>


    </div>
    <div class="box">
        <form action="${pageContext.request.contextPath}/ServletMenu?accion=agregarProductoDeVenta" method="POST">
            <button id="agregar" type="submit">Agregar</button>
            <p id="puno" >ID del producto</p>
            <p id="puno" style="margin-top:4rem">ID de la Venta</p>
            <p id="pdos" >Cantidad</p>
            <section id = "ventas"> 
                <div class="texbox1">
                    <select name="idVentas" id="idVentas">
                        <c:forEach var="venta" items="${ventas}" varStatus="status">
                            <option value="${venta.idVenta}">${venta.idVenta}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="texbox1" style="margin-top: 5rem;">
                    <select name="idProductos" id="idProductos">
                        <c:forEach var="producto" items="${productos}" varStatus="status">
                            <option value="${producto.idProducto}">${producto.idProducto}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="texbox2">
                    <input id="dos"  type="number" placeholder="" min="1" name="cantidad" required/>
                </div>
            </section>
        </form>
            
        <form action="${pageContext.request.contextPath}/ServletMenu?accion=registrarVenta" method="POST">
            <button type="submit" id="terminar">Terminar</button>
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
                    <c:forEach var="detalleVenta" items="${detalleVentas}" varStatus="status" >
                        <tr>
                            <td>${detalleVenta.idDetalleVenta}</td>
                            <td>${detalleVenta.idVenta} </td>                            
                            <td>${detalleVenta.idProducto}</td>
                            <td>${detalleVenta.cantidad}</td>
                            <td>${detalleVenta.total}</td>
                            <td><a href="${pageContext.request.contextPath}/ServletInventario?accion=editarVendedor&idVendedor=${vendedor.idVendedor}"><span class="material-symbols-outlined eliminar">
                                        delete
                                    </span></a></td>
                        </tr>
                    </c:forEach>
                </section>
                </tbody>
            </table>
        </div>
    </div>
</div>