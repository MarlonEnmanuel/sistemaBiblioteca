/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCopia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.exceptions.IllegalOrphanException;
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


    public boolean create(eCopia eCopia) {
        try {
            //eCopia persistenteCopia = em.find(eCopia.class, eCopia.getIdcopia());
            em.getTransaction().begin();
            em.persist(eCopia);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public boolean actualiza(eCopia eCopia) throws NonexistentEntityException, Exception{
        try {
            eCopia persistenteCopia = em.find(eCopia.class, eCopia.getIdcopia());
            em.getTransaction().begin();
            persistenteCopia.setCodigo(eCopia.getCodigo());
            persistenteCopia.setEstado(eCopia.getEstado());
            persistenteCopia.setDisponible(eCopia.getDisponible());
            persistenteCopia.setIdejemplar(eCopia.getIdejemplar());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean destroy(eCopia eCopia) throws IllegalOrphanException, NonexistentEntityException {
        try {
            eCopia persistenteCopia = em.find(eCopia.class, eCopia.getIdcopia());
            em.getTransaction().begin();
            em.remove(persistenteCopia);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            return false;
        }finally {
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
