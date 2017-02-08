<%@page import="modelo.eEjemplarJpaController"%>
<%@page import="modelo.eCategoriaJpaController"%>
<%@page import="entidad.eCategoria"%>
<%@page import="entidad.eCopia"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="entidad.ePersona"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.ePrestamo"%>
<%@page import="java.util.List"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_idcat = request.getParameter("idcat") != null ? request.getParameter("idcat") : "";
    
    modelo.eEjemplarJpaController me = new eEjemplarJpaController();
    List<eEjemplar> Materiales = me.findeEjemplarEntities();

    modelo.eCategoriaJpaController mm = new eCategoriaJpaController();
    List<eCategoria> Categorias = mm.findeCategoriaEntities();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Reporte de Materiales</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Reporte de Materiales"/>
	</jsp:include>
	<main>
	    <div class="container">
		<form class="row hide-on-print" method="GET">
		    <div class="col s12 m6 input-field">
			<select name="idcat" id="idcat">
			    <option value="0"> --- </option>
			    <%
				for (Iterator it = Categorias.iterator(); it.hasNext();) {
				    eCategoria cat = (eCategoria) it.next();
			    %>
			    <option value="<%= cat.getIdcategoria()%>" <%= cat.getIdcategoria().toString().equals(p_idcat) ? "selected" : ""%>><%= cat.getNombre()%></option>
			    <% }%>
			</select>
			<label for="idcat">Generar por Categoría</label>
		    </div>
		    <div class="col s12 m6 input-field">
			<button class="imprimir waves-effect waves-light btn left" >Generar</button>
			<a class="imprimir waves-effect waves-light btn right" onClick="window.print()">Imprimir</a>
		    </div>
		    <br>
		</form>
		<% if(p_idcat!=""){ %>
		    <h4 class="center-align">
			Reporte de Materiales
		    </h4>
		    <table class="bordered striped">
			<thead>
			    <tr>
				<th>ID</th>
				<th>Categoría</th>
				<th>Cod Ejemplar</th>
				<th>Título</th>
				<th>Autor(s)</th>
				<th>Publicacion</th>
				<th class="hide-on-print">Ver Copias</th>
			    </tr>
			</thead>
			<tbody>
			    <%
				for (Iterator it = Materiales.iterator(); it.hasNext();) {
				    eEjemplar eje = (eEjemplar) it.next();
			    %>
			    <tr>
				<td><%= eje.getIdejemplar() %></td>
				<td><%= eje.getIdcategoria().getNombre() %></td>
				<td><%= eje.getCodigo() %></td>
				<td><%= eje.getTitulo() %></td>
				<td><%= eje.getAutores() %></td>
				<td><%= eje.getPublicacion() %></td>
				<td class="hide-on-print center-align">
				    <a href="../copias?cod=<%= eje.getCodigo() %>" title="Ver Copias"><i class="material-icons">visibility</i></a>
				</td>
			    </tr>
			    <% }%>
			</tbody>
		    </table>
		<% } %>
	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    $('select').material_select();
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>