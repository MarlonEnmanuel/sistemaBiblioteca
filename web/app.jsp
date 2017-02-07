<%
    String p_msj = request.getParameter("msj")!=null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user")!=null ? request.getParameter("user") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="partials/headapp.html" %>
        <title>Bienvenido</title>
    </head>
    <body>
	<jsp:include page="/partials/header.jsp">
	    <jsp:param name="title" value="Inicio"/>
	</jsp:include>
        <main>
            
        </main>
	<%@include file="partials/footer.jsp" %>
        <script>
            Materialize.toast('<%= p_msj %>');
        </script>
    </body>
</html>