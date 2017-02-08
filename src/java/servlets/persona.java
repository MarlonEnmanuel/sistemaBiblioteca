/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.ePersona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.ePersonaJpaController;
import modelo.exceptions.IllegalOrphanException;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "persona", urlPatterns = {"/servlets/persona"})
public class persona extends HttpServlet {

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
	    throws ServletException, IOException, Exception {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
	
	if(accion.equals("insertar")) this.insertar(request, response);
	if(accion.equals("actualizar")) this.actualizar(request, response);
	if(accion.equals("eliminar")) this.eliminar(request, response);
	if(accion.equals("")) out.println("Acción incorrecta ...");
    }
    
    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	* parámetros recibidos
	* nombre, descrip, datos
	*/
	PrintWriter out = response.getWriter();
	try {
            Date date= new Date();
            ePersona per=new ePersona(null, date, request.getParameter("codigo"), request.getParameter("nombres"), request.getParameter("apellidos"), request.getParameter("escuela"), request.getParameter("facultad"), request.getParameter("tipo"));
            ePersonaJpaController pjc=new ePersonaJpaController();
            if (pjc.create(per)) {
                response.sendRedirect("/sistemaBiblioteca/personas?msj=INSERTO CORRECTAMENTE");
            }else{
                response.sendRedirect("/sistemaBiblioteca/personas?msj=ERROR AL INSERTAR");
            }
	} finally {
	    out.close();
	}
    }
    
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
	/*
	* parámetros recibidos
	* idcategoria, nombre, descrip, datos
	*/
	PrintWriter out = response.getWriter();
	try {
            Date date= new Date();
            ePersona per=new ePersona(Integer.parseInt(request.getParameter("idpersona")), date, request.getParameter("codigo"), request.getParameter("nombres"), request.getParameter("apellidos"), request.getParameter("escuela"), request.getParameter("facultad"), request.getParameter("tipo"));
            ePersonaJpaController pjc=new ePersonaJpaController();
            if (pjc.actualiza(per)) {
                response.sendRedirect("/sistemaBiblioteca/personas?msj=MODIFICO CORRECTAMENTE");
            }else{
                response.sendRedirect("/sistemaBiblioteca/personas?msj=ERROR AL MODIFICAR");
            }
	} finally {
	    out.close();
	}
    }
    
    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalOrphanException, NonexistentEntityException {
	/*
	* parámetros recibidos
	* idcategoria
	*/
	PrintWriter out = response.getWriter();
	try {
            ePersona per=new ePersona(Integer.parseInt(request.getParameter("idpersona")));
            ePersonaJpaController pjc=new ePersonaJpaController();
            try {
                if(pjc.destroy(per)){
                    response.sendRedirect("/sistemaBiblioteca/personas?msj=ELIMINO CORRECTAMENTE");
                }else{
                    response.sendRedirect("/sistemaBiblioteca/personas?msj=ERROR AL ELIMINAR");
                }
            } catch (IllegalOrphanException ex) {
                Logger.getLogger(categoria.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
	} finally {
	    out.close();
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(persona.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(persona.class.getName()).log(Level.SEVERE, null, ex);
        }
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
