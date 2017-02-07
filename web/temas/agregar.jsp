<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Agregar Tema</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Nuevo Tema"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="../servlets/tema" method="POST">
		    <input type="hidden" name="accion" value="insertar">

		    <div class="input-field">
			<input type="text" id="nombre" name="nombre" class="validate" maxlength="45" tabindex="1" autofocus>
			<label for="nombre">Nombre del Tema</label>
		    </div>
		    <div class="input-field">
			<input type="text" id="descrip" name="descrip" class="validate" tabindex="2">
			<label for="descrip">Descripción</label>
		    </div>

		    <br>
		    
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