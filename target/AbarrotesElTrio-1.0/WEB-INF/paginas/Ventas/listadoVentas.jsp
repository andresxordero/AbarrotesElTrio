<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
    <div class="superior">
        <h2>Registros de ventas</h2>
        <a href="${pageContext.request.contextPath}/ServletMenu"><button type="submit">Nuevo registro</button></a>
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
                    </tr>
                </c:forEach>
            </section>
            </tbody>
        </table>
    </div>
</div>