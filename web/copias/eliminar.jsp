<%@page import="entidad.eCategoria"%>
<%@page import="modelo.eCategoriaJpaController"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("msj") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Eliminar Copia</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Eliminar Copia"/>
	</jsp:include>
	<main>
	    <div class="container">
		<br>
		<h4 class="center-align">¿Seguro que desea eliminar?</h4>
		<br>
		<br>
		<form action="../servlets/copia" method="POST">
		    <input type="hidden" name="accion" value="eliminar">
		    <input type="hidden" name="idcopia" value="<%= p_id %>">
		    
		    <a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
		    <button class="waves-effect waves-light btn right">Eliminar</button>
		</form>
	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>