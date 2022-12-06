<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
    <div class="superior">
        <h2>Registros de vendedores</h2>
        <form action="${pageContext.request.contextPath}/ServletAdministracion?accion=registrarVendedorForm" method="POST">
            <button id="agregar" type="submit">Nuevo registro</button>
        </form>
    </div>

    <div class="container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Teléfono</th>
                    <th>Rol</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <section id = "vendedores">  
                <c:forEach var="vendedor" items="${vendedores}" varStatus="status" >
                    <tr>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletAdministracion?accion=editarVendedor&idVendedor=${vendedor.idVendedor}">${vendedor.idVendedor}</a></td>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletAdministracion?accion=editarVendedor&idVendedor=${vendedor.idVendedor}">${vendedor.nombre}</a></td>    
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletAdministracion?accion=editarVendedor&idVendedor=${vendedor.idVendedor}">${vendedor.apellido}</a></td>                        
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletAdministracion?accion=editarVendedor&idVendedor=${vendedor.idVendedor}">${vendedor.telefono}</a></td>
                        <td><a class="enlace" href="${pageContext.request.contextPath}/ServletAdministracion?accion=editarVendedor&idVendedor=${vendedor.idVendedor}">${vendedor.rol}</a></td>
                        <td><a href="${pageContext.request.contextPath}/ServletAdministracion?accion=eliminarVendedor&idVendedor=${vendedor.idVendedor}"><span class="material-symbols-outlined eliminar">
                                    delete
                                </span></a></td>
                    </tr>
                </c:forEach>
            </section>
            </tbody>
        </table>
    </div>
</div>