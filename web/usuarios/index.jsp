<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="entidad.eUsuario"%>
<%@page import="modelo.eUsuarioJpaController"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";

    modelo.eUsuarioJpaController mu = new eUsuarioJpaController();
    List<eUsuario> ls=mu.findeUsuarioEntities();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="../partials/headapp.html" %>
        <title>Usuarios</title>
    </head>
    <body>
        <jsp:include page="../partials/header.jsp">
         <jsp:param name="title" value="Usuarios"/>
        </jsp:include>
        <main>
            <div class="container">
		<div class="right-align">
		    <a href="agregar.jsp" class="waves-effect waves-light btn">Registrar Usuario</a>
		</div>
		<br>
                <table class="bordered striped">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Usuario</th>
                            <th>Contraseña</th>
                            <th>Tipo</th>
			    <th>Estado</th>
			    <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
			    for (Iterator it = ls.iterator(); it.hasNext();) {
				int i = 0;
				eUsuario usu = (eUsuario) it.next();
                        %>
                        <tr>
                            <td><%= usu.getIdusuario()%></td>
                            <td><%= usu.getUser()%></td>
			    <td><%= usu.getPass() %></td>
			    <td><%= usu.getTipo() %></td>
			    <td><%= usu.getEstado() ? "Activo" : "Inactivo" %></td>
                            <td width="68">
                                <a class="editar" href="editar.jsp?id=<%= usu.getIdusuario()%>" title="Editar"><i class="material-icons">mode_edit</i></a>
                                <a class="elimin" href="eliminar.jsp?id=<%= usu.getIdusuario() %>" title="Eliminar"><i class="material-icons">delete</i></a>
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