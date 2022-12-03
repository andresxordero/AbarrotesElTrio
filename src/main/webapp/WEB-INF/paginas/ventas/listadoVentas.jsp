<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
    <div class="superior">
        <h2>Registros de ventas</h2>
        <button type="submit">Nuevo registro</button>
    </div>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID del vendedor</th>
                    <th>Fecha</th>
                    <th>CostoTotal</th>
                </tr>
            </thead>
            <tbody>
            <section id = "ventas">  
                <c:forEach var="venta" items="${ventas}" varStatus="status" >
                    <tr>
                        <td>${venta.idVenta}</td>
                        <td>${venta.idVendedor} </td>                            
                        <td>${venta.fecha}</td>
                        <td>${venta.costoTotal}</td>
                        <td><a href="${pageContext.request.contextPath}/ServletInventario?accion=editarVenta&idCliente=${venta.idVenta}"><span class="material-symbols-outlined eliminar">
                                    delete
                                </span></a></td>
                    </tr>
                </c:forEach>
            </section>
            </tbody>
        </table>
    </div>
</div>