/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.ePersona;
import java.sql.Date;

/**
 *
 * @author MiguelSc
 */
public class main {
    
    public static void main(String[] args) {
        Date i=new Date(2017, 2, 6);
        ePersona p=new ePersona(null,i , "1010", "prueba","prueba","ing sitemas","ficsa","alumno");
        modelo.ePersonaJpaController d=new ePersonaJpaController();
        d.create(p);
    }
}