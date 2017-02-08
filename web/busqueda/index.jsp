<%@page import="modelo.eEjemplarJpaController"%>
<%@page import="modelo.eCategoriaJpaController"%>
<%@page import="entidad.eCategoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="java.util.List"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_idcat = request.getParameter("idcat") != null ? request.getParameter("idcat") : "0";
    String id=request.getParameter("idcat");
    modelo.eCategoriaJpaController mm = new eCategoriaJpaController();
    List<eCategoria> Categorias = mm.findeCategoriaEntities();

    //Buscar ejemplares por idcotegoria y titulo(like)
    modelo.eEjemplarJpaController ejc=new eEjemplarJpaController();
    List<eEjemplar> ls=null;
    if (id!=null) {
        ls = ejc.findeEjemplarEntities();
    }
    
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
			    <th></th>
			</tr>
		    </thead>
		    <tbody>
			<%
                            if (ls!=null) {
                                for (Iterator it = ls.iterator(); it.hasNext();) {
                                    eEjemplar Ejem = (eEjemplar) it.next();
                                    //Obtener su categoria
                                    eCategoria Cat = Ejem.getIdcategoria();
                                    //Obtener número de copoas disponibles
			%>
			<tr>
                            <%
                                if (Cat.getIdcategoria()==Integer.parseInt(id)) {
                                      
                            %>
			    <td><%= Ejem.getIdcategoria().getNombre() %></td>
			    <td><%= Ejem.getCodigo()%></td>
			    <td><%= Ejem.getTitulo()%></td>
			    <td><%= Ejem.getAutores()%></td>
			    <td width="68">
				<% if (Ejem.getUrlpdf() != "") {%>
				<a class="editar" href="<%= Ejem.getUrlpdf()%>" title="Ver PDF"><i class="material-icons">visibility</i></a>
				<% } %>
			    </td>
			</tr>
			<%          }
                                }
                            }   
                        %>
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