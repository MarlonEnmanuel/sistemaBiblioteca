/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.eCategoria;
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
import modelo.eCategoriaJpaController;
import modelo.exceptions.IllegalOrphanException;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "categoria", urlPatterns = {"/servlets/categoria"})
public class categoria extends HttpServlet {

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
            Date date=new Date();
	    eCategoria cat=new eCategoria(null, date, request.getParameter("nombre"), request.getParameter("descrip"), request.getParameter("datos"));
            System.out.println(cat);
            eCategoriaJpaController cjc=new eCategoriaJpaController();
            if (cjc.create(cat)) {
                response.sendRedirect("/sistemaBiblioteca/categorias");
            }
	} finally {
	    out.close();
	}
    }
    
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	* parámetros recibidos
	* idcategoria, nombre, descrip, datos
	*/
	PrintWriter out = response.getWriter();
	try {
            eCategoria cat=new eCategoria();
            cat.setIdcategoria(Integer.parseInt(request.getParameter("idcategoria")));
            cat.setNombre(request.getParameter("nombre"));
            cat.setDatos(request.getParameter("datos"));
            cat.setDescripcion(request.getParameter("descrip"));
            eCategoriaJpaController cjc=new eCategoriaJpaController();
            try {
                if(cjc.actualiza(cat)){
                    response.sendRedirect("/sistemaBiblioteca/categorias");
                }
                
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(categoria.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(categoria.class.getName()).log(Level.SEVERE, null, ex);
            }
	} finally {
	    out.close();
	}
    }
    
    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	* parámetros recibidos
	* idcategoria
	*/
	PrintWriter out = response.getWriter();
	try {
            eCategoria cat=new eCategoria();
            cat.setIdcategoria(Integer.parseInt(request.getParameter("idcategoria")));
	    eCategoriaJpaController cjc=new eCategoriaJpaController();
            try {
                if(cjc.destroy(cat)){
                    response.sendRedirect("/sistemaBiblioteca/categorias");
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
