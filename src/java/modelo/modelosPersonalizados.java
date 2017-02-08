/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCopia;
import entidad.eEjemplar;
import entidad.ePersona;
import entidad.ePrestamo;
import entidad.eUsuario;
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
            Query query = em.createQuery("SELECT e FROM eUsuario e WHERE e.pass = :pass and e.user= :user and e.estado=1");
            query.setParameter("user", user).setParameter("pass", pass);

            eUsuario eusuario = (eUsuario) query.getSingleResult();

            return eusuario;
        } catch (Exception e) {
            return null;
        }
    }


    public eEjemplar retornaEjemplarxCodigo(String codigo){
        try {
            Query query=em.createQuery("SELECT e FROM eEjemplar e WHERE e.codigo= :codigo");
            query.setParameter("codigo", codigo);
            eEjemplar eje=(eEjemplar) query.getSingleResult();
            return eje;
        } catch (Exception e) {
            return null;
        }
    }
    
    public eCopia retornaCopiaXcodigo(String codigo){
        try {
        Query query=em.createQuery("SELECT e FROM eCopia e WHERE e.codigo= :codigo AND e.disponible=1");
            query.setParameter("codigo", codigo);
            eCopia cop=(eCopia) query.getSingleResult();
            return cop;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ePersona retornaPersonaXcodigo(String codigo){
        try {
            Query query=em.createQuery("SELECT e FROM ePersona e WHERE e.codigo= :codigo");
            query.setParameter("codigo", codigo);
            ePersona cop=(ePersona) query.getSingleResult();
            return cop;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    public ePrestamo retornaPrestamoxCodigoCopia(String codigo){
        try {
            Query query=em.createQuery("SELECT e FROM ePrestamo e WHERE e.codigo= :codigo and e.devuelto=0");
            query.setParameter("codigo", codigo);
            ePrestamo pre=(ePrestamo) query.getSingleResult();
            return pre;
        } catch (Exception e) {
            return null;
        }
    }
}
