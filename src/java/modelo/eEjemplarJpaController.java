/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import entidad.eEjemplar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.exceptions.IllegalOrphanException;
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

    public boolean create(eEjemplar eEjemplar) {
        try {
            em.getTransaction().begin();
            em.persist(eEjemplar);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

     public boolean actualiza(eEjemplar eEjemplar) throws NonexistentEntityException, Exception{
        try {
            eEjemplar persistenteEjemplar=em.find(eEjemplar.class,eEjemplar.getIdejemplar());
            em.getTransaction().begin();
            persistenteEjemplar.setAutores(eEjemplar.getAutores());
            persistenteEjemplar.setCodigo(eEjemplar.getCodigo());
            persistenteEjemplar.setIdejemplar(eEjemplar.getIdejemplar());
            persistenteEjemplar.setPublicacion(eEjemplar.getPublicacion());
            persistenteEjemplar.setTitulo(eEjemplar.getTitulo());
            persistenteEjemplar.getIdcategoria().setIdcategoria(eEjemplar.getIdcategoria().getIdcategoria());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            
            return false;
        }
    }
     
    public boolean destroy(eEjemplar eEjemplar) throws IllegalOrphanException, NonexistentEntityException {
        try {
            eEjemplar persistenteEjemplar=em.find(eEjemplar.class,eEjemplar.getIdejemplar());
            em.getTransaction().begin();
            em.remove(persistenteEjemplar);
            em.getTransaction().commit();
            return true;
        } catch(Exception e){
            return false;
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
