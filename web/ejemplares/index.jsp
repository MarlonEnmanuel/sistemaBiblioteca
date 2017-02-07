<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="modelo.eEjemplarJpaController"%>
<%@page import="modelo.eCategoriaJpaController"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="entidad.eCategoria"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user") != null ? request.getParameter("user") : "";
    modelo.eEjemplarJpaController ejc=new eEjemplarJpaController();
    List<eEjemplar> lseje = ejc.findeEjemplarEntities();
    modelo.eCategoriaJpaController mm = new eCategoriaJpaController();
    List<eCategoria> ls = mm.findeCategoriaEntities();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Ejemplares</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Ejemplares"/>
	</jsp:include>
	<main>
	    <div class="container">
		<div class="right-align">
		    <a href="agregar.jsp" class="waves-effect waves-light btn">Nuevo Ejemplar</a>
		</div>
		<br>
		<table class="bordered striped">
		    <thead>
			<tr>
			    <th>ID</th>
			    <th>Categoría</th>
			    <th>Codigo</th>
			    <th>Título</th>
			    <th>Autor</th>
			    <th>Publicación</th>
			    <th></th>
			</tr>
		    </thead>
		    <tbody>
			<%
			    for (Iterator it = lseje.iterator(); it.hasNext();) {
				int i = 0;
				eEjemplar eje = (eEjemplar) it.next();
                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			%>
			<tr>
			    <td><%=eje.getIdejemplar()%></td>
			    <td>hola</td>
			    <td><%=eje.getCodigo()%></td>
			    <td><%=eje.getTitulo()%></td>
			    <td><%=eje.getAutores()%></td>
                            <td><%=formato.format(eje.getPublicacion()) %></td>
			    <td width="68">
				<a class="editar" href="editar.jsp?id=<%=eje.getIdejemplar()%>" title="Editar"><i class="material-icons">mode_edit</i></a>
				<a class="elimin" href="eliminar.jsp?id=<%=eje.getIdejemplar()%>" title="Eliminar"><i class="material-icons">delete</i></a>
			    </td>
			</tr>
			<% }%>
		    </tbody>
		</table>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>