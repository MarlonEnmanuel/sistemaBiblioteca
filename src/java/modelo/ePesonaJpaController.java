/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.ePesona;
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
public class ePesonaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public ePesonaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }
    
    public void create(ePesona ePesona) {
        try {
            em.getTransaction().begin();
            em.persist(ePesona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ePesona ePesona) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            ePesona = em.merge(ePesona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ePesona.getPersonaId();
                if (findePesona(id) == null) {
                    throw new NonexistentEntityException("The ePesona with id " + id + " no longer exists.");
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
            ePesona ePesona;
            try {
                ePesona = em.getReference(ePesona.class, id);
                ePesona.getPersonaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ePesona with id " + id + " no longer exists.", enfe);
            }
            em.remove(ePesona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ePesona> findePesonaEntities() {
        return findePesonaEntities(true, -1, -1);
    }

    public List<ePesona> findePesonaEntities(int maxResults, int firstResult) {
        return findePesonaEntities(false, maxResults, firstResult);
    }

    private List<ePesona> findePesonaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ePesona.class));
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

    public ePesona findePesona(Integer id) {
        try {
            return em.find(ePesona.class, id);
        } finally {
            em.close();
        }
    }

    public int getePesonaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ePesona> rt = cq.from(ePesona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
