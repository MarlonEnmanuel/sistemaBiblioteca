<%@page import="modelo.ePersonaJpaController"%>
<%@page import="entidad.ePersona"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("id") : "0";
    int id=Integer.parseInt(request.getParameter("id"));
    ePersonaJpaController pjc=new ePersonaJpaController();
    ePersona Persona =pjc.findePersona(id);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Registrar Persona</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Registrar Persona"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="../servlets/persona" method="POST">
		    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="idpersona" value="<%=Persona.getIdpersona()%>">

		    <div class="input-field">
			<input type="text" id="codigo" name="codigo" class="validate" maxlength="45" tabindex="1" autofocus value="<%= Persona.getCodigo()%>">
			<label for="codigo">Código Universitario</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="nombres" name="nombres" class="validate" tabindex="2" value="<%= Persona.getNombres()%>">
			<label for="nombres">Nombres</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="apellidos" name="apellidos" class="validate" tabindex="3" value="<%= Persona.getApellidos()%>">
			<label for="apellidos">Apellidos</label>
		    </div>

		    <div class="input-field">
			<select id="escuela" name="escuela" tabindex="4">
			    <option value="Ingeniería de Sistemas" <%= Persona.getEscuela().equals("") ? "selected" : ""%>>Ingeniería de Sistemas</option>
			    <option value="Ingeniería Civil" <%= Persona.getEscuela().equals("") ? "selected" : ""%>>Ingeniería Civil</option>
			    <option value="Arquitectura" <%= Persona.getEscuela().equals("") ? "selected" : ""%>>Arquitectura</option>
			    <option value="Medicina Humana" <%= Persona.getEscuela().equals("") ? "selected" : ""%>>Medicina Humana</option>
			    <option value="Enfermería" <%= Persona.getEscuela().equals("") ? "selected" : ""%>>Enfermería</option>
			</select>
			<label for="escuela">Escuela Profesional</label>
		    </div>

		    <div class="input-field">
			<select id="facultad" name="facultad" tabindex="5">
			    <option value="FICSA" <%= Persona.getFacultad().equals("FICSA") ? "selected" : ""%>>FICSA</option>
			    <option value="FIME" <%= Persona.getFacultad().equals("FIME") ? "selected" : ""%>>FIME</option>
			    <option value="FACHSE" <%= Persona.getFacultad().equals("FACHSE") ? "selected" : ""%>>FACHSE</option>
			    <option value="FIQUIA" <%= Persona.getFacultad().equals("FIQUIA") ? "selected" : ""%>>FIQUIA</option>
			    <option value="FACFYM" <%= Persona.getFacultad().equals("FACFYM") ? "selected" : ""%>>FACFYM</option>
			</select>
			<label for="facultad">Facultad</label>
		    </div>

		    <div class="input-field">
			<select id="tipo" name="tipo" tabindex="6">
			    <option value="Alumno" <%= Persona.getTipo().equals("Alumno")?"selected":"" %>>Alumno</option>
			    <option value="Docente" <%= Persona.getTipo().equals("Docente")?"selected":"" %>>Docente</option>
			</select>
			<label for="tipo">Facultad</label>
		    </div>

		    <br>

		    <div class="input-field">
			<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
			<button tabindex="7" class="waves-effect waves-light btn right">Guardar</button>
		    </div>
		</form>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    $('select').material_select();
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>