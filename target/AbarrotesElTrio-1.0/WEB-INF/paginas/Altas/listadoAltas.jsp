<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
      <div class="superior">
        <h2>Altas de productos</h2>
        <button type="submit">Nuevo registro</button>
      </div>

      <div class="container">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>ID del producto</th>
              <th>ID del proveedor</th>
              <th>Fecha</th>
              <th>Cantidad de ventas</th>
            </tr>
          </thead>
          <tbody>
            <section id = "altas">  
                <c:forEach var="alta" items="${altas}" varStatus="status" >
                    <tr>
                        <td>${alta.idAlta}</td>
                        <td>${alta.idProducto} </td>                            
                        <td>${alta.idProveedor}</td>
                        <td>${alta.fecha}</td>
                        <td>${alta.cantidad}</td>
                        <td><a href="${pageContext.request.contextPath}/ServletInventario?accion=editarAlta&idAlta=${alta.idAlta}"><span class="material-symbols-outlined eliminar">
                                    delete
                                </span></a></td>
                    </tr>
                </c:forEach>
            </section>
          </tbody>
        </table>
      </div>
    </div>