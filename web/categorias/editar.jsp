<%@page import="entidad.eCategoria"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("msj") : "";
    eCategoria Categoria = new eCategoria(); //Modificar esta linea para obtener por id
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Editar Categoría</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Editar Categoría"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="/sistemaBiblioteca/servlets/categoria" method="POST">
		    <input type="hidden" name="accion" value="actualizar">
		    <input type="hidden" name="idcategoria" value="<%= Categoria.getIdcategoria()%>">

		    <div class="input-field">
			<input type="text" id="nombre" name="nombre" class="validate" maxlength="45" tabindex="1" autofocus value="<%= Categoria.getNombre()%>">
			<label for="nombre">Nombre de la Categoría</label>
		    </div>
		    <div class="input-field">
			<input type="text" id="descrip" name="descrip" class="validate" tabindex="2" value="<%= Categoria.getDescripcion()%>">
			<label for="descrip">Descripción</label>
		    </div>

		    <br>
		    <br>

		    <p>Cada ejemplar registra su código, título, autores, y la fecha de publicación. Sin embargo puede indicar separado por comas, datos adicionales que se deseen registrar para cada ejemplar de esta categoría.</p>
		    <br>
		    <div class="input-field">
			<input type="text" id="datos" name="datos" class="validate" tabindex="3" placeholder="Coautor, Pagina Web, Volumen, Edicion" value="<%= Categoria.getDatos()%>">
			<label for="datos">Datos Adicionales</label>
		    </div>
		    <div class="input-field">
			<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
			<button tabindex="4" class="waves-effect waves-light btn right">Actualizar</button>
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