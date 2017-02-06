/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCategoria;
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
public class eCategoriaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eCategoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }


    public void create(eCategoria eCategoria) {
        try {
            em.getTransaction().begin();
            em.persist(eCategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eCategoria eCategoria) throws NonexistentEntityException, Exception {
        try{
            em.getTransaction().begin();
            eCategoria = em.merge(eCategoria);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eCategoria.getCategoriaId();
                if (findeCategoria(id) == null) {
                    throw new NonexistentEntityException("The eCategoria with id " + id + " no longer exists.");
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
            eCategoria eCategoria;
            try {
                eCategoria = em.getReference(eCategoria.class, id);
                eCategoria.getCategoriaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eCategoria with id " + id + " no longer exists.", enfe);
            }
            em.remove(eCategoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<eCategoria> findeCategoriaEntities() {
        return findeCategoriaEntities(true, -1, -1);
    }

    public List<eCategoria> findeCategoriaEntities(int maxResults, int firstResult) {
        return findeCategoriaEntities(false, maxResults, firstResult);
    }

    private List<eCategoria> findeCategoriaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eCategoria.class));
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

    public eCategoria findeCategoria(Integer id) {
        try {
            return em.find(eCategoria.class, id);
        } finally {
            em.close();
        }
    }

    public int geteCategoriaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eCategoria> rt = cq.from(eCategoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
