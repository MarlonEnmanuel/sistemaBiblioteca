<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="modelo.ePersonaJpaController"%>
<%@page import="entidad.ePersona"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    ePersonaJpaController ejc=new ePersonaJpaController();
    List<ePersona> ls=ejc.findePersonaEntities();
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
	    <jsp:param name="title" value="Registrar Usuario"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="../servlets/usuario" method="POST">
		    <input type="hidden" name="accion" value="insertar">

                    <div class="input-field">
			<select id="persona" name="persona" tabindex="1">
			    <%
                            for (Iterator it = ls.iterator(); it.hasNext();) {
                                ePersona per = (ePersona) it.next();
                            %>
                            <option value="<%=per.getIdpersona()%>"> <%=per.getNombres()+" "+per.getApellidos() %> </option>
                            <%
                            }
                            %>
			</select>
			<label for="personas">Persona</label>
		    </div>
                        
                    <div class="input-field">
			<select id="tipo" name="tipo" tabindex="2">
			    <option value="Bibliotecario">Bibliotecario</option>
			    <option value="Docente">Docente</option>
			</select>
			<label for="tipo">Tipo</label>
		    </div>
		    <div class="input-field">
			<input type="text" id="user" name="user" class="validate" tabindex="3">
			<label for="apellidos">Usuario</label>
		    </div>
                    <div class="input-field">
			<input type="text" id="pass" name="pass" class="validate" tabindex="4">
			<label for="apellidos">Contraseña</label>
		    </div>
                    <div class="col s6 input-field">
			<input class="filled-in" type="checkbox" id="estado" name="estado" tabindex="5" checked>
			<label for="estado">Estado</label>
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