/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.eUsuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.modelosPersonalizados;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "auth", urlPatterns = {"/servlets/auth"})
public class auth extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /**
        * Parámetros recibidos
        * action (login,logout)
        * user (Usuario a logear)
        * pass (Contraseña)
        */
        response.setContentType("text/html;charset=UTF-8");
        modelosPersonalizados ml=new modelosPersonalizados();
        try {
            String usu=request.getParameter("user");
            String pass=request.getParameter("pass");
            System.out.println(usu+"-"+pass);
            
            eUsuario User=ml.retornaUsuario(usu, pass);
            
            if(User!=null){
                System.out.println(User);
                response.sendRedirect("/sistemaBiblioteca/app.jsp");
                HttpSession session=request.getSession(true);
                session.setAttribute("Usuario", User);
            }else{
                response.sendRedirect("/sistemaBiblioteca/login.jsp?msj=usuario incorrecto");
            }
                    
        } finally {
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
