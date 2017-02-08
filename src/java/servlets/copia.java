package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entidad.eCopia;
import entidad.eEjemplar;
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
import modelo.eCopiaJpaController;
import modelo.exceptions.IllegalOrphanException;
import modelo.exceptions.NonexistentEntityException;
import modelo.modelosPersonalizados;

/**
 *
 * @author Enmanuel
 */
@WebServlet(name="copia", urlPatterns = {"/servlets/copia"})
public class copia extends HttpServlet {

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
	    throws ServletException, IOException, IllegalOrphanException, NonexistentEntityException {
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
            boolean estado;
            boolean disponible;
            if(null!=request.getParameter("disponible")){
                disponible=true;
            }else{
                disponible=false;
            }
            if(null!=request.getParameter("estado")){
                estado=true;
            }else{
                estado=false;
            }
            modelosPersonalizados mp=new modelosPersonalizados();
            eEjemplar Ejemplar = mp.retornaEjemplarxCodigo(request.getParameter("codigoejemplar"));
            eCopia cop=new eCopia(null, date, request.getParameter("codigo"), estado, disponible,Ejemplar);
            eCopiaJpaController cjc=new eCopiaJpaController();
            if (cjc.create(cop)) {
                response.sendRedirect("/sistemaBiblioteca/copias?msj=INSERTO CORRECTAMENTE");
            }else{
                response.sendRedirect("/sistemaBiblioteca/copias?msj=ERROR AL INSERTAR");
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
            Date date=new Date();
            boolean estado;
            boolean disponible;
            if(null!=request.getParameter("disponible")){
                disponible=true;
            }else{
                disponible=false;
            }
            if(null!=request.getParameter("estado")){
                estado=true;
            }else{
                estado=false;
            }
            modelosPersonalizados mp=new modelosPersonalizados();
            eEjemplar Ejemplar = mp.retornaEjemplarxCodigo(request.getParameter("codigoejemplar"));
            eCopia cop=new eCopia(Integer.parseInt(request.getParameter("idcopia")), date, request.getParameter("codigo"), estado, disponible,Ejemplar);
            eCopiaJpaController cjc=new eCopiaJpaController();
            try {
                if (cjc.actualiza(cop)) {
                    response.sendRedirect("/sistemaBiblioteca/copias?msj=MODIFICO CORRECTAMENTE");
                }else{
                    response.sendRedirect("/sistemaBiblioteca/copias?msj=ERROR AL MODIFICAR");
                }
            } catch (Exception ex) {
                Logger.getLogger(copia.class.getName()).log(Level.SEVERE, null, ex);
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
            eCopia cop=new eCopia(Integer.parseInt(request.getParameter("idcopia")));
            eCopiaJpaController cjc=new eCopiaJpaController();
            try {
                if(cjc.destroy(cop)){
                    response.sendRedirect("/sistemaBiblioteca/copias?msj=ELIMINO CORRECTAMENTE");
                }else{
                    response.sendRedirect("/sistemaBiblioteca/copias?msj=ERROR AL ELIMINAR");
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
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(copia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(copia.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(copia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(copia.class.getName()).log(Level.SEVERE, null, ex);
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
