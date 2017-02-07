<%@page import="modelo.eCategoriaJpaController"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="entidad.eCategoria"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user") != null ? request.getParameter("user") : "";
    modelo.eCategoriaJpaController mm = new eCategoriaJpaController();
    List<eCategoria> ls = mm.findeCategoriaEntities();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="../partials/headapp.html" %>
        <title>Categorías</title>
    </head>
    <body>
        <jsp:include page="../partials/header.jsp">
            <jsp:param name="title" value="Categorías"/>
        </jsp:include>
        <main>
            <div class="container">
		<div class="right-align">
		    <a href="agregar.jsp" class="waves-effect waves-light btn">Nueva Categoría</a>
		</div>
		<br>
                <table class="bordered striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Datos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
			    for (Iterator it = ls.iterator(); it.hasNext();) {
				int i = 0;
				eCategoria cat = (eCategoria) it.next();
                        %>
                        <tr>
                            <td><%=cat.getIdcategoria()%></td>
                            <td><%=cat.getNombre()%></td>
                            <td><%=cat.getDescripcion()%></td>
                            <td><%=cat.getDatos()%></td>
                            <td width="68">
                                <a class="editar" href="editar.jsp?id=" title="Editar"><i class="material-icons">mode_edit</i></a>
                                <a class="elimin" href="eliminar.jsp?id=" title="Eliminar"><i class="material-icons">delete</i></a>
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