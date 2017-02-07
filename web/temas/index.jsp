<%@page import="entidad.eTema"%>
<%@page import="modelo.eTemaJpaController"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user") != null ? request.getParameter("user") : "";
    modelo.eTemaJpaController tjc = new eTemaJpaController();
    List<eTema> ls= tjc.findeTemaEntities();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="../partials/headapp.html" %>
        <title>Temas</title>
    </head>
    <body>
        <jsp:include page="../partials/header.jsp">
            <jsp:param name="title" value="Temas"/>
        </jsp:include>
        <main>
            <div class="container">
		<div class="right-align">
		    <a href="agregar.jsp" class="waves-effect waves-light btn">Nuevo Tema</a>
		</div>
		<br>
                <table class="bordered striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
			    for (Iterator it = ls.iterator(); it.hasNext();) {
				int i = 0;
				eTema tema = (eTema) it.next();
                        %>
                        <tr>
                            <td><%=tema.getIdtema()%></td>
                            <td><%=tema.getNombre()%></td>
                            <td><%=tema.getDescripcion()%></td>
                            <td width="68">
                                <a class="editar" href="editar.jsp?id=<%=tema.getIdtema()%>" title="Editar"><i class="material-icons">mode_edit</i></a>
                                <a class="elimin" href="eliminar.jsp?id=<%=tema.getIdtema()%>" title="Eliminar"><i class="material-icons">delete</i></a>
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