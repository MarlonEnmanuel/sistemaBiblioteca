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
	<title>Eliminar Persona</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Eliminar Persona"/>
	</jsp:include>
	<main>
	    <div class="container">
		<br>
		<h4 class="center-align">¿Seguro que desea eliminar?</h4>
		<br>
		<br>
		<form action="../servlets/persona" method="POST">
		    <input type="hidden" name="accion" value="eliminar">
		    <input type="hidden" name="idpersona" value="<%= p_id%>">

		    <a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
		    <button class="waves-effect waves-light btn right">Eliminar</button>
		</form>
	    </div>
	</main>
	<div style="position: absolute; right: 0; left: 0; z-index: 1000; bottom: 0; display: none;" id="dnbook">
	    <div class="container" style="max-width: 600px; height: 80%; overflow: hidden;">
		<img src="../vendor/img/book.png" alt="Death Note" style="display: block; width: 100%;">
	    </div>
	</div>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    Materialize.toast('<%= p_msj%>');
	    var timer;
	    $(document).keydown(function (ev) {
		if (ev.keyCode == 115) {
		    $('#dnbook').slideDown(600);
		    window.clearTimeout(timer);
		    timer = window.setTimeout(function () {
			$('#dnbook').slideUp(400);
		    }, 2000);
		}
	    });
	</script>
    </body>
</html>