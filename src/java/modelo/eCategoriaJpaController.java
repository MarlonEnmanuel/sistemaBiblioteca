/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCategoria;
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
public class eCategoriaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eCategoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }


    public boolean create(eCategoria eCategoria) {
        try {
            em.getTransaction().begin();
            em.persist(eCategoria);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean destroy(eCategoria eCategoria) throws IllegalOrphanException, NonexistentEntityException {
        try {
            eCategoria persistenteCategoria = em.find(eCategoria.class, eCategoria.getIdcategoria());
            em.getTransaction().begin();
            em.remove(persistenteCategoria);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            return false;
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
    
    public boolean actualiza(eCategoria eCategoria) throws NonexistentEntityException, Exception{
        try {
            eCategoria persistenteCategoria = em.find(eCategoria.class, eCategoria.getIdcategoria());
            em.getTransaction().begin();
            persistenteCategoria.setDatos(eCategoria.getDatos());
            persistenteCategoria.setDescripcion(eCategoria.getDescripcion());
            persistenteCategoria.setNombre(eCategoria.getNombre());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            
            return false;
        }
    }
    
}
