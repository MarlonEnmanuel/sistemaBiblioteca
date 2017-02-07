<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user") != null ? request.getParameter("user") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Categorías</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Categorías"/>
	</jsp:include>
	<main>
	    <div class="container">

		<table class="bordered striped">
		    <thead>
			<tr>
			    <th>ID</th>
			    <th>Nombre</th>
			    <th>Descripción</th>
			    <th>Datos</th>
			</tr>
		    </thead>
		    <tbody>
			<tr>
			    <td>1</td>
			    <td>Tesis</td>
			    <td>Esa huevada para tener tu titulo</td>
			    <td>tesistas,jurado,asesor,carrera</td>
			    <td width="68">
				<a class="editar" href="editar.jsp?id=" title="Editar"><i class="material-icons">mode_edit</i></a>
				<a class="elimin" href="eliminar.jsp?id=" title="Eliminar"><i class="material-icons">delete</i></a>
			    </td>
			</tr>
		    </tbody>
		</table>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>