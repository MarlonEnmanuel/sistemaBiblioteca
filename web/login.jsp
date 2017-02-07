<%
    String p_msj = request.getParameter("msj")!=null ? request.getParameter("msj") : "";
    String p_user = request.getParameter("user")!=null ? request.getParameter("user") : "";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <%@include file="partials/head.html" %>
        <title>Login</title>
        <style>
            .login-form{
                width: 450px;
                max-width: 100%;
                padding: 20px 20px;
                display: inline-block;
            }
        </style>
    </head>
    <body>
        <main class="valign-wrapper teal">
            <div class="container valign center-align">
                <div class="login-form white z-depth-4">
                    <form class="row left-align" method="POST" action="/sistemaBiblioteca/servlets/auth">
                        <div class="col s12 center-align teal-text">
                            <h4>Sistema Biblioteca</h4>
                        </div>
                        <div class="col s12 input-field">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="user" type="text" tabindex="1" autofocus required value="<%= p_user %>">
                            <label for="user">Usuario</label>
                        </div>
                        <div class="col s12 input-field">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="pass" type="password" tabindex="2" required>
                            <label for="pass">Constraseña</label>
                        </div>
                        <div class="col s12 input-field center">
                            <button tabindex="3" id="entrar" class="waves-effect waves-light btn">Entrar</button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
        <script>
            Materialize.toast('<%= p_msj %>');
        </script>
    </body>
</html>
