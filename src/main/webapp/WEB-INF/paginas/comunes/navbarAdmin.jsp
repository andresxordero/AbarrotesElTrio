<!--BARRA DE NAVEGACI�N-->
  <header class="header">
    <img src="assets/img/logoNaranja.png" alt="Logo de la marca" />
    <nav>
      <ul class="nav-links">
        <li><a href="${pageContext.request.contextPath}/ServletMenu">Men�</a></li>
        <li><a href="${pageContext.request.contextPath}/ServletInventario">Inventario</a></li>
        <li><a href="${pageContext.request.contextPath}/ServletAdministracion">Administraci�n</a></li>
      </ul>
    </nav>
    <a href="${pageContext.request.contextPath}/ServletInicio"><span class="material-symbols-outlined salir">logout
      </span></a>

  </header>