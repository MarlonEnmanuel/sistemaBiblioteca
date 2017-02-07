/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.eCategoria;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.eCategoriaJpaController;

/**
 *
 * @author MiguelSc
 */
public class main {
    public static void main(String[] args) {
        modelo.eCategoriaJpaController mm=new eCategoriaJpaController();
        Date date = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        eCategoria eCategoria1=new eCategoria(null,date, "prueba", "prueba", "prueba");
        mm.create(eCategoria1);
    }
}
