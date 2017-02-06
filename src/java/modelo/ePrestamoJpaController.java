/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.ePrestamo;
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
public class ePrestamoJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public ePrestamoJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public void create(ePrestamo ePrestamo) {
        try {
            em.getTransaction().begin();
            em.persist(ePrestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ePrestamo ePrestamo) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            ePrestamo = em.merge(ePrestamo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ePrestamo.getPrestamoId();
                if (findePrestamo(id) == null) {
                    throw new NonexistentEntityException("The ePrestamo with id " + id + " no longer exists.");
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
            ePrestamo ePrestamo;
            try {
                ePrestamo = em.getReference(ePrestamo.class, id);
                ePrestamo.getPrestamoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ePrestamo with id " + id + " no longer exists.", enfe);
            }
            em.remove(ePrestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ePrestamo> findePrestamoEntities() {
        return findePrestamoEntities(true, -1, -1);
    }

    public List<ePrestamo> findePrestamoEntities(int maxResults, int firstResult) {
        return findePrestamoEntities(false, maxResults, firstResult);
    }

    private List<ePrestamo> findePrestamoEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ePrestamo.class));
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

    public ePrestamo findePrestamo(Integer id) {
        try {
            return em.find(ePrestamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getePrestamoCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ePrestamo> rt = cq.from(ePrestamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
