/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eEjemplartema;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author MiguelSc
 */
public class eEjemplartemaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eEjemplartemaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public void create(eEjemplartema eEjemplartema) {
        try {
            em.getTransaction().begin();
            em.persist(eEjemplartema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eEjemplartema eEjemplartema) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eEjemplartema = em.merge(eEjemplartema);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eEjemplartema.getEjemplartemaId();
                if (findeEjemplartema(id) == null) {
                    throw new NonexistentEntityException("The eEjemplartema with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        try {
            em.getTransaction().begin();
            eEjemplartema eEjemplartema;
            try {
                eEjemplartema = em.getReference(eEjemplartema.class, id);
                eEjemplartema.getEjemplartemaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eEjemplartema with id " + id + " no longer exists.", enfe);
            }
            em.remove(eEjemplartema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<eEjemplartema> findeEjemplartemaEntities() {
        return findeEjemplartemaEntities(true, -1, -1);
    }

    public List<eEjemplartema> findeEjemplartemaEntities(int maxResults, int firstResult) {
        return findeEjemplartemaEntities(false, maxResults, firstResult);
    }

    private List<eEjemplartema> findeEjemplartemaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eEjemplartema.class));
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

    public eEjemplartema findeEjemplartema(Integer id) {
        try {
            return em.find(eEjemplartema.class, id);
        } finally {
            em.close();
        }
    }

    public int geteEjemplartemaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eEjemplartema> rt = cq.from(eEjemplartema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
