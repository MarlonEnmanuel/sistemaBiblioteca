/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCopia;
import entidad.eEjemplar;
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

    static public eEjemplar retornaEjemplarxCodigo(String codigo){
        try {
            Query query = em.createQuery("SELECT e FROM eEjemplar e WHERE e.codigo = :codigo");
            System.out.println("fin");
            query.setParameter("codigo", codigo);

            eEjemplar eEjemplar = (eEjemplar) query.getSingleResult();
            return eEjemplar;
        } catch (Exception e) {
            return null;
        }
    }
    
    static public eCopia retornaCopiaxCodigo(String codigo){
        try {
            Query query = em.createQuery("SELECT e FROM eCopia e WHERE e.codigo = :codigo");
            query.setParameter("codigo", codigo);
            eCopia eCopia = (eCopia) query.getSingleResult();
            return eCopia;
        } catch (Exception e) {
            return null;
        }
    }
    
    static public List<eCopia> listaCopiaxCOdigo(String idejemplar){
        try {
            Query query = em.createQuery("SELECT e FROM eCopia e WHERE e.idejemplar = :idejemplar");
            query.setParameter("idejemplar", idejemplar);
            
            List<eCopia> ls=query.getResultList();
            return ls;
        } catch (Exception e) {
            return null;
        }
    }

}
