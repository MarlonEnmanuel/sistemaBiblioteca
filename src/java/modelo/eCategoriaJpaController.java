/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCategoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.eEjemplar;
import java.util.ArrayList;
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


    public void create(eCategoria eCategoria) {
        if (eCategoria.getEEjemplarList() == null) {
            eCategoria.setEEjemplarList(new ArrayList<eEjemplar>());
        }
        EntityManager em = null;
        try {
            em.getTransaction().begin();
            List<eEjemplar> attachedEEjemplarList = new ArrayList<eEjemplar>();
            for (eEjemplar EEjemplarListeEjemplarToAttach : eCategoria.getEEjemplarList()) {
                EEjemplarListeEjemplarToAttach = em.getReference(EEjemplarListeEjemplarToAttach.getClass(), EEjemplarListeEjemplarToAttach.getIdejemplar());
                attachedEEjemplarList.add(EEjemplarListeEjemplarToAttach);
            }
            eCategoria.setEEjemplarList(attachedEEjemplarList);
            em.persist(eCategoria);
            for (eEjemplar EEjemplarListeEjemplar : eCategoria.getEEjemplarList()) {
                eCategoria oldIdcategoriaOfEEjemplarListeEjemplar = EEjemplarListeEjemplar.getIdcategoria();
                EEjemplarListeEjemplar.setIdcategoria(eCategoria);
                EEjemplarListeEjemplar = em.merge(EEjemplarListeEjemplar);
                if (oldIdcategoriaOfEEjemplarListeEjemplar != null) {
                    oldIdcategoriaOfEEjemplarListeEjemplar.getEEjemplarList().remove(EEjemplarListeEjemplar);
                    oldIdcategoriaOfEEjemplarListeEjemplar = em.merge(oldIdcategoriaOfEEjemplarListeEjemplar);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eCategoria eCategoria) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em.getTransaction().begin();
            eCategoria persistenteCategoria = em.find(eCategoria.class, eCategoria.getIdcategoria());
            List<eEjemplar> EEjemplarListOld = persistenteCategoria.getEEjemplarList();
            List<eEjemplar> EEjemplarListNew = eCategoria.getEEjemplarList();
            List<String> illegalOrphanMessages = null;
            for (eEjemplar EEjemplarListOldeEjemplar : EEjemplarListOld) {
                if (!EEjemplarListNew.contains(EEjemplarListOldeEjemplar)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain eEjemplar " + EEjemplarListOldeEjemplar + " since its idcategoria field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<eEjemplar> attachedEEjemplarListNew = new ArrayList<eEjemplar>();
            for (eEjemplar EEjemplarListNeweEjemplarToAttach : EEjemplarListNew) {
                EEjemplarListNeweEjemplarToAttach = em.getReference(EEjemplarListNeweEjemplarToAttach.getClass(), EEjemplarListNeweEjemplarToAttach.getIdejemplar());
                attachedEEjemplarListNew.add(EEjemplarListNeweEjemplarToAttach);
            }
            EEjemplarListNew = attachedEEjemplarListNew;
            eCategoria.setEEjemplarList(EEjemplarListNew);
            eCategoria = em.merge(eCategoria);
            for (eEjemplar EEjemplarListNeweEjemplar : EEjemplarListNew) {
                if (!EEjemplarListOld.contains(EEjemplarListNeweEjemplar)) {
                    eCategoria oldIdcategoriaOfEEjemplarListNeweEjemplar = EEjemplarListNeweEjemplar.getIdcategoria();
                    EEjemplarListNeweEjemplar.setIdcategoria(eCategoria);
                    EEjemplarListNeweEjemplar = em.merge(EEjemplarListNeweEjemplar);
                    if (oldIdcategoriaOfEEjemplarListNeweEjemplar != null && !oldIdcategoriaOfEEjemplarListNeweEjemplar.equals(eCategoria)) {
                        oldIdcategoriaOfEEjemplarListNeweEjemplar.getEEjemplarList().remove(EEjemplarListNeweEjemplar);
                        oldIdcategoriaOfEEjemplarListNeweEjemplar = em.merge(oldIdcategoriaOfEEjemplarListNeweEjemplar);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eCategoria.getIdcategoria();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em.getTransaction().begin();
            eCategoria eCategoria;
            try {
                eCategoria = em.getReference(eCategoria.class, id);
                eCategoria.getIdcategoria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eCategoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<eEjemplar> EEjemplarListOrphanCheck = eCategoria.getEEjemplarList();
            for (eEjemplar EEjemplarListOrphanCheckeEjemplar : EEjemplarListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This eCategoria (" + eCategoria + ") cannot be destroyed since the eEjemplar " + EEjemplarListOrphanCheckeEjemplar + " in its EEjemplarList field has a non-nullable idcategoria field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
