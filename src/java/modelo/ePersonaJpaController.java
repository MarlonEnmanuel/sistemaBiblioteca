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

    public void create(ePersona ePersona) {
        if (ePersona.getEUsuarioList() == null) {
            ePersona.setEUsuarioList(new ArrayList<eUsuario>());
        }
        if (ePersona.getEPrestamoList() == null) {
            ePersona.setEPrestamoList(new ArrayList<ePrestamo>());
        }
        try {
            em.getTransaction().begin();
            List<eUsuario> attachedEUsuarioList = new ArrayList<eUsuario>();
            for (eUsuario EUsuarioListeUsuarioToAttach : ePersona.getEUsuarioList()) {
                EUsuarioListeUsuarioToAttach = em.getReference(EUsuarioListeUsuarioToAttach.getClass(), EUsuarioListeUsuarioToAttach.getIdusuario());
                attachedEUsuarioList.add(EUsuarioListeUsuarioToAttach);
            }
            ePersona.setEUsuarioList(attachedEUsuarioList);
            List<ePrestamo> attachedEPrestamoList = new ArrayList<ePrestamo>();
            for (ePrestamo EPrestamoListePrestamoToAttach : ePersona.getEPrestamoList()) {
                EPrestamoListePrestamoToAttach = em.getReference(EPrestamoListePrestamoToAttach.getClass(), EPrestamoListePrestamoToAttach.getIdprestamo());
                attachedEPrestamoList.add(EPrestamoListePrestamoToAttach);
            }
            ePersona.setEPrestamoList(attachedEPrestamoList);
            em.persist(ePersona);
            for (eUsuario EUsuarioListeUsuario : ePersona.getEUsuarioList()) {
                ePersona oldIdpersonaOfEUsuarioListeUsuario = EUsuarioListeUsuario.getIdpersona();
                EUsuarioListeUsuario.setIdpersona(ePersona);
                EUsuarioListeUsuario = em.merge(EUsuarioListeUsuario);
                if (oldIdpersonaOfEUsuarioListeUsuario != null) {
                    oldIdpersonaOfEUsuarioListeUsuario.getEUsuarioList().remove(EUsuarioListeUsuario);
                    oldIdpersonaOfEUsuarioListeUsuario = em.merge(oldIdpersonaOfEUsuarioListeUsuario);
                }
            }
            for (ePrestamo EPrestamoListePrestamo : ePersona.getEPrestamoList()) {
                ePersona oldIdpersonaOfEPrestamoListePrestamo = EPrestamoListePrestamo.getIdpersona();
                EPrestamoListePrestamo.setIdpersona(ePersona);
                EPrestamoListePrestamo = em.merge(EPrestamoListePrestamo);
                if (oldIdpersonaOfEPrestamoListePrestamo != null) {
                    oldIdpersonaOfEPrestamoListePrestamo.getEPrestamoList().remove(EPrestamoListePrestamo);
                    oldIdpersonaOfEPrestamoListePrestamo = em.merge(oldIdpersonaOfEPrestamoListePrestamo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ePersona ePersona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            ePersona persistentePersona = em.find(ePersona.class, ePersona.getIdpersona());
            List<eUsuario> EUsuarioListOld = persistentePersona.getEUsuarioList();
            List<eUsuario> EUsuarioListNew = ePersona.getEUsuarioList();
            List<ePrestamo> EPrestamoListOld = persistentePersona.getEPrestamoList();
            List<ePrestamo> EPrestamoListNew = ePersona.getEPrestamoList();
            List<String> illegalOrphanMessages = null;
            for (eUsuario EUsuarioListOldeUsuario : EUsuarioListOld) {
                if (!EUsuarioListNew.contains(EUsuarioListOldeUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain eUsuario " + EUsuarioListOldeUsuario + " since its idpersona field is not nullable.");
                }
            }
            for (ePrestamo EPrestamoListOldePrestamo : EPrestamoListOld) {
                if (!EPrestamoListNew.contains(EPrestamoListOldePrestamo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ePrestamo " + EPrestamoListOldePrestamo + " since its idpersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<eUsuario> attachedEUsuarioListNew = new ArrayList<eUsuario>();
            for (eUsuario EUsuarioListNeweUsuarioToAttach : EUsuarioListNew) {
                EUsuarioListNeweUsuarioToAttach = em.getReference(EUsuarioListNeweUsuarioToAttach.getClass(), EUsuarioListNeweUsuarioToAttach.getIdusuario());
                attachedEUsuarioListNew.add(EUsuarioListNeweUsuarioToAttach);
            }
            EUsuarioListNew = attachedEUsuarioListNew;
            ePersona.setEUsuarioList(EUsuarioListNew);
            List<ePrestamo> attachedEPrestamoListNew = new ArrayList<ePrestamo>();
            for (ePrestamo EPrestamoListNewePrestamoToAttach : EPrestamoListNew) {
                EPrestamoListNewePrestamoToAttach = em.getReference(EPrestamoListNewePrestamoToAttach.getClass(), EPrestamoListNewePrestamoToAttach.getIdprestamo());
                attachedEPrestamoListNew.add(EPrestamoListNewePrestamoToAttach);
            }
            EPrestamoListNew = attachedEPrestamoListNew;
            ePersona.setEPrestamoList(EPrestamoListNew);
            ePersona = em.merge(ePersona);
            for (eUsuario EUsuarioListNeweUsuario : EUsuarioListNew) {
                if (!EUsuarioListOld.contains(EUsuarioListNeweUsuario)) {
                    ePersona oldIdpersonaOfEUsuarioListNeweUsuario = EUsuarioListNeweUsuario.getIdpersona();
                    EUsuarioListNeweUsuario.setIdpersona(ePersona);
                    EUsuarioListNeweUsuario = em.merge(EUsuarioListNeweUsuario);
                    if (oldIdpersonaOfEUsuarioListNeweUsuario != null && !oldIdpersonaOfEUsuarioListNeweUsuario.equals(ePersona)) {
                        oldIdpersonaOfEUsuarioListNeweUsuario.getEUsuarioList().remove(EUsuarioListNeweUsuario);
                        oldIdpersonaOfEUsuarioListNeweUsuario = em.merge(oldIdpersonaOfEUsuarioListNeweUsuario);
                    }
                }
            }
            for (ePrestamo EPrestamoListNewePrestamo : EPrestamoListNew) {
                if (!EPrestamoListOld.contains(EPrestamoListNewePrestamo)) {
                    ePersona oldIdpersonaOfEPrestamoListNewePrestamo = EPrestamoListNewePrestamo.getIdpersona();
                    EPrestamoListNewePrestamo.setIdpersona(ePersona);
                    EPrestamoListNewePrestamo = em.merge(EPrestamoListNewePrestamo);
                    if (oldIdpersonaOfEPrestamoListNewePrestamo != null && !oldIdpersonaOfEPrestamoListNewePrestamo.equals(ePersona)) {
                        oldIdpersonaOfEPrestamoListNewePrestamo.getEPrestamoList().remove(EPrestamoListNewePrestamo);
                        oldIdpersonaOfEPrestamoListNewePrestamo = em.merge(oldIdpersonaOfEPrestamoListNewePrestamo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ePersona.getIdpersona();
                if (findePersona(id) == null) {
                    throw new NonexistentEntityException("The ePersona with id " + id + " no longer exists.");
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
            ePersona ePersona;
            try {
                ePersona = em.getReference(ePersona.class, id);
                ePersona.getIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ePersona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<eUsuario> EUsuarioListOrphanCheck = ePersona.getEUsuarioList();
            for (eUsuario EUsuarioListOrphanCheckeUsuario : EUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ePersona (" + ePersona + ") cannot be destroyed since the eUsuario " + EUsuarioListOrphanCheckeUsuario + " in its EUsuarioList field has a non-nullable idpersona field.");
            }
            List<ePrestamo> EPrestamoListOrphanCheck = ePersona.getEPrestamoList();
            for (ePrestamo EPrestamoListOrphanCheckePrestamo : EPrestamoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ePersona (" + ePersona + ") cannot be destroyed since the ePrestamo " + EPrestamoListOrphanCheckePrestamo + " in its EPrestamoList field has a non-nullable idpersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ePersona);
            em.getTransaction().commit();
        } finally {
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
