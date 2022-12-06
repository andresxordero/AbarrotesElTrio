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
            <section id = "productos">  
                <c:forEach var="producto" items="${productos}" varStatus="status" >
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idProducto=${producto.idProducto}">${producto.idProducto}</a></td>
                        <td>${producto.nombre} </td>                            
                        <td>${producto.descripcion}</td>
                        <td>${producto.existencias}</td>
                        <td>${producto.precioVenta}</td>
                        <td>${producto.precioCompra}</td>
                    
                    <td><a href="${pageContext.request.contextPath}/ServletInventario?accion=eliminarProducto&idProducto=${producto.idProducto}"><span class="material-symbols-outlined eliminar">
                                delete
                            </span></a></td>
                    </tr>
                    </a>
                </c:forEach>
            </section>
            </tbody>
        </table>
    </div>
</div>