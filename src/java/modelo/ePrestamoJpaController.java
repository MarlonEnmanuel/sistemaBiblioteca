/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.ePersona;
import entidad.eCopia;
import entidad.ePrestamo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
 

    public boolean create(ePrestamo ePrestamo) {
        try {
            em.getTransaction().begin();
            em.persist(ePrestamo);
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

    public boolean edit(ePrestamo ePrestamo) throws NonexistentEntityException, Exception {
        try {
            ePrestamo persistentePrestamo = em.find(ePrestamo.class, ePrestamo.getIdprestamo());
            em.getTransaction().begin();
            persistentePrestamo.setCodigo(ePrestamo.getCodigo());
            persistentePrestamo.setDevuelto(ePrestamo.getDevuelto());
            persistentePrestamo.setFechadev(ePrestamo.getFechadev());
            persistentePrestamo.setFechafin(ePrestamo.getFechafin());
            persistentePrestamo.setFechaini(ePrestamo.getFechaini());
            persistentePrestamo.setFechareg(ePrestamo.getFechareg());
            persistentePrestamo.setIdcopia(ePrestamo.getIdcopia());
            persistentePrestamo.setIdpersona(ePrestamo.getIdpersona());
            persistentePrestamo.setIdprestamo(ePrestamo.getIdprestamo());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean actualiza(ePrestamo ePrestamo)throws NonexistentEntityException, Exception{
        try {
            ePrestamo persistentePrestamo = em.find(ePrestamo.class, ePrestamo.getIdprestamo());
            em.getTransaction().begin();
            persistentePrestamo.setCodigo(ePrestamo.getCodigo());
            persistentePrestamo.setDevuelto(ePrestamo.getDevuelto());
            persistentePrestamo.setFechadev(ePrestamo.getFechadev());
            persistentePrestamo.setFechafin(ePrestamo.getFechafin());
            persistentePrestamo.setFechaini(ePrestamo.getFechaini());
            persistentePrestamo.setFechareg(ePrestamo.getFechareg());
            persistentePrestamo.setIdcopia(ePrestamo.getIdcopia());
            persistentePrestamo.setIdpersona(ePrestamo.getIdpersona());
            persistentePrestamo.setIdprestamo(ePrestamo.getIdprestamo());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public void destroy(Integer id) throws NonexistentEntityException {
        try {
            em.getTransaction().begin();
            ePrestamo ePrestamo;
            try {
                ePrestamo = em.getReference(ePrestamo.class, id);
                ePrestamo.getIdprestamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ePrestamo with id " + id + " no longer exists.", enfe);
            }
            ePersona idpersona = ePrestamo.getIdpersona();
            if (idpersona != null) {
                idpersona.getEPrestamoList().remove(ePrestamo);
                idpersona = em.merge(idpersona);
            }
            eCopia idcopia = ePrestamo.getIdcopia();
            if (idcopia != null) {
                idcopia.getEPrestamoList().remove(ePrestamo);
                idcopia = em.merge(idcopia);
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
            //em.close();
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
