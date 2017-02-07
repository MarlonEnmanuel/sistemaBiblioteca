<%@page import="entidad.eEjemplar"%>
<%@page import="entidad.eCopia"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("id") : "";
    eCopia Copia = new eCopia();
    eEjemplar Ejemplar = new eEjemplar();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Editar Copia</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Editar Copia"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="/sistemaBiblioteca/servlets/copia" method="POST" class="row">
		    <input type="hidden" name="accion" value="actualizar">
		    <input type="hidden" name="idcopia" value="<%= Copia.getIdcopia() %>">

		    <div class="col s12 input-field">
			<input type="text" id="codigoejemplar" name="codigoejemplar" class="validate" maxlength="45" tabindex="1" autofocus value="<%= Ejemplar.getCodigo() %>">
			<label for="codigoejemplar">Código del Ejemplar</label>
		    </div>

		    <div class="col s12 input-field">
			<input type="text" id="codigo" name="codigo" class="validate" maxlength="45" tabindex="2" value="<%= Copia.getCodigo() %>">
			<label for="codigo">Código de la Copia</label>
		    </div>

		    <div class="col s6 input-field">
			<input class="filled-in" type="checkbox" id="estado" name="estado" tabindex="3" <%= Copia.getEstado()?"checked":"" %>>
			<label for="estado">Estado</label>
		    </div>

		    <div class="col s6 input-field">
			<input class="filled-in" type="checkbox" id="disponible" name="disponible" tabindex="4" checked <%= Copia.getDisponible()?"checked":"" %>>
			<label for="disponible">Disponible</label>
		    </div>

		    <div class="col s12 input-field">
			<br><br>
			<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</a>
			<button tabindex="5" class="waves-effect waves-light btn right">Guardar</button>
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