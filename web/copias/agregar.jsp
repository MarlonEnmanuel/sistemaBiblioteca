<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Agregar Copia</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Nueva Copia"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="/sistemaBiblioteca/servlets/copia" method="POST" class="row">
		    <input type="hidden" name="accion" value="insertar">

		    <div class="col s12 input-field">
                        <input type="text" id="codigoejemplar" name="codigoejemplar" class="validate" maxlength="45" tabindex="1" autofocus>
			<label for="codigoejemplar">Código del Ejemplar</label>
		    </div>

		    <div class="col s12 input-field">
			<input type="text" id="codigo" name="codigo" class="validate" maxlength="45" tabindex="2">
			<label for="codigo">Código de la Copia</label>
		    </div>

		    <div class="col s6 input-field">
			<input class="filled-in" type="checkbox" id="estado" name="estado" tabindex="3" checked>
			<label for="estado">Estado</label>
		    </div>

		    <div class="col s6 input-field">
			<input class="filled-in" type="checkbox" id="disponible" name="disponible" tabindex="3" checked>
			<label for="disponible">Disponible</label>
		    </div>

		    <div class="col s12 input-field">
			<br><br>
			<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</a>
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