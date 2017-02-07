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
import entidad.eEjemplar;
import entidad.eTema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.exceptions.NonexistentEntityException;

/**
 *
 * @author MiguelSc
 */
public class eTemaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eTemaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }

    public void create(eTema eTema) {
        if (eTema.getEEjemplarList() == null) {
            eTema.setEEjemplarList(new ArrayList<eEjemplar>());
        }
        try {
            em.getTransaction().begin();
            List<eEjemplar> attachedEEjemplarList = new ArrayList<eEjemplar>();
            for (eEjemplar EEjemplarListeEjemplarToAttach : eTema.getEEjemplarList()) {
                EEjemplarListeEjemplarToAttach = em.getReference(EEjemplarListeEjemplarToAttach.getClass(), EEjemplarListeEjemplarToAttach.getIdejemplar());
                attachedEEjemplarList.add(EEjemplarListeEjemplarToAttach);
            }
            eTema.setEEjemplarList(attachedEEjemplarList);
            em.persist(eTema);
            for (eEjemplar EEjemplarListeEjemplar : eTema.getEEjemplarList()) {
                EEjemplarListeEjemplar.getETemaList().add(eTema);
                EEjemplarListeEjemplar = em.merge(EEjemplarListeEjemplar);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eTema eTema) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eTema persistenteTema = em.find(eTema.class, eTema.getIdtema());
            List<eEjemplar> EEjemplarListOld = persistenteTema.getEEjemplarList();
            List<eEjemplar> EEjemplarListNew = eTema.getEEjemplarList();
            List<eEjemplar> attachedEEjemplarListNew = new ArrayList<eEjemplar>();
            for (eEjemplar EEjemplarListNeweEjemplarToAttach : EEjemplarListNew) {
                EEjemplarListNeweEjemplarToAttach = em.getReference(EEjemplarListNeweEjemplarToAttach.getClass(), EEjemplarListNeweEjemplarToAttach.getIdejemplar());
                attachedEEjemplarListNew.add(EEjemplarListNeweEjemplarToAttach);
            }
            EEjemplarListNew = attachedEEjemplarListNew;
            eTema.setEEjemplarList(EEjemplarListNew);
            eTema = em.merge(eTema);
            for (eEjemplar EEjemplarListOldeEjemplar : EEjemplarListOld) {
                if (!EEjemplarListNew.contains(EEjemplarListOldeEjemplar)) {
                    EEjemplarListOldeEjemplar.getETemaList().remove(eTema);
                    EEjemplarListOldeEjemplar = em.merge(EEjemplarListOldeEjemplar);
                }
            }
            for (eEjemplar EEjemplarListNeweEjemplar : EEjemplarListNew) {
                if (!EEjemplarListOld.contains(EEjemplarListNeweEjemplar)) {
                    EEjemplarListNeweEjemplar.getETemaList().add(eTema);
                    EEjemplarListNeweEjemplar = em.merge(EEjemplarListNeweEjemplar);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eTema.getIdtema();
                if (findeTema(id) == null) {
                    throw new NonexistentEntityException("The eTema with id " + id + " no longer exists.");
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
            eTema eTema;
            try {
                eTema = em.getReference(eTema.class, id);
                eTema.getIdtema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eTema with id " + id + " no longer exists.", enfe);
            }
            List<eEjemplar> EEjemplarList = eTema.getEEjemplarList();
            for (eEjemplar EEjemplarListeEjemplar : EEjemplarList) {
                EEjemplarListeEjemplar.getETemaList().remove(eTema);
                EEjemplarListeEjemplar = em.merge(EEjemplarListeEjemplar);
            }
            em.remove(eTema);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<eTema> findeTemaEntities() {
        return findeTemaEntities(true, -1, -1);
    }

    public List<eTema> findeTemaEntities(int maxResults, int firstResult) {
        return findeTemaEntities(false, maxResults, firstResult);
    }

    private List<eTema> findeTemaEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(eTema.class));
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

    public eTema findeTema(Integer id) {
        try {
            return em.find(eTema.class, id);
        } finally {
            em.close();
        }
    }

    public int geteTemaCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<eTema> rt = cq.from(eTema.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
