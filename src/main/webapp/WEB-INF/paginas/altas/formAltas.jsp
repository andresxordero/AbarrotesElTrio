<%@page import="dominio.Vendedor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form class="form" action="${pageContext.request.contextPath}/ServletInventario?accion=registrarAlta&idAlta=${alta.idAlta}" method="POST">
                        <h2>Registrar alta de producto</h2>
                        <div class="inputBox">
                            <p class="subtitle">ID del producto</p>
                            <select class="comboBox" name="idProductos" id="idProductos">
                                <c:forEach var="producto" items="${productos}" varStatus="status">
                                    <option value="${producto.idProducto}">${producto.idProducto}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inputBox">
                            <p class="subtitle">ID del proveedor</p>
                            <select class="comboBox" name="idProveedores" id="idProveedor">
                                <c:forEach var="proveedor" items="${proveedores}" varStatus="status">
                                    <option value="${proveedor.idProveedor}">${proveedor.idProveedor}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inputBox">
                            <p class="subtitle">Fecha</p>
                            <input type="date" name="fecha" placeholder="Fecha" required value="${alta.fecha}">
                        </div>
                        <div class="inputBox">
                            <input type="number" step="any" name="cantidad" placeholder="Cantidad" required value="${alta.cantidad}">
                        </div>
                        <a href="${pageContext.request.contextPath}/ServletMenu?accion=agregarProducto&idProducto=${producto.idProducto}"></a>
                        <button type="submit">Registrar</button>
                    </form>
                </div>
        </div>
    </section>
</div>
</body>

</html>

