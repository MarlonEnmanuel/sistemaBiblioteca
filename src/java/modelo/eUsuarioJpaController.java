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
import entidad.eUsuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    public boolean create(eUsuario eUsuario) {
        try {
            em.getTransaction().begin();
            em.persist(eUsuario);
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

    public boolean actualiza(eUsuario eUsuario) throws NonexistentEntityException, Exception{
        try {
            eUsuario persistenteUsuario=em.find(eUsuario.class, eUsuario.getIdusuario());
            em.getTransaction().begin();
            persistenteUsuario.setEstado(eUsuario.getEstado());
            persistenteUsuario.setIdpersona(eUsuario.getIdpersona());
            persistenteUsuario.setPass(eUsuario.getPass());
            persistenteUsuario.setTipo(eUsuario.getTipo());
            persistenteUsuario.setUser(eUsuario.getUser());
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean destroy(eUsuario eUsuario) throws NonexistentEntityException {
        try {
            eUsuario persistenteUsuario=em.find(eUsuario.class, eUsuario.getIdusuario());
            em.getTransaction().begin();
            em.remove(persistenteUsuario);
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
