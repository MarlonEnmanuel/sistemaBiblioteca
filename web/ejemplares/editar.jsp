<%@page import="modelo.eEjemplarJpaController"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="entidad.eEjemplar"%>
<%@page import="entidad.eCategoria"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.eCategoriaJpaController"%>
<%@page import="java.util.List"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("id") : "0";
    int id=Integer.parseInt(request.getParameter("id"));
    modelo.eEjemplarJpaController ejc=new eEjemplarJpaController();
    modelo.eCategoriaJpaController mm = new eCategoriaJpaController();
    List<eCategoria> ls = mm.findeCategoriaEntities();
    eEjemplar Ejemplar = ejc.findeEjemplar(id);
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Editar Ejemplar</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Editar Ejemplar"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="/sistemaBiblioteca/servlets/ejemplar" method="POST">
		    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="idejemplar" value="<%=Ejemplar.getIdejemplar()%>">
		    <div class="input-field" style="margin-bottom: 30px">
			<select id="idcategoria" name="idcategoria" tabindex="1" autofocus>
			    <%
				for (Iterator it = ls.iterator(); it.hasNext();) {
				    eCategoria cat = (eCategoria) it.next();
			    %>
			    <option value="<%= cat.getIdcategoria()%>" <%= (Ejemplar.getIdcategoria().getIdcategoria().equals(cat.getIdcategoria()))?"selected":"" %> ><%= cat.getNombre()%></option>
			    <% }%>
			</select>
			<label for="idcategoria">Categoría</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="codigo" name="codigo" class="validate" maxlength="45" tabindex="2" value="<%= Ejemplar.getCodigo() %>">
			<label for="codigo">Código del Ejemplar</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="titulo" name="titulo" class="validate" maxlength="45" tabindex="3" value="<%= Ejemplar.getTitulo() %>">
			<label for="titulo">Título</label>
		    </div>

		    <div class="input-field">
			<input type="text" id="autores" name="autores" class="validate" maxlength="45" tabindex="4" value="<%= Ejemplar.getAutores() %>">
			<label for="autores">Autor(s)</label>
		    </div>

		    <div class="input-field">
			<input type="date" class="datepicker" id="publicacion" name="publicacion" tabindex="5" data-value="<%= formato.format(Ejemplar.getPublicacion()) %>">
			<label for="publicacion">Fecha de Publicación</label>
		    </div>

		    <br>

		    <div class="input-field">
			<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
			<button tabindex="4" class="waves-effect waves-light btn right">Guardar</button>
		    </div>
		</form>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    $('.datepicker').pickadate({
		selectMonths: true,
		selectYears: 200,
		max: new Date(),
		onthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		monthsShort: ['Ene', 'Feb', 'Mar', 'bpr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic'],
		weekdaysFull: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sábado'],
		weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
		format: 'dd/mm/yyyy',
	    });
	    $('select').material_select();
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>