/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.eCopia;
import entidad.ePersona;
import entidad.ePrestamo;
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
import modelo.eCopiaJpaController;
import modelo.ePrestamoJpaController;
import modelo.modelosPersonalizados;

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String accion = request.getParameter("accion") != null ? request.getParameter("accion") : "";

        if (accion.equals("insertar")) {
            this.insertar(request, response);
        }
        if (accion.equals("actualizar")) {
            this.actualizar(request, response);
        }
        if (accion.equals("")) {
            out.println("Acción incorrecta ...");
        }
    }

    protected void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        /*
	* parámetros recibidos
	* codigocopia, codigopersona, fechaini, fechafin
         */
        PrintWriter out = response.getWriter();

        //Insertar préstamo, con devuelto false y fechadev null
        //Actualizar tabla copia -> campo disponible a false
        String codigo = request.getParameter("codigocopia");
        modelo.modelosPersonalizados mp = new modelosPersonalizados();
        eCopia cop = mp.retornaCopiaXcodigo(codigo);
        String cod = request.getParameter("codigopersona");
        ePersona per = mp.retornaPersonaXcodigo(cod);
        if (cop != null) {
            if (per != null) {
                try {
                    Date date = new Date();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateini = formato.parse(request.getParameter("fechaini"));
                    Date datefin = formato.parse(request.getParameter("fechafin"));
                    ePrestamo pre = new ePrestamo(null, date, codigo, dateini, datefin, false, null, cop, per);
                    ePrestamoJpaController pjc = new ePrestamoJpaController();
                    if (pjc.create(pre)) {
                        cop.setDisponible(false);
                        eCopiaJpaController co = new eCopiaJpaController();
                        if (co.actualiza(cop)) {
                            response.sendRedirect("/sistemaBiblioteca/prestamos?msj=REGISTRO CORRECTO");
                        }
                    } else {
                        response.sendRedirect("/sistemaBiblioteca/prestamos?msj=ERROR AL REGISTRAR");
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(prestamo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                response.sendRedirect("/sistemaBiblioteca/prestamos?msj=EL CODIGO DE LA PERSONA NO EXISTE");
            }
        } else {
            response.sendRedirect("/sistemaBiblioteca/prestamos?msj=EL CODIGO DE LA COPIA NO EXISTE O NO ESTA DISPONIBLE");
        }
    }

    protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        /*
	* parámetros recibidos
	* idprestamos
         */
        PrintWriter out = response.getWriter();

        //Actualizar tabla prestamo -> campo devuelto a true y campo fechadev a la fecha actual
        //Actualizar tabla copia -> campo disponible a true
        Date date=new Date();
        int idpre = Integer.parseInt(request.getParameter("idprestamo"));
        ePrestamoJpaController pjc = new ePrestamoJpaController();
        ePrestamo pre = pjc.findePrestamo(idpre);
        pre.setDevuelto(true);
        pre.setFechadev(date);
        if (pjc.actualiza(pre)) {
            eCopia c = pre.getIdcopia();
            c.setDisponible(true);
            eCopiaJpaController co = new eCopiaJpaController();
            if (co.actualiza(c)) {
                response.sendRedirect("/sistemaBiblioteca/devoluciones?msj=DEVOLUCION CORRECTO");
            }
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
            Logger.getLogger(prestamo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(prestamo.class.getName()).log(Level.SEVERE, null, ex);
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
