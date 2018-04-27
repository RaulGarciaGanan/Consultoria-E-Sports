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
import MsiClases.Equipo;
import MsiClases.Jugador;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aitor Alday
 */
public class JugadorJpaController implements Serializable {

    public JugadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jugador jugador) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo idEquipo = jugador.getIdEquipo();
            if (idEquipo != null) {
                idEquipo = em.getReference(idEquipo.getClass(), idEquipo.getIdEquipo());
                jugador.setIdEquipo(idEquipo);
            }
            em.persist(jugador);
            if (idEquipo != null) {
                idEquipo.getJugadorList().add(jugador);
                idEquipo = em.merge(idEquipo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findJugador(jugador.getIdJugador()) != null) {
                throw new PreexistingEntityException("Jugador " + jugador + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jugador jugador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Jugador persistentJugador = em.find(Jugador.class, jugador.getIdJugador());
            Equipo idEquipoOld = persistentJugador.getIdEquipo();
            Equipo idEquipoNew = jugador.getIdEquipo();
            if (idEquipoNew != null) {
                idEquipoNew = em.getReference(idEquipoNew.getClass(), idEquipoNew.getIdEquipo());
                jugador.setIdEquipo(idEquipoNew);
            }
            jugador = em.merge(jugador);
            if (idEquipoOld != null && !idEquipoOld.equals(idEquipoNew)) {
                idEquipoOld.getJugadorList().remove(jugador);
                idEquipoOld = em.merge(idEquipoOld);
            }
            if (idEquipoNew != null && !idEquipoNew.equals(idEquipoOld)) {
                idEquipoNew.getJugadorList().add(jugador);
                idEquipoNew = em.merge(idEquipoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = jugador.getIdJugador();
                if (findJugador(id) == null) {
                    throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.");
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
            Jugador jugador;
            try {
                jugador = em.getReference(Jugador.class, id);
                jugador.getIdJugador();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jugador with id " + id + " no longer exists.", enfe);
            }
            Equipo idEquipo = jugador.getIdEquipo();
            if (idEquipo != null) {
                idEquipo.getJugadorList().remove(jugador);
                idEquipo = em.merge(idEquipo);
            }
            em.remove(jugador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Jugador> findJugadorEntities() {
        return findJugadorEntities(true, -1, -1);
    }

    public List<Jugador> findJugadorEntities(int maxResults, int firstResult) {
        return findJugadorEntities(false, maxResults, firstResult);
    }

    private List<Jugador> findJugadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jugador.class));
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

    public Jugador findJugador(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugador.class, id);
        } finally {
            em.close();
        }
    }

    public int getJugadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jugador> rt = cq.from(Jugador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
