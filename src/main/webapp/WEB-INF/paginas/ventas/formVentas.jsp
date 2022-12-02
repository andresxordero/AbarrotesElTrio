<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!--FORMULARIO-->
  <div class="general">
    <div class="box">
      <h2>Venta</h2>
      <h3>TOTAL: $55</h3>
      
      <form>
          <a href="${pageContext.request.contextPath}/ServletMenu?accion=agregarProducto&idProducto=${producto.idProducto}"><button id="agregar" type="submit">Agregar</button></a>
          <a href="${pageContext.request.contextPath}/ServletMenu?accion=registrarVenta&idVenta=${venta.idVenta}"><button id="terminar" type="submit">Terminar</button></a>
      <p id="puno" for="idProducto">Código de barras</p>
      <p id="pdos" for="cantidad">Cantidad</p>
      <div class="texbox1">
        <select name="idProducto" id="">
            <c:forEach var="producto" items="${productos}" varStatus="status" >
                <option>${producto.id}</option>
            </c:forEach>
        </select>
      </div>
      <div class="texbox2">
          <input id="dos" type="text" placeholder="" name="cantidad" required/>
      </div>
      </form>
      
      <div class="container">
        <table>
          <thead>
            <tr>
              <th>Producto</th>
              <th>Cantidad</th>
              <th>Subtotal</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
              <c:forEach var="producto" items="${productos}" varStatus="status" >
            </c:forEach>
              <tr>
                <td>Agua Bonafont</td>
                <td>1</td>
                <td>$13</td>
                <td><a href="${pageContext.request.contextPath}/ServletMenu?accion=eliminar&idVenta=${venta.idVenta}"><span class="material-symbols-outlined">
                  delete
                </span></a></td>
              </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>