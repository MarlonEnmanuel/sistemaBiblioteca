<%@page import="modelo.eTemaJpaController"%>
<%@page import="entidad.eTema"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("msj") : "";
    int id = Integer.parseInt(request.getParameter("id"));
    modelo.eTemaJpaController tjc=new eTemaJpaController();
    eTema tema=tjc.findeTema(id);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Eliminar Tema</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Eliminar Tema"/>
	</jsp:include>
	<main>
	    <div class="container">
		<br>
		<h4 class="center-align">¿Seguro que desea eliminar?</h4>
		<br>
		<br>
		<form action="../servlets/tema" method="POST">
		    <input type="hidden" name="accion" value="eliminar">
		    <input type="hidden" name="idtema" value="<%=tema.getIdtema() %>">
		    
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