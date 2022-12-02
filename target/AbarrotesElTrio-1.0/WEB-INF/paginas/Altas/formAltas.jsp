<section>
    <div class="container">
      <form class="form">
        <h2>Registrar alta de producto</h2>
        <div class="inputBox">
          <p class="subtitle">ID del producto</p>
          <select class="comboBox" >
            <option>1</option>
          </select>
        </div>
        <div class="inputBox">
          <p class="subtitle">ID del proveedor</p>
          <select class="comboBox">
            <option>1</option>
          </select>
        </div>
        <div class="inputBox">
          <p class="subtitle">Fecha</p>
          <input type="date" placeholder="Fecha" required>
        </div>
        <div class="inputBox">
          <input type="text" placeholder="Cantidad" required>
        </div>
        <a href="${pageContext.request.contextPath}/ServletMenu?accion=agregarProducto&idProducto=${producto.idProducto}"></a>
        <button type="submit">Registrar</button>
      </form>
    </div>
    </div>
  </section>