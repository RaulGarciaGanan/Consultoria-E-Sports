/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClasesBD.exceptions.IllegalOrphanException;
import MisClasesBD.exceptions.NonexistentEntityException;
import MisClasesBD.exceptions.PreexistingEntityException;
import MsiClases.Jornada;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MsiClases.Partido;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aitor Alday
 */
public class JornadaJpaController implements Serializable {

    public JornadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jornada jornada) throws PreexistingEntityException, Exception {
        if (jornada.getPartidoList() == null) {
            jornada.setPartidoList(new ArrayList<Partido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Partido> attachedPartidoList = new ArrayList<Partido>();
            for (Partido partidoListPartidoToAttach : jornada.getPartidoList()) {
                partidoListPartidoToAttach = em.getReference(partidoListPartidoToAttach.getClass(), partidoListPartidoToAttach.getIdPartido());
                attachedPartidoList.add(partidoListPartidoToAttach);
            }
            jornada.setPartidoList(attachedPartidoList);
            em.persist(jornada);
            for (Partido partidoListPartido : jornada.getPartidoList()) {
                Jornada oldIdJornadaOfPartidoListPartido = partidoListPartido.getIdJornada();
                partidoListPartido.setIdJornada(jornada);
                partidoListPartido = em.merge(partidoListPartido);
                if (oldIdJornadaOfPartidoListPartido != null) {
                    oldIdJornadaOfPartidoListPartido.getPartidoList().remove(partidoListPartido);
                    oldIdJornadaOfPartidoListPartido = em.merge(oldIdJornadaOfPartidoListPartido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJornada(jornada.getIdJornada()) != null) {
                throw new PreexistingEntityException("Jornada " + jornada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jornada jornada) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jornada persistentJornada = em.find(Jornada.class, jornada.getIdJornada());
            List<Partido> partidoListOld = persistentJornada.getPartidoList();
            List<Partido> partidoListNew = jornada.getPartidoList();
            List<String> illegalOrphanMessages = null;
            for (Partido partidoListOldPartido : partidoListOld) {
                if (!partidoListNew.contains(partidoListOldPartido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Partido " + partidoListOldPartido + " since its idJornada field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Partido> attachedPartidoListNew = new ArrayList<Partido>();
            for (Partido partidoListNewPartidoToAttach : partidoListNew) {
                partidoListNewPartidoToAttach = em.getReference(partidoListNewPartidoToAttach.getClass(), partidoListNewPartidoToAttach.getIdPartido());
                attachedPartidoListNew.add(partidoListNewPartidoToAttach);
            }
            partidoListNew = attachedPartidoListNew;
            jornada.setPartidoList(partidoListNew);
            jornada = em.merge(jornada);
            for (Partido partidoListNewPartido : partidoListNew) {
                if (!partidoListOld.contains(partidoListNewPartido)) {
                    Jornada oldIdJornadaOfPartidoListNewPartido = partidoListNewPartido.getIdJornada();
                    partidoListNewPartido.setIdJornada(jornada);
                    partidoListNewPartido = em.merge(partidoListNewPartido);
                    if (oldIdJornadaOfPartidoListNewPartido != null && !oldIdJornadaOfPartidoListNewPartido.equals(jornada)) {
                        oldIdJornadaOfPartidoListNewPartido.getPartidoList().remove(partidoListNewPartido);
                        oldIdJornadaOfPartidoListNewPartido = em.merge(oldIdJornadaOfPartidoListNewPartido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = jornada.getIdJornada();
                if (findJornada(id) == null) {
                    throw new NonexistentEntityException("The jornada with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jornada jornada;
            try {
                jornada = em.getReference(Jornada.class, id);
                jornada.getIdJornada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jornada with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Partido> partidoListOrphanCheck = jornada.getPartidoList();
            for (Partido partidoListOrphanCheckPartido : partidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jornada (" + jornada + ") cannot be destroyed since the Partido " + partidoListOrphanCheckPartido + " in its partidoList field has a non-nullable idJornada field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(jornada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jornada> findJornadaEntities() {
        return findJornadaEntities(true, -1, -1);
    }

    public List<Jornada> findJornadaEntities(int maxResults, int firstResult) {
        return findJornadaEntities(false, maxResults, firstResult);
    }

    private List<Jornada> findJornadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jornada.class));
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

    public Jornada findJornada(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jornada.class, id);
        } finally {
            em.close();
        }
    }

    public int getJornadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jornada> rt = cq.from(Jornada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
