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

    List<ePrestamo> Pendientes = new ArrayList<ePrestamo>();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Reporte de Préstamos</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Reporte de Préstamos"/>
	</jsp:include>
	<main>
	    <div class="container">
		<div class="right-align hide-on-print">
		    <button class="imprimir waves-effect waves-light btn" onClick="window.print()">Imprimir</button>
		</div>
		<h4 class="center-align">
		    Reporte de Préstamos Pendientes
		</h4>
		<table class="bordered striped">
		    <thead>
			<tr>
			    <th>Cod Copia</th>
			    <th>Ejemplar</th>
			    <th>Prestado a</th>
			    <th>Datos Pers</th>
			    <th>Fch Préstamo</th>
			    <th>Fch Devolucion</th>
			    <th class="hide-on-print"></th>
			</tr>
		    </thead>
		    <tbody>
			<%
			    for (Iterator it = Pendientes.iterator(); it.hasNext();) {
				ePrestamo pres = (ePrestamo) it.next();
				ePersona per = new ePersona();
				eEjemplar eje = new eEjemplar();
			%>
			<tr>
			    <td><%= pres.getCodigo() %></td>
			    <td><%= eje.getTitulo() %></td>
			    <td><%= per.getApellidos() %> <%= per.getApellidos() %></td>
			    <td><%= per.getCodigo() %> <%= per.getEscuela() %></td>
			    <td><%= pres.getFechaini() %></td>
			    <td><%= pres.getFechafin() %></td>
			    <td width="68" class="hide-on-print">
				<a class="editar" href="../devoluciones?cod=<%= pres.getCodigo() %>" title="Devolución"><i class="material-icons">swap_horiz</i></a>
			    </td>
			</tr>
			<% } %>
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