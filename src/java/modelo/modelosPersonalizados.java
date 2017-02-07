/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCategoria;
import entidad.eUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author MiguelSc
 */
public class modelosPersonalizados {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public modelosPersonalizados() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public eUsuario retornaUsuario(String user, String pass) {
        try {
            System.out.println(user + "," + pass);
            Query query = em.createQuery("SELECT e FROM eUsuario e WHERE e.pass = :pass and e.user= :user");
            System.out.println("fin");
            query.setParameter("user", user).setParameter("pass", pass);

            eUsuario eusuario = (eUsuario) query.getSingleResult();

            return eusuario;
        } catch (Exception e) {
            return null;
        }
    }


}
