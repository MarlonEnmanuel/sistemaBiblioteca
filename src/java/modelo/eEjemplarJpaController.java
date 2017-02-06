/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eEjemplar;
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
public class eEjemplarJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eEjemplarJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public void create(eEjemplar eEjemplar) {
        try {
            em.getTransaction().begin();
            em.persist(eEjemplar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eEjemplar eEjemplar) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eEjemplar = em.merge(eEjemplar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eEjemplar.getEjemplarId();
                if (findeEjemplar(id) == null) {
                    throw new NonexistentEntityException("The eEjemplar with id " + id + " no longer exists.");
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
            eEjemplar eEjemplar;
            try {
                eEjemplar = em.getReference(eEjemplar.class, id);
                eEjemplar.getEjemplarId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eEjemplar with id " + id + " no longer exists.", enfe);
            }
            em.remove(eEjemplar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<eEjemplar> findeEjemplarEntities() {
        return findeEjemplarEntities(true, -1, -1);
    }

    public List<eEjemplar> findeEjemplarEntities(int maxResults, int firstResult) {
        return findeEjemplarEntities(false, maxResults, firstResult);
    }

    private List<eEjemplar> findeEjemplarEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eEjemplar.class));
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

    public eEjemplar findeEjemplar(Integer id) {
        try {
            return em.find(eEjemplar.class, id);
        } finally {
            em.close();
        }
    }

    public int geteEjemplarCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eEjemplar> rt = cq.from(eEjemplar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
