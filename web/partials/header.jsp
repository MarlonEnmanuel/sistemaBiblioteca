<%@page import="entidad.eUsuario"%>
<%
    HttpSession Sesion = request.getSession();
    String nombre = Sesion.getAttribute("Usuario")!=null ? ((eUsuario)Sesion.getAttribute("Usuario")).getUser(): "Invitado" ;
    String p_title = request.getParameter("title") != null ? request.getParameter("title") : "Principal";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style type="text/css">
    .nav-user{
	position: relative;
    }
    .nav-user img{
	display: block;
	width: 100%;
    }
    .nav-user-data{
	position: absolute;
	top: 0;
	right: 32px;
	left: 32px;
    }
    nav .divider{
	margin: 0 !important;
    }
    nav ul li *{
	color: white !important;
    }
    nav .collapsible-header{
	padding-left: 32px !important;
	padding-right: 32px !important;
    }
    nav .collapsible-body li a{
	padding-left: 50px !important;
	padding-right: 32px !important;
    }
</style>
<header>
    <nav class="blue">
	<div class="nav-wrapper container">
	    <a href="#" class="brand-logo"><%= p_title %></a>
	    <ul id="nav-mobile" class="right hide-on-med-and-down">
		<li><a href="collapsible.html">Mi Perfil</a></li>
	    </ul>
	</div>
	<ul id="slide-out" class="side-nav fixed blue-grey darken-2">
	    <li>
		<div class="nav-user">
		    <img src="/sistemaBiblioteca/vendor/img/bg01.jpg">
		    <div class="nav-user-data white-text">
			<p style="margin: 8px 0; font-size: 22px; font-weight: 300;">Sistema Biblioteca</p>
		    </div>
		</div>
	    </li>
	    <li>
		<a href="#!"><i class="material-icons">person_pin</i><%= nombre %></a>
	    </li>
	    <li><div class="divider blue-grey darken-1"></div></li>
	    <li class="no-padding">
		<ul class="collapsible collapsible-accordion">
		    <li>
			<a class="collapsible-header">
			    Mantenimientos
			    <i class="material-icons right">arrow_drop_down</i>
			</a>
			<div class="collapsible-body blue-grey darken-1">
			    <ul>
				<li><a href="/sistemaBiblioteca/categorias">Categorías</a></li>
				<li><a href="/sistemaBiblioteca/temas">Temas</a></li>
				<li><a href="/sistemaBiblioteca/ejemplares">Ejemplares</a></li>
				<li><a href="/sistemaBiblioteca/copias">Copias</a></li>
				<li><a href="/sistemaBiblioteca/personas">Personas</a></li>
			    </ul>
			</div>
		    </li>
		</ul>
	    </li>
	    <li class="no-padding">
		<ul class="collapsible collapsible-accordion">
		    <li>
			<a class="collapsible-header">
			    Operaciones
			    <i class="material-icons right">arrow_drop_down</i>
			</a>
			<div class="collapsible-body blue-grey darken-1">
			    <ul>
				<li><a href="#!">Búsqueda</a></li>
				<li><a href="#!">Préstamos</a></li>
				<li><a href="#!">Devoluciones</a></li>
			    </ul>
			</div>
		    </li>
		</ul>
	    </li>
	    <li><div class="divider blue-grey darken-1"></div></li>
	    <li><a class="waves-effect" href="#!">Cerrar Sesión</a></li>
	</ul>
	<a href="#" data-activates="slide-out" class="button-collapse">
	    <i class="material-icons">menu</i>
	</a>
    </nav>
    <script>
	$(".button-collapse").sideNav({
	    menuWidth: 260
	});
    </script>
</header>
