<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="box">
      <div class="superior">
        <h2>Altas de productos</h2>
        <form action="${pageContext.request.contextPath}/ServletInventario?accion=registrarAltaForm" method="POST">
            <button id="agregar" type="submit">Nuevo registro</button>
        </form>
      </div>

      <div class="container">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>ID del producto</th>
              <th>ID del proveedor</th>
              <th>Fecha</th>
              <th>Cantidad de altas</th>
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
                    </tr>
                </c:forEach>
            </section>
          </tbody>
        </table>
      </div>
    </div>