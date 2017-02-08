/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCopia;
import entidad.eEjemplar;
import entidad.ePersona;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author MiguelSc
 */
public class main {
    
    public static void main(String[] args) {
        modelosPersonalizados p=new modelosPersonalizados();
        eEjemplar eje = p.retornaEjemplarxCodigo("1010");
        System.out.println(eje.getIdejemplar());
    }
}