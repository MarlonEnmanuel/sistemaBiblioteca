<%@page import="entidad.eTema"%>
<%@page import="modelo.eTemaJpaController"%>
<%
    String p_msj = request.getParameter("msj") != null ? request.getParameter("msj") : "";
    String p_id = request.getParameter("id") != null ? request.getParameter("msj") : "";
    int id = Integer.parseInt(request.getParameter("id"));
    modelo.eTemaJpaController tjc=new eTemaJpaController();
    eTema tema=tjc.findeTema(id);

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="../partials/headapp.html" %>
        <title>Editar Tema</title>
    </head>
    <body>
        <jsp:include page="../partials/header.jsp">
            <jsp:param name="title" value="Editar Tema"/>
        </jsp:include>
        <main>
            <div class="container">

                <form action="../servlets/tema" method="POST">
                    <input type="hidden" name="accion" value="actualizar">
                    <input type="hidden" name="idtema" value="<%= tema.getIdtema()%>">

                    <div class="input-field">
                        <input type="text" id="nombre" name="nombre" class="validate" maxlength="45" tabindex="1" autofocus value="<%= tema.getNombre()%>">
                        <label for="nombre">Nombre del Tema</label>
                    </div>
                    <div class="input-field">
                        <input type="text" id="descrip" name="descrip" class="validate" tabindex="2" value="<%= tema.getDescripcion()%>">
                        <label for="descrip">Descripción</label>
                    </div>

                    <br>
                    
                    <div class="input-field">
                        <a class="waves-effect waves-light btn left" onclick="history.back()">Cancelar</A>
                        <button tabindex="3" class="waves-effect waves-light btn right">Actualizar</button>
                    </div>
                </form>

            </div>
        </main>
        <%@include file="../partials/footer.jsp" %>
        <script>
            Materialize.toast('<%= p_msj%>');
        </script>
    </body>
</html>