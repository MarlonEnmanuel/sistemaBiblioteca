/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import entidad.eCopia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidad.eEjemplar;
import entidad.ePrestamo;
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
public class eCopiaJpaController implements Serializable {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    
    public eCopiaJpaController() {
        emf = Persistence.createEntityManagerFactory("sistemaBiblioteca2017");
        em = emf.createEntityManager();
    }


    public void create(eCopia eCopia) {
        if (eCopia.getEPrestamoList() == null) {
            eCopia.setEPrestamoList(new ArrayList<ePrestamo>());
        }
        try {
            em.getTransaction().begin();
            eEjemplar idejemplar = eCopia.getIdejemplar();
            if (idejemplar != null) {
                idejemplar = em.getReference(idejemplar.getClass(), idejemplar.getIdejemplar());
                eCopia.setIdejemplar(idejemplar);
            }
            List<ePrestamo> attachedEPrestamoList = new ArrayList<ePrestamo>();
            for (ePrestamo EPrestamoListePrestamoToAttach : eCopia.getEPrestamoList()) {
                EPrestamoListePrestamoToAttach = em.getReference(EPrestamoListePrestamoToAttach.getClass(), EPrestamoListePrestamoToAttach.getIdprestamo());
                attachedEPrestamoList.add(EPrestamoListePrestamoToAttach);
            }
            eCopia.setEPrestamoList(attachedEPrestamoList);
            em.persist(eCopia);
            if (idejemplar != null) {
                idejemplar.getECopiaList().add(eCopia);
                idejemplar = em.merge(idejemplar);
            }
            for (ePrestamo EPrestamoListePrestamo : eCopia.getEPrestamoList()) {
                eCopia oldIdcopiaOfEPrestamoListePrestamo = EPrestamoListePrestamo.getIdcopia();
                EPrestamoListePrestamo.setIdcopia(eCopia);
                EPrestamoListePrestamo = em.merge(EPrestamoListePrestamo);
                if (oldIdcopiaOfEPrestamoListePrestamo != null) {
                    oldIdcopiaOfEPrestamoListePrestamo.getEPrestamoList().remove(EPrestamoListePrestamo);
                    oldIdcopiaOfEPrestamoListePrestamo = em.merge(oldIdcopiaOfEPrestamoListePrestamo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eCopia eCopia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eCopia persistenteCopia = em.find(eCopia.class, eCopia.getIdcopia());
            eEjemplar idejemplarOld = persistenteCopia.getIdejemplar();
            eEjemplar idejemplarNew = eCopia.getIdejemplar();
            List<ePrestamo> EPrestamoListOld = persistenteCopia.getEPrestamoList();
            List<ePrestamo> EPrestamoListNew = eCopia.getEPrestamoList();
            List<String> illegalOrphanMessages = null;
            for (ePrestamo EPrestamoListOldePrestamo : EPrestamoListOld) {
                if (!EPrestamoListNew.contains(EPrestamoListOldePrestamo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ePrestamo " + EPrestamoListOldePrestamo + " since its idcopia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idejemplarNew != null) {
                idejemplarNew = em.getReference(idejemplarNew.getClass(), idejemplarNew.getIdejemplar());
                eCopia.setIdejemplar(idejemplarNew);
            }
            List<ePrestamo> attachedEPrestamoListNew = new ArrayList<ePrestamo>();
            for (ePrestamo EPrestamoListNewePrestamoToAttach : EPrestamoListNew) {
                EPrestamoListNewePrestamoToAttach = em.getReference(EPrestamoListNewePrestamoToAttach.getClass(), EPrestamoListNewePrestamoToAttach.getIdprestamo());
                attachedEPrestamoListNew.add(EPrestamoListNewePrestamoToAttach);
            }
            EPrestamoListNew = attachedEPrestamoListNew;
            eCopia.setEPrestamoList(EPrestamoListNew);
            eCopia = em.merge(eCopia);
            if (idejemplarOld != null && !idejemplarOld.equals(idejemplarNew)) {
                idejemplarOld.getECopiaList().remove(eCopia);
                idejemplarOld = em.merge(idejemplarOld);
            }
            if (idejemplarNew != null && !idejemplarNew.equals(idejemplarOld)) {
                idejemplarNew.getECopiaList().add(eCopia);
                idejemplarNew = em.merge(idejemplarNew);
            }
            for (ePrestamo EPrestamoListNewePrestamo : EPrestamoListNew) {
                if (!EPrestamoListOld.contains(EPrestamoListNewePrestamo)) {
                    eCopia oldIdcopiaOfEPrestamoListNewePrestamo = EPrestamoListNewePrestamo.getIdcopia();
                    EPrestamoListNewePrestamo.setIdcopia(eCopia);
                    EPrestamoListNewePrestamo = em.merge(EPrestamoListNewePrestamo);
                    if (oldIdcopiaOfEPrestamoListNewePrestamo != null && !oldIdcopiaOfEPrestamoListNewePrestamo.equals(eCopia)) {
                        oldIdcopiaOfEPrestamoListNewePrestamo.getEPrestamoList().remove(EPrestamoListNewePrestamo);
                        oldIdcopiaOfEPrestamoListNewePrestamo = em.merge(oldIdcopiaOfEPrestamoListNewePrestamo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eCopia.getIdcopia();
                if (findeCopia(id) == null) {
                    throw new NonexistentEntityException("The eCopia with id " + id + " no longer exists.");
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
        try {
            em.getTransaction().begin();
            eCopia eCopia;
            try {
                eCopia = em.getReference(eCopia.class, id);
                eCopia.getIdcopia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eCopia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ePrestamo> EPrestamoListOrphanCheck = eCopia.getEPrestamoList();
            for (ePrestamo EPrestamoListOrphanCheckePrestamo : EPrestamoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This eCopia (" + eCopia + ") cannot be destroyed since the ePrestamo " + EPrestamoListOrphanCheckePrestamo + " in its EPrestamoList field has a non-nullable idcopia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            eEjemplar idejemplar = eCopia.getIdejemplar();
            if (idejemplar != null) {
                idejemplar.getECopiaList().remove(eCopia);
                idejemplar = em.merge(idejemplar);
            }
            em.remove(eCopia);
            em.getTransaction().commit();
        } finally {
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
