/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.eTema;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author MiguelSc
 */
public class eTemaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eTemaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public boolean create(eTema eTema) {
        try {
            em.getTransaction().begin();
            em.persist(eTema);
            em.getTransaction().commit();
            return true;
        }  catch (Exception e) {
            return false;
        }
    }

   public boolean actualiza(eTema eTema) throws NonexistentEntityException, Exception{
        try {
            eTema persistenteTema = em.find(eTema.class, eTema.getIdtema());
            em.getTransaction().begin();
            persistenteTema.setNombre(eTema.getNombre());
            persistenteTema.setDescripcion(eTema.getDescripcion());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean destroy(eTema eTema) throws NonexistentEntityException {
        try {
            eTema persistenteTema = em.find(eTema.class, eTema.getIdtema());
            em.getTransaction().begin();
            em.remove(persistenteTema);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<eTema> findeTemaEntities() {
        return findeTemaEntities(true, -1, -1);
    }

    public List<eTema> findeTemaEntities(int maxResults, int firstResult) {
        return findeTemaEntities(false, maxResults, firstResult);
    }

    private List<eTema> findeTemaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eTema.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public eTema findeTema(Integer id) {
        try {
            return em.find(eTema.class, id);
        } finally {
            em.close();
        }
    }

    public int geteTemaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eTema> rt = cq.from(eTema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
