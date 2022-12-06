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
  <title>Panel de Inventario</title>
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
      <form class="form" action="${pageContext.request.contextPath}/ServletAdministracion?accion=registrarVendedor&idVendedor=${vendedor.idVendedor}" method="POST">
        <h2>Registrar vendedor</h2>
        <div class="inputBox">
            <input type="text" name="nombre" placeholder="Nombre" required value="${vendedor.nombre}">
        </div>
        <div class="inputBox">
            <input type="text" name="apellido" placeholder="Apellido" required value="${vendedor.apellido}">
        </div>
        <div class="inputBox">
            <input type="number" name="telefono" placeholder="Tel�fono" required value="${vendedor.telefono}">
        </div>
        <div class="inputBox">
            <input type="text" name="usuario" placeholder="Nombre de usuario" required value="${vendedor.usuario}">
        </div>
        <div class="inputBox">
            <input type="text" name="password" placeholder="Contrase�a" required value="${vendedor.password}">
        </div>
        <div class="inputBox">
            <input type="text" name="rol" placeholder="Rol" required value="${vendedor.rol}">
        </div>
        <button type="submit">Registrar</button>
      </form>
    </div>
  </section>
  </div>
</body>

</html>
  

  