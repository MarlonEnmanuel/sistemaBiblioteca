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
 

    public void create(ePrestamo ePrestamo) {
        try {
            em.getTransaction().begin();
            ePersona idpersona = ePrestamo.getIdpersona();
            if (idpersona != null) {
                idpersona = em.getReference(idpersona.getClass(), idpersona.getIdpersona());
                ePrestamo.setIdpersona(idpersona);
            }
            eCopia idcopia = ePrestamo.getIdcopia();
            if (idcopia != null) {
                idcopia = em.getReference(idcopia.getClass(), idcopia.getIdcopia());
                ePrestamo.setIdcopia(idcopia);
            }
            em.persist(ePrestamo);
            if (idpersona != null) {
                idpersona.getEPrestamoList().add(ePrestamo);
                idpersona = em.merge(idpersona);
            }
            if (idcopia != null) {
                idcopia.getEPrestamoList().add(ePrestamo);
                idcopia = em.merge(idcopia);
            }
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
            ePrestamo persistentePrestamo = em.find(ePrestamo.class, ePrestamo.getIdprestamo());
            ePersona idpersonaOld = persistentePrestamo.getIdpersona();
            ePersona idpersonaNew = ePrestamo.getIdpersona();
            eCopia idcopiaOld = persistentePrestamo.getIdcopia();
            eCopia idcopiaNew = ePrestamo.getIdcopia();
            if (idpersonaNew != null) {
                idpersonaNew = em.getReference(idpersonaNew.getClass(), idpersonaNew.getIdpersona());
                ePrestamo.setIdpersona(idpersonaNew);
            }
            if (idcopiaNew != null) {
                idcopiaNew = em.getReference(idcopiaNew.getClass(), idcopiaNew.getIdcopia());
                ePrestamo.setIdcopia(idcopiaNew);
            }
            ePrestamo = em.merge(ePrestamo);
            if (idpersonaOld != null && !idpersonaOld.equals(idpersonaNew)) {
                idpersonaOld.getEPrestamoList().remove(ePrestamo);
                idpersonaOld = em.merge(idpersonaOld);
            }
            if (idpersonaNew != null && !idpersonaNew.equals(idpersonaOld)) {
                idpersonaNew.getEPrestamoList().add(ePrestamo);
                idpersonaNew = em.merge(idpersonaNew);
            }
            if (idcopiaOld != null && !idcopiaOld.equals(idcopiaNew)) {
                idcopiaOld.getEPrestamoList().remove(ePrestamo);
                idcopiaOld = em.merge(idcopiaOld);
            }
            if (idcopiaNew != null && !idcopiaNew.equals(idcopiaOld)) {
                idcopiaNew.getEPrestamoList().add(ePrestamo);
                idcopiaNew = em.merge(idcopiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ePrestamo.getIdprestamo();
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
