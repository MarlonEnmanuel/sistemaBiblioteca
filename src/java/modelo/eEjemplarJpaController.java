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
import entidad.eCategoria;
import entidad.eTema;
import java.util.ArrayList;
import java.util.List;
import entidad.eCopia;
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

    public void create(eEjemplar eEjemplar) {
        if (eEjemplar.getETemaList() == null) {
            eEjemplar.setETemaList(new ArrayList<eTema>());
        }
        if (eEjemplar.getECopiaList() == null) {
            eEjemplar.setECopiaList(new ArrayList<eCopia>());
        }
        try {
            em.getTransaction().begin();
            eCategoria idcategoria = eEjemplar.getIdcategoria();
            if (idcategoria != null) {
                idcategoria = em.getReference(idcategoria.getClass(), idcategoria.getIdcategoria());
                eEjemplar.setIdcategoria(idcategoria);
            }
            List<eTema> attachedETemaList = new ArrayList<eTema>();
            for (eTema ETemaListeTemaToAttach : eEjemplar.getETemaList()) {
                ETemaListeTemaToAttach = em.getReference(ETemaListeTemaToAttach.getClass(), ETemaListeTemaToAttach.getIdtema());
                attachedETemaList.add(ETemaListeTemaToAttach);
            }
            eEjemplar.setETemaList(attachedETemaList);
            List<eCopia> attachedECopiaList = new ArrayList<eCopia>();
            for (eCopia ECopiaListeCopiaToAttach : eEjemplar.getECopiaList()) {
                ECopiaListeCopiaToAttach = em.getReference(ECopiaListeCopiaToAttach.getClass(), ECopiaListeCopiaToAttach.getIdcopia());
                attachedECopiaList.add(ECopiaListeCopiaToAttach);
            }
            eEjemplar.setECopiaList(attachedECopiaList);
            em.persist(eEjemplar);
            if (idcategoria != null) {
                idcategoria.getEEjemplarList().add(eEjemplar);
                idcategoria = em.merge(idcategoria);
            }
            for (eTema ETemaListeTema : eEjemplar.getETemaList()) {
                ETemaListeTema.getEEjemplarList().add(eEjemplar);
                ETemaListeTema = em.merge(ETemaListeTema);
            }
            for (eCopia ECopiaListeCopia : eEjemplar.getECopiaList()) {
                eEjemplar oldIdejemplarOfECopiaListeCopia = ECopiaListeCopia.getIdejemplar();
                ECopiaListeCopia.setIdejemplar(eEjemplar);
                ECopiaListeCopia = em.merge(ECopiaListeCopia);
                if (oldIdejemplarOfECopiaListeCopia != null) {
                    oldIdejemplarOfECopiaListeCopia.getECopiaList().remove(ECopiaListeCopia);
                    oldIdejemplarOfECopiaListeCopia = em.merge(oldIdejemplarOfECopiaListeCopia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(eEjemplar eEjemplar) throws IllegalOrphanException, NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();
            eEjemplar persistenteEjemplar = em.find(eEjemplar.class, eEjemplar.getIdejemplar());
            eCategoria idcategoriaOld = persistenteEjemplar.getIdcategoria();
            eCategoria idcategoriaNew = eEjemplar.getIdcategoria();
            List<eTema> ETemaListOld = persistenteEjemplar.getETemaList();
            List<eTema> ETemaListNew = eEjemplar.getETemaList();
            List<eCopia> ECopiaListOld = persistenteEjemplar.getECopiaList();
            List<eCopia> ECopiaListNew = eEjemplar.getECopiaList();
            List<String> illegalOrphanMessages = null;
            for (eCopia ECopiaListOldeCopia : ECopiaListOld) {
                if (!ECopiaListNew.contains(ECopiaListOldeCopia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain eCopia " + ECopiaListOldeCopia + " since its idejemplar field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcategoriaNew != null) {
                idcategoriaNew = em.getReference(idcategoriaNew.getClass(), idcategoriaNew.getIdcategoria());
                eEjemplar.setIdcategoria(idcategoriaNew);
            }
            List<eTema> attachedETemaListNew = new ArrayList<eTema>();
            for (eTema ETemaListNeweTemaToAttach : ETemaListNew) {
                ETemaListNeweTemaToAttach = em.getReference(ETemaListNeweTemaToAttach.getClass(), ETemaListNeweTemaToAttach.getIdtema());
                attachedETemaListNew.add(ETemaListNeweTemaToAttach);
            }
            ETemaListNew = attachedETemaListNew;
            eEjemplar.setETemaList(ETemaListNew);
            List<eCopia> attachedECopiaListNew = new ArrayList<eCopia>();
            for (eCopia ECopiaListNeweCopiaToAttach : ECopiaListNew) {
                ECopiaListNeweCopiaToAttach = em.getReference(ECopiaListNeweCopiaToAttach.getClass(), ECopiaListNeweCopiaToAttach.getIdcopia());
                attachedECopiaListNew.add(ECopiaListNeweCopiaToAttach);
            }
            ECopiaListNew = attachedECopiaListNew;
            eEjemplar.setECopiaList(ECopiaListNew);
            eEjemplar = em.merge(eEjemplar);
            if (idcategoriaOld != null && !idcategoriaOld.equals(idcategoriaNew)) {
                idcategoriaOld.getEEjemplarList().remove(eEjemplar);
                idcategoriaOld = em.merge(idcategoriaOld);
            }
            if (idcategoriaNew != null && !idcategoriaNew.equals(idcategoriaOld)) {
                idcategoriaNew.getEEjemplarList().add(eEjemplar);
                idcategoriaNew = em.merge(idcategoriaNew);
            }
            for (eTema ETemaListOldeTema : ETemaListOld) {
                if (!ETemaListNew.contains(ETemaListOldeTema)) {
                    ETemaListOldeTema.getEEjemplarList().remove(eEjemplar);
                    ETemaListOldeTema = em.merge(ETemaListOldeTema);
                }
            }
            for (eTema ETemaListNeweTema : ETemaListNew) {
                if (!ETemaListOld.contains(ETemaListNeweTema)) {
                    ETemaListNeweTema.getEEjemplarList().add(eEjemplar);
                    ETemaListNeweTema = em.merge(ETemaListNeweTema);
                }
            }
            for (eCopia ECopiaListNeweCopia : ECopiaListNew) {
                if (!ECopiaListOld.contains(ECopiaListNeweCopia)) {
                    eEjemplar oldIdejemplarOfECopiaListNeweCopia = ECopiaListNeweCopia.getIdejemplar();
                    ECopiaListNeweCopia.setIdejemplar(eEjemplar);
                    ECopiaListNeweCopia = em.merge(ECopiaListNeweCopia);
                    if (oldIdejemplarOfECopiaListNeweCopia != null && !oldIdejemplarOfECopiaListNeweCopia.equals(eEjemplar)) {
                        oldIdejemplarOfECopiaListNeweCopia.getECopiaList().remove(ECopiaListNeweCopia);
                        oldIdejemplarOfECopiaListNeweCopia = em.merge(oldIdejemplarOfECopiaListNeweCopia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = eEjemplar.getIdejemplar();
                if (findeEjemplar(id) == null) {
                    throw new NonexistentEntityException("The eEjemplar with id " + id + " no longer exists.");
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
            eEjemplar eEjemplar;
            try {
                eEjemplar = em.getReference(eEjemplar.class, id);
                eEjemplar.getIdejemplar();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eEjemplar with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<eCopia> ECopiaListOrphanCheck = eEjemplar.getECopiaList();
            for (eCopia ECopiaListOrphanCheckeCopia : ECopiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This eEjemplar (" + eEjemplar + ") cannot be destroyed since the eCopia " + ECopiaListOrphanCheckeCopia + " in its ECopiaList field has a non-nullable idejemplar field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            eCategoria idcategoria = eEjemplar.getIdcategoria();
            if (idcategoria != null) {
                idcategoria.getEEjemplarList().remove(eEjemplar);
                idcategoria = em.merge(idcategoria);
            }
            List<eTema> ETemaList = eEjemplar.getETemaList();
            for (eTema ETemaListeTema : ETemaList) {
                ETemaListeTema.getEEjemplarList().remove(eEjemplar);
                ETemaListeTema = em.merge(ETemaListeTema);
            }
            em.remove(eEjemplar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
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
