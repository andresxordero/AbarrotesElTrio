<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
    <div class="superior">
        <h2>Registros de productos</h2>
        <form action="${pageContext.request.contextPath}/ServletInventario?accion=registrarProductoForm" method="POST">
            <button id="agregar" type="submit">Nuevo registro</button>
        </form>

    </div>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Existencias</th>
                    <th>Precio de ventas</th>
                    <th>Precio de compra</th>
                </tr>
            </thead>
            <tbody>
            <section id="productos">  
                <c:forEach var="producto" items="${productos}" varStatus="status" >
                    <tr>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.idProducto}</a></td></span>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.nombre}</a></td>                            
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.descripcion}</a></td>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.existencias}</a></td>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.precioVenta}</a></td>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.precioCompra}</a></td>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletInventario?accion=eliminarProducto&idProducto=${producto.idProducto}"><span class="material-symbols-outlined eliminar">
                                    delete
                                </span></a></td>
                    </tr>
                </c:forEach>
            </section>
            </tbody>
        </table>
    </div>
</div>