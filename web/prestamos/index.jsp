<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
	<%@include file="../partials/headapp.html" %>
	<title>Registrar Préstamo</title>
    </head>
    <body>
	<jsp:include page="../partials/header.jsp">
	    <jsp:param name="title" value="Registrar Préstamo"/>
	</jsp:include>
	<main>
	    <div class="container">

		<form action="/sistemaBiblioteca/servlets/prestamo" method="POST" class="row">
		    <input type="hidden" name="accion" value="insertar">

		    <div class="col s12 input-field">
			<input type="text" id="codigocopia" name="codigocopia" class="validate" required maxlength="45" tabindex="1">
			<label for="codigocopia">Código de la Copia</label>
		    </div>

		    <div class="col s12 input-field">
			<input type="text" id="codigopersona" name="codigopersona" class="validate" required maxlength="45" tabindex="2">
			<label for="codigopersona">Código Universitario</label>
		    </div>

		    <div class="col s12 m6 input-field">
			<input type="date" class="datepicker1" id="fechaini" name="fechaini" tabindex="3">
			<label for="fechaini">Fecha de Préstamo</label>
		    </div>

		    <div class="col s12 m6 input-field">
			<input type="date" class="datepicker2" id="fechafin" name="fechafin" tabindex="4">
			<label for="fechafin">Fecha de Devolución</label>
		    </div>

		    <div class="col s12 input-field">
			<br>
			<a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</a>
			<button tabindex="5" class="waves-effect waves-light btn right">Guardar</button>
		    </div>
		</form>

	    </div>
	</main>
	<%@include file="../partials/footer.jsp" %>
	<script>
	    $('.datepicker1').pickadate({
		selectMonths: true,
		selectYears: 10,
		min: new Date(),
		onthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		monthsShort: ['Ene', 'Feb', 'Mar', 'bpr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic'],
		weekdaysFull: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sábado'],
		weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
		format: 'dd/mm/yyyy',
	    });
	    $('.datepicker2').pickadate({
		selectMonths: true,
		selectYears: 10,
		min: new Date(),
		onthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		monthsShort: ['Ene', 'Feb', 'Mar', 'bpr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic'],
		weekdaysFull: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sábado'],
		weekdaysShort: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
		format: 'dd/mm/yyyy',
	    });
	    Materialize.toast('<%= p_msj%>');
	</script>
    </body>
</html>