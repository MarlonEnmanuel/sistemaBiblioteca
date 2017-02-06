/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eTema;
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
public class eTemaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eTemaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public void create(eTema eTema) {
        try {
            em.getTransaction().begin();
            em.persist(eTema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eTema eTema) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eTema = em.merge(eTema);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eTema.getTemaId();
                if (findeTema(id) == null) {
                    throw new NonexistentEntityException("The eTema with id " + id + " no longer exists.");
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
            eTema eTema;
            try {
                eTema = em.getReference(eTema.class, id);
                eTema.getTemaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eTema with id " + id + " no longer exists.", enfe);
            }
            em.remove(eTema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
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
