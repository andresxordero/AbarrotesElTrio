<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
      <div class="superior">
        <h2>Registros de proveedores</h2>
      <button type="submit">Nuevo registro</button>
      </div>
      
      <div class="container">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Teléfono</th>
              <th>Correo</th>
            </tr>
          </thead>
          <tbody>
            <section id = "proveedores">  
                <c:forEach var="proveedor" items="${proveedores}" varStatus="status" >
                    <tr>
                        <td>${proveedor.idProveedor}</td>
                        <td>${proveedor.nombre} </td>                            
                        <td>${proveedor.telefono}</td>
                        <td>${proveedor.correo}</td>
                        <td><a href="${pageContext.request.contextPath}/ServletInventario?accion=editarProducto&idCliente=${proveedor.idProveedor}"><span class="material-symbols-outlined eliminar">
                                    delete
                                </span></a></td>
                    </tr>
                </c:forEach>
            </section>
          </tbody>
        </table>
      </div>
    </div>