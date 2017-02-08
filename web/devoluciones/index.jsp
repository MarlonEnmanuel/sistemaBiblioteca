<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.ePersonaJpaController"%>
<%@page import="modelo.modelosPersonalizados"%>
<%@page import="entidad.ePersona"%>
<%@page import="entidad.ePrestamo"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_cod = request.getParameter("cod") != null ? request.getParameter("cod") : "";
    
    modelo.modelosPersonalizados mp=new modelosPersonalizados();
    ePersonaJpaController ejc=new ePersonaJpaController();
    //Buscar prestamo por codigo y devuelto false
    ePrestamo Prestamo = mp.retornaPrestamoxCodigoCopia(p_cod);
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Devoluciones</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Devoluciones"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form method="GET" class="row">
		    <div class="col s12 m6 input-field">
			<input type="text" name="cod" id="cod" value="<%= p_cod%>" autofocus required>
			<label for="cod">Codigo de Copia</label>
		    </div>
		    <div class="col s12 m6 input-field">
			<button class="waves-effect waves-light btn">Buscar Préstamo</button>
		    </div>
		</form>

		<% if (!p_cod.equals("")) { %>
		    <% if (Prestamo == null) { %>
			<br>
			<br>
			<h5 class="center-align">Préstamo no encontado</h5>
		    <% } else {
                        ePersona Persona=ejc.findePersona(Prestamo.getIdpersona().getIdpersona());
                    %>
			<br>
			<div class="row">
			    <div class="col s6">Copia prestada</div>
			    <div class="col s6"><%= Prestamo.getCodigo() %></div>

			    <div class="col s6">Prestado a</div>
			    <div class="col s6">
				<%= Persona.getCodigo() %><br>
				<%= Persona.getNombres() %> <%= Persona.getApellidos() %><br>
				<%= Persona.getEscuela() %> <%= Persona.getFacultad() %>
			    </div>

			    <div class="col s6">Prestado el:</div>
                            <div class="col s6"><%= formato.format(Prestamo.getFechaini()) %></div>

			    <div class="col s6">Para devolver el:</div>
			    <div class="col s6"><%= formato.format(Prestamo.getFechafin()) %></div>
			</div>
			<form action="../servlets/prestamo" method="POST">
			    <input type="hidden" name="accion" value="actualizar">
			    <input type="hidden" name="idprestamo" value="<%= Prestamo.getIdprestamo() %>">
			    <div class="center-align">
				<button class="waves-effect waves-light btn">Registrar Devolución</button>
			    </div>
			</form>
		    <% }%>
		<% }%>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>