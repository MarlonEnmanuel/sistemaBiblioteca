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
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name = "prestamo", urlPatterns = {"/servlets/prestamo"})
public class prestamo extends HttpServlet {

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
	if(accion.equals("")) out.println("Acción incorrecta ...");
    }
    
    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	* parámetros recibidos
	* codigocopia, codigopersona, fechaini, fechafin
	*/
	PrintWriter out = response.getWriter();
	
	//Insertar préstamo, con devuelto false y fechadev null
	//Actualizar tabla copia -> campo disponible a false
    }
    
    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	* parámetros recibidos
	* idprestamos
	*/
	PrintWriter out = response.getWriter();
	
	//Actualizar tabla prestamo -> campo devuelto a true y campo fechadev a la fecha actual
	//Actualizar tabla copia -> campo disponible a true
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
