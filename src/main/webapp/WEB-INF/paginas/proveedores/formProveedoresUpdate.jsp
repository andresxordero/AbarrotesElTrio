
<%@page import="dominio.Vendedor"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="assets/styles/stylesRegistro.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <link rel="shortcut icon" href="assets/img/icono.ico" type="image/x-icon">
  <title>Actualizar registro de proveedor</title>
</head>

<body>
     <%
            HttpSession sesion = request.getSession();
            Vendedor usuario = (Vendedor) sesion.getAttribute("usuario");
            if (usuario.getRol().equals("Administrador")) {
        %>
        <jsp:include page="../comunes/navbarAdmin.jsp"/>
        <%
        } else {
        %>
        <jsp:include page="../comunes/navbarVendedor.jsp"/>
        <%
            }
        %>
 
  <div class="general">
      <section>
    <div class="container">
      <form class="form" action="${pageContext.request.contextPath}/ServletAdministracion?accion=modificarProveedor&idProveedor=${proveedor.idProveedor}" method="POST">
        <h2>Actualizar proveedor</h2>
        <div class="inputBox">
            <p class="subtitle">Nombre del proveedor</p>
          <input type="text" name="nombre" required value="${proveedor.nombre}">
        </div>
        <div class="inputBox">
            <p class="subtitle">Tel?fono del proveedor</p>
          <input type="number" name="telefono" required value="${proveedor.telefono}">
        </div>
        <div class="inputBox">
            <p class="subtitle">Correo del proveedor</p>
          <input type="email" name="correo" required value="${proveedor.correo}">
        </div>
        <button type="submit">Actualizar</button>
      </form>
    </div>
    </div>
  </section>
  </div>
</body>

</html>
  

  
  