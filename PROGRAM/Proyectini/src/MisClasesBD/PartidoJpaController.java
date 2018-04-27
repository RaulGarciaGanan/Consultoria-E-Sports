/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClasesBD.exceptions.NonexistentEntityException;
import MisClasesBD.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MsiClases.Jornada;
import MsiClases.Equipo;
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
public class PartidoJpaController implements Serializable {

    public PartidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Partido partido) throws PreexistingEntityException, Exception {
        if (partido.getEquipoList() == null) {
            partido.setEquipoList(new ArrayList<Equipo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jornada idJornada = partido.getIdJornada();
            if (idJornada != null) {
                idJornada = em.getReference(idJornada.getClass(), idJornada.getIdJornada());
                partido.setIdJornada(idJornada);
            }
            List<Equipo> attachedEquipoList = new ArrayList<Equipo>();
            for (Equipo equipoListEquipoToAttach : partido.getEquipoList()) {
                equipoListEquipoToAttach = em.getReference(equipoListEquipoToAttach.getClass(), equipoListEquipoToAttach.getIdEquipo());
                attachedEquipoList.add(equipoListEquipoToAttach);
            }
            partido.setEquipoList(attachedEquipoList);
            em.persist(partido);
            if (idJornada != null) {
                idJornada.getPartidoList().add(partido);
                idJornada = em.merge(idJornada);
            }
            for (Equipo equipoListEquipo : partido.getEquipoList()) {
                equipoListEquipo.getPartidoList().add(partido);
                equipoListEquipo = em.merge(equipoListEquipo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPartido(partido.getIdPartido()) != null) {
                throw new PreexistingEntityException("Partido " + partido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Partido partido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partido persistentPartido = em.find(Partido.class, partido.getIdPartido());
            Jornada idJornadaOld = persistentPartido.getIdJornada();
            Jornada idJornadaNew = partido.getIdJornada();
            List<Equipo> equipoListOld = persistentPartido.getEquipoList();
            List<Equipo> equipoListNew = partido.getEquipoList();
            if (idJornadaNew != null) {
                idJornadaNew = em.getReference(idJornadaNew.getClass(), idJornadaNew.getIdJornada());
                partido.setIdJornada(idJornadaNew);
            }
            List<Equipo> attachedEquipoListNew = new ArrayList<Equipo>();
            for (Equipo equipoListNewEquipoToAttach : equipoListNew) {
                equipoListNewEquipoToAttach = em.getReference(equipoListNewEquipoToAttach.getClass(), equipoListNewEquipoToAttach.getIdEquipo());
                attachedEquipoListNew.add(equipoListNewEquipoToAttach);
            }
            equipoListNew = attachedEquipoListNew;
            partido.setEquipoList(equipoListNew);
            partido = em.merge(partido);
            if (idJornadaOld != null && !idJornadaOld.equals(idJornadaNew)) {
                idJornadaOld.getPartidoList().remove(partido);
                idJornadaOld = em.merge(idJornadaOld);
            }
            if (idJornadaNew != null && !idJornadaNew.equals(idJornadaOld)) {
                idJornadaNew.getPartidoList().add(partido);
                idJornadaNew = em.merge(idJornadaNew);
            }
            for (Equipo equipoListOldEquipo : equipoListOld) {
                if (!equipoListNew.contains(equipoListOldEquipo)) {
                    equipoListOldEquipo.getPartidoList().remove(partido);
                    equipoListOldEquipo = em.merge(equipoListOldEquipo);
                }
            }
            for (Equipo equipoListNewEquipo : equipoListNew) {
                if (!equipoListOld.contains(equipoListNewEquipo)) {
                    equipoListNewEquipo.getPartidoList().add(partido);
                    equipoListNewEquipo = em.merge(equipoListNewEquipo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = partido.getIdPartido();
                if (findPartido(id) == null) {
                    throw new NonexistentEntityException("The partido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Partido partido;
            try {
                partido = em.getReference(Partido.class, id);
                partido.getIdPartido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The partido with id " + id + " no longer exists.", enfe);
            }
            Jornada idJornada = partido.getIdJornada();
            if (idJornada != null) {
                idJornada.getPartidoList().remove(partido);
                idJornada = em.merge(idJornada);
            }
            List<Equipo> equipoList = partido.getEquipoList();
            for (Equipo equipoListEquipo : equipoList) {
                equipoListEquipo.getPartidoList().remove(partido);
                equipoListEquipo = em.merge(equipoListEquipo);
            }
            em.remove(partido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Partido> findPartidoEntities() {
        return findPartidoEntities(true, -1, -1);
    }

    public List<Partido> findPartidoEntities(int maxResults, int firstResult) {
        return findPartidoEntities(false, maxResults, firstResult);
    }

    private List<Partido> findPartidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Partido.class));
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

    public Partido findPartido(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Partido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPartidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Partido> rt = cq.from(Partido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
