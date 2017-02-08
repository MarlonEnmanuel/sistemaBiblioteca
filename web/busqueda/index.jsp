<%@page import="modelo.eCategoriaJpaController"%>
<%@page import="entidad.eCategoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="java.util.List"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_idcat = request.getParameter("idcat") != null ? request.getParameter("msj") : "0";
    String p_nom = request.getParameter("nom") != null ? request.getParameter("nom") : "";

    modelo.eCategoriaJpaController mm = new eCategoriaJpaController();
    List<eCategoria> Categorias = mm.findeCategoriaEntities();

    //Buscar ejemplares por idcotegoria y titulo(like)
    List<eEjemplar> ls = new ArrayList<eEjemplar>();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Búsqueda</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Buscar Material"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form method="GET" class="row">
		    <div class="col s6 input-field">
			<select name="idcat" id="idcat">
			    <option value="0"> --- </option>
			    <%
				for (Iterator it = Categorias.iterator(); it.hasNext();) {
				    eCategoria cat = (eCategoria) it.next();
			    %>
			    <option value="<%= cat.getIdcategoria()%>" <%= cat.getIdcategoria().toString().equals(p_idcat)?"selected":"" %>><%= cat.getNombre()%></option>
			    <% }%>
			</select>
			<label for="idcat">Categoría</label>
		    </div>
		    <div class="col s6 input-field">
			<input id="nom" name="nom" type="text" value="<%= p_nom %>">
			<label for="nom">Nombre a buscar</label>
		    </div>
		    <div class="col s12 input-field">
			<button class="waves-effect waves-light btn right">Buscar</button>
		    </div>
		</form>

		<table class="bordered striped">
		    <thead>
			<tr>
			    <th>Categoría</th>
			    <th>Codigo</th>
			    <th>Título</th>
			    <th>Autor</th>
			    <th>Copias Disponibles</th>
			    <th></th>
			</tr>
		    </thead>
		    <tbody>
			<%
			    for (Iterator it = ls.iterator(); it.hasNext();) {
				eEjemplar Ejem = (eEjemplar) it.next();
				//Obtener su categoria
				eCategoria Cat = new eCategoria();
				//Obtener número de copoas disponibles
				int copias = 5;
			%>
			<tr>
			    <td><%= Cat.getNombre()%></td>
			    <td><%= Ejem.getCodigo()%></td>
			    <td><%= Ejem.getTitulo()%></td>
			    <td><%= Ejem.getAutores()%></td>
			    <td><%= copias%></td>
			    <td width="68">
				<% if (Ejem.getUrlpdf() != "") {%>
				<a class="editar" href="<%= Ejem.getUrlpdf()%>" title="Ver PDF"><i class="material-icons">visibility</i></a>
				<% } %>
			    </td>
			</tr>
			<% }%>
		    </tbody>
		</table>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    $('select').material_select();
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>