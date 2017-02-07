/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidad.eCategoria;
import java.util.List;
import modelo.eCategoriaJpaController;

/**
 *
 * @author MiguelSc
 */
public class main {
    public static void main(String[] args) {
        modelo.eCategoriaJpaController mm=new eCategoriaJpaController();
        List<eCategoria> ls=mm.findeCategoriaEntities();
        System.out.println(ls.size());
    }
}
