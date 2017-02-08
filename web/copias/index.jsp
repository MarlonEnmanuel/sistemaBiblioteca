<%@page import="modelo.modelosPersonalizados"%>
<%@page import="entidad.eCopia"%>
<%@page import="modelo.eEjemplarJpaController"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="entidad.eCategoria"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user") != null ? request.getParameter("user") : "";
    String p_cod = request.getParameter("cod") != null ? request.getParameter("cod") : "";
    eEjemplar Ejemplar = modelosPersonalizados.retornaEjemplarxCodigo(p_cod);
    List<eCopia> Copias = modelosPersonalizados.listaCopiaxCodigoEjemplar(p_cod);
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Copias</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Copias"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form method="GET" class="row">
		    <div class="col s12 m6 input-field">
			<input type="text" name="cod" id="cod" value="<%= p_cod%>" autofocus required>
			<label for="cod">Codigo de Ejemplar</label>
		    </div>
		    <div class="col s12 m6 input-field">
			<button class="waves-effect waves-light btn">Buscar Copias</button>
			<a href="agregar.jsp" class="waves-effect waves-light btn right">Nueva Copia</a>
		    </div>
		</form>

		<% if (!p_cod.equals("")) { %>
		    <% if (Ejemplar == null) { %>
			<br>
			<br>
			<h5 class="center-align">Código de ejemplar no existe</h5>
		    <% } else { %>
			<table class="bordered striped">
			    <thead>
				<tr>
				    <th>ID</th>
				    <th>Código Copia</th>
				    <th>Estado</th>
				    <th>Disponible</th>
				    <th></th>
				</tr>
			    </thead>
			    <tbody>
				<%
				    for (Iterator it = Copias.iterator(); it.hasNext();) {
					eCopia cat = (eCopia) it.next();
				%>
				<tr>
				    <td><%= cat.getIdcopia()%></td>
				    <td><%= cat.getCodigo()%></td>
				    <td><%= cat.getEstado() ? "Activo" : "Inactivo"%></td>
				    <td><%= cat.getDisponible() ? "Si" : "No"%></td>
				    <td width="68">
					<a class="editar" href="editar.jsp?id=<%= cat.getIdcopia()%>" title="Editar"><i class="material-icons">mode_edit</i></a>
					<a class="elimin" href="eliminar.jsp?id=<%= cat.getIdcopia()%>" title="Eliminar"><i class="material-icons">delete</i></a>
				    </td>
				</tr>
				<% }%>
			    </tbody>
			</table>
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