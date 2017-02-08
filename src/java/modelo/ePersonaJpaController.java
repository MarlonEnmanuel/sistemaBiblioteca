/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.ePersona;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.eUsuario;
import java.util.ArrayList;
import java.util.List;
import entidad.ePrestamo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.exceptions.IllegalOrphanException;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author MiguelSc
 */
public class ePersonaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public ePersonaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public boolean create(ePersona ePersona) {
        try {
            em.getTransaction().begin();
            em.persist(ePersona);
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

    public boolean actualiza(ePersona ePersona) throws NonexistentEntityException, Exception{
        try {
            ePersona persistentePersona = em.find(ePersona.class, ePersona.getIdpersona());
            em.getTransaction().begin();
            persistentePersona.setApellidos(ePersona.getApellidos());
            persistentePersona.setCodigo(ePersona.getCodigo());
            persistentePersona.setEscuela(ePersona.getEscuela());
            persistentePersona.setFacultad(ePersona.getFacultad());
            persistentePersona.setNombres(ePersona.getNombres());
            persistentePersona.setTipo(ePersona.getTipo());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean destroy(ePersona ePersona) throws IllegalOrphanException, NonexistentEntityException {
        try {
            ePersona persistentePersona = em.find(ePersona.class, ePersona.getIdpersona());
            em.getTransaction().begin();
            em.remove(persistentePersona);
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

    public List<ePersona> findePersonaEntities() {
        return findePersonaEntities(true, -1, -1);
    }

    public List<ePersona> findePersonaEntities(int maxResults, int firstResult) {
        return findePersonaEntities(false, maxResults, firstResult);
    }

    private List<ePersona> findePersonaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ePersona.class));
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

    public ePersona findePersona(Integer id) {
        try {
            return em.find(ePersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getePersonaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ePersona> rt = cq.from(ePersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
