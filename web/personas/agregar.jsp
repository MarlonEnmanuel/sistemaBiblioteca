<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
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
		    <input type="hidden" name="accion" value="insertar">

		    <div class="input-field">
			<input type="text" id="codigo" name="codigo" class="validate" maxlength="45" tabindex="1" autofocus>
			<label for="codigo">Código Universitario</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="nombres" name="nombres" class="validate" tabindex="2">
			<label for="nombres">Nombres</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="apellidos" name="apellidos" class="validate" tabindex="3">
			<label for="apellidos">Apellidos</label>
		    </div>
                    
                    <div class="input-field">
			<select id="facultad" name="facultad" tabindex="5">
			    <option value="FICSA">FICSA</option>
			    <option value="FIME">FIME</option>
			    <option value="FACHSE">FACHSE</option>
			    <option value="FIQUIA">FIQUIA</option>
			    <option value="FACFYM">FACFYM</option>
			</select>
			<label for="facultad">Facultad</label>
		    </div>
                    
		    <div class="input-field">
			<select id="escuela" name="escuela" tabindex="4">
			    <option value="Ingeniería de Sistemas">Ingeniería de Sistemas</option>
			    <option value="Ingeniería Civil">Ingeniería Civil</option>
			    <option value="Arquitectura">Arquitectura</option>
			    <option value="Medicina Humana">Medicina Humana</option>
			    <option value="Enfermería">Enfermería</option>
			</select>
			<label for="escuela">Escuela Profesional</label>
		    </div>

		    <div class="input-field">
			<select id="tipo" name="tipo" tabindex="6">
			    <option value="Alumno">Alumno</option>
			    <option value="Docente">Docente</option>
			</select>
			<label for="tipo">Tipo</label>
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