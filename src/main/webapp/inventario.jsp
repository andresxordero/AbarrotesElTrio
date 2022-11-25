<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" type="text/css" href="assets/styles/stylesListado.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet"
    href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <link rel="shortcut icon" href="assets/img/icono.ico" type="image/x-icon">
  <title>Panel de Inventario</title>
</head>

<body>
     <!--Formulario de Inicio-->
     <jsp:include page="/WEB-INF/paginas/comunes/navbar.jsp"/>
 
  <div class="general">
      <jsp:include page="/WEB-INF/paginas/ventas/listadoVentas.jsp"/>
      
      <jsp:include page="/WEB-INF/paginas/productos/listadoProductos.jsp"/>
      
      <jsp:include page="/WEB-INF/paginas/altas/listadoAltas.jsp"/>
  </div>
</body>

</html>