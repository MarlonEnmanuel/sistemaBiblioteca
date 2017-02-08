/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.eCategoria;
import entidad.eEjemplar;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.eEjemplarJpaController;
import modelo.exceptions.IllegalOrphanException;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "ejemplar", urlPatterns = {"/servlets/ejemplar"})
public class ejemplar extends HttpServlet {

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
	    throws ServletException, IOException, ParseException, IllegalOrphanException, NonexistentEntityException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";
        if (accion.equals("insertar")) {
            this.insertar(request, response);
        }
        if (accion.equals("actualizar")) {
            this.actualizar(request, response);
        }
        if (accion.equals("eliminar")) {
            this.eliminar(request, response);
        }
    }
    
    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
	/*
	* parámetros recibidos
	* nombre, descrip, datos
	*/
	PrintWriter out = response.getWriter();
	try {
            Date date=new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
            Date datePu=formato.parse(request.getParameter("publicacion"));
            eCategoria cat=new eCategoria(Integer.parseInt(request.getParameter("idcategoria")));
            eEjemplar eje=new eEjemplar(null, date, request.getParameter("codigo"), request.getParameter("titulo"), request.getParameter("autores"), datePu, "{}", "",cat);
            eEjemplarJpaController ejc= new eEjemplarJpaController();
            if (ejc.create(eje)) {
                response.sendRedirect("/sistemaBiblioteca/ejemplares");
            }
	} finally {
	    out.close();
	}
    }
    
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
//	/*
//	* parámetros recibidos
//	* idcategoria, nombre, descrip
//	*/
	PrintWriter out = response.getWriter();
	try {
            Date date=new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
            Date datePu=formato.parse(request.getParameter("publicacion"));
            eCategoria cat=new eCategoria(Integer.parseInt(request.getParameter("idcategoria")));
            System.out.println("id ejemplar "+Integer.parseInt(request.getParameter("idejemplar")));
            eEjemplar eje=new eEjemplar(Integer.parseInt(request.getParameter("idejemplar")), date, request.getParameter("codigo"), request.getParameter("titulo"), request.getParameter("autores"), datePu, "{}", "",cat);
            eEjemplarJpaController ejc=new eEjemplarJpaController();
            try {
                if (ejc.actualiza(eje)) {
                    response.sendRedirect("/sistemaBiblioteca/ejemplares");
                }
            } catch (Exception ex) {
                Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
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
            eEjemplar eje=new eEjemplar(Integer.parseInt(request.getParameter("idejemplar")));
            eEjemplarJpaController ejc=new eEjemplarJpaController();
            try {
                if(ejc.destroy(eje)){
                    response.sendRedirect("/sistemaBiblioteca/ejemplares");
                }
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
        } catch (ParseException ex) {
            Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ejemplar.class.getName()).log(Level.SEVERE, null, ex);
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
