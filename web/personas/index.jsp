<%@page import="entidad.ePersona"%>
<%@page import="entidad.ePersona"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="modelo.ePersonaJpaController"%>
<%@page import="java.util.Iterator"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";

    modelo.ePersonaJpaController mp = new ePersonaJpaController();
    List<ePersona> ls = mp.findePersonaEntities();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="../partials/headapp.html" %>
        <title>Personas</title>
    </head>
    <body>
        <jsp:include page="../partials/header.jsp">
            <jsp:param name="title" value="Personas"/>
        </jsp:include>
        <main>
            <div class="container">
		<div class="right-align">
		    <a href="agregar.jsp" class="waves-effect waves-light btn">Registrar Persona</a>
		</div>
		<br>
                <table class="bordered striped">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombres</th>
                            <th>Escuela</th>
                            <th>Facultad</th>
			    <th>Tipo</th>
			    <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
			    for (Iterator it = ls.iterator(); it.hasNext();) {
				int i = 0;
				ePersona per = (ePersona) it.next();
                        %>
                        <tr>
                            <td><%= per.getCodigo() %></td>
                            <td><%= per.getNombres()+" "+per.getApellidos() %></td>
			    <td><%= per.getEscuela() %></td>
			    <td><%= per.getFacultad() %></td>
			    <td><%= per.getTipo() %></td>
                            <td width="68">
                                <a class="editar" href="editar.jsp?id=<%= per.getIdpersona() %>" title="Editar"><i class="material-icons">mode_edit</i></a>
                                <a class="elimin" href="eliminar.jsp?id=<%= per.getIdpersona() %>" title="Eliminar"><i class="material-icons">delete</i></a>
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