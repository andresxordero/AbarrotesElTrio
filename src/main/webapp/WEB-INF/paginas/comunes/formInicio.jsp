<!--FORMULARIO DE INICIO DE SESI?N-->

  <div class="logo">
      <img src="assets/img/logoBlanco.png" alt="logo">
  </div>
  <section class="box">
    <span class="contend">
      <div class="login">
        <h2>Inicio de sesi?n</h2>
        <h1><b>Bienvenido</b> de regreso,<br>
          por favor ingresa tus datos</h1>
        <form action="${pageContext.request.contextPath}/ServletInicio?accion=login" class="login-form" method="POST">
          <div class="texbox">
            <input type="text" placeholder="Nombre de usuario" name="username" required />
            <span class="material-symbols-outlined">account_circle</span>
          </div>
          <div class="texbox">
            <input type="password" placeholder="Contrase?a" name="password" required />
            <span class="material-symbols-outlined">lock</span>
          </div>
          <button type="submit">Iniciar sesi?n</button>
        </form>
      </div>
    </span>

  </section>