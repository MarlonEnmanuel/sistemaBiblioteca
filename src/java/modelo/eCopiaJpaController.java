/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCopia;
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
public class eCopiaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eCopiaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }


    public void create(eCopia eCopia) {
        try {
            em.getTransaction().begin();
            em.persist(eCopia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eCopia eCopia) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eCopia = em.merge(eCopia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eCopia.getCopiaId();
                if (findeCopia(id) == null) {
                    throw new NonexistentEntityException("The eCopia with id " + id + " no longer exists.");
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
            eCopia eCopia;
            try {
                eCopia = em.getReference(eCopia.class, id);
                eCopia.getCopiaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eCopia with id " + id + " no longer exists.", enfe);
            }
            em.remove(eCopia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<eCopia> findeCopiaEntities() {
        return findeCopiaEntities(true, -1, -1);
    }

    public List<eCopia> findeCopiaEntities(int maxResults, int firstResult) {
        return findeCopiaEntities(false, maxResults, firstResult);
    }

    private List<eCopia> findeCopiaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eCopia.class));
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

    public eCopia findeCopia(Integer id) {
        try {
            return em.find(eCopia.class, id);
        } finally {
            em.close();
        }
    }

    public int geteCopiaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eCopia> rt = cq.from(eCopia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
