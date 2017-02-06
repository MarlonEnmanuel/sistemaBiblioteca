/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eUsuario;
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
public class eUsuarioJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eUsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public void create(eUsuario eUsuario) {
        try {
            em.getTransaction().begin();
            em.persist(eUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eUsuario eUsuario) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eUsuario = em.merge(eUsuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eUsuario.getUsuarioId();
                if (findeUsuario(id) == null) {
                    throw new NonexistentEntityException("The eUsuario with id " + id + " no longer exists.");
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
            eUsuario eUsuario;
            try {
                eUsuario = em.getReference(eUsuario.class, id);
                eUsuario.getUsuarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eUsuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(eUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<eUsuario> findeUsuarioEntities() {
        return findeUsuarioEntities(true, -1, -1);
    }

    public List<eUsuario> findeUsuarioEntities(int maxResults, int firstResult) {
        return findeUsuarioEntities(false, maxResults, firstResult);
    }

    private List<eUsuario> findeUsuarioEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eUsuario.class));
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

    public eUsuario findeUsuario(Integer id) {
        try {
            return em.find(eUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int geteUsuarioCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eUsuario> rt = cq.from(eUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
