<%
String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
String p_user = request.getParameter("user") != null ? request.getParameter("user") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="../partials/headapp.html" %>
	<title>Agregar Categoría</title>
</head>
<body>
	<jsp:include page="../partials/header.jsp">
	<jsp:param name="title" value="Nueva Categoría"/>
</jsp:include>
<main>
	<div class="container">

		<form action="/sistemaBiblioteca/servlets/categoria" method="POST">
			<input type="hidden" name="accion" value="insertar">

			<div class="input-field">
				<input type="text" id="nombre" name="nombre" class="validate" maxlength="45" tabindex="1" autofocus>
				<label for="nombre">Nombre de la Categoría</label>
			</div>
			<div class="input-field">
				<input type="text" id="descrip" name="descrip" class="validate" tabindex="2">
				<label for="descrip">Descripción</label>
			</div>

			<br>
			<br>

			<p>Cada ejemplar registra su código, título, autores, y la fecha de publicación. Sin embargo puede indicar separado por comas, datos adicionales que se deseen registrar para cada ejemplar de esta categoría.</p>
			<br>
			<div class="input-field">
				<input type="text" id="datos" name="datos" class="validate" tabindex="3" placeholder="Coautor, Pagina Web, Volumen, Edicion">
				<label for="datos">Datos Adicionales</label>
			</div>
			<div class="input-field">
				<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
					<button tabindex="4" class="waves-effect waves-light btn right">Guardar</button>
				</div>
			</form>

		</div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
		Materialize.toast('<%= p_msj%>');
	</script>
</body>
</html>