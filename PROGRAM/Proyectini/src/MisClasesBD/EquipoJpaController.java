/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MisClasesBD;

import MisClasesBD.exceptions.IllegalOrphanException;
import MisClasesBD.exceptions.NonexistentEntityException;
import MisClasesBD.exceptions.PreexistingEntityException;
import MsiClases.Equipo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import MsiClases.Partido;
import java.util.ArrayList;
import java.util.List;
import MsiClases.Jugador;
import MsiClases.Persona;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aitor Alday
 */
public class EquipoJpaController implements Serializable {

    public EquipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Equipo equipo) throws PreexistingEntityException, Exception {
        if (equipo.getPartidoList() == null) {
            equipo.setPartidoList(new ArrayList<Partido>());
        }
        if (equipo.getJugadorList() == null) {
            equipo.setJugadorList(new ArrayList<Jugador>());
        }
        if (equipo.getPersonaList() == null) {
            equipo.setPersonaList(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Partido> attachedPartidoList = new ArrayList<Partido>();
            for (Partido partidoListPartidoToAttach : equipo.getPartidoList()) {
                partidoListPartidoToAttach = em.getReference(partidoListPartidoToAttach.getClass(), partidoListPartidoToAttach.getIdPartido());
                attachedPartidoList.add(partidoListPartidoToAttach);
            }
            equipo.setPartidoList(attachedPartidoList);
            List<Jugador> attachedJugadorList = new ArrayList<Jugador>();
            for (Jugador jugadorListJugadorToAttach : equipo.getJugadorList()) {
                jugadorListJugadorToAttach = em.getReference(jugadorListJugadorToAttach.getClass(), jugadorListJugadorToAttach.getIdJugador());
                attachedJugadorList.add(jugadorListJugadorToAttach);
            }
            equipo.setJugadorList(attachedJugadorList);
            List<Persona> attachedPersonaList = new ArrayList<Persona>();
            for (Persona personaListPersonaToAttach : equipo.getPersonaList()) {
                personaListPersonaToAttach = em.getReference(personaListPersonaToAttach.getClass(), personaListPersonaToAttach.getIdPersona());
                attachedPersonaList.add(personaListPersonaToAttach);
            }
            equipo.setPersonaList(attachedPersonaList);
            em.persist(equipo);
            for (Partido partidoListPartido : equipo.getPartidoList()) {
                partidoListPartido.getEquipoList().add(equipo);
                partidoListPartido = em.merge(partidoListPartido);
            }
            for (Jugador jugadorListJugador : equipo.getJugadorList()) {
                Equipo oldIdEquipoOfJugadorListJugador = jugadorListJugador.getIdEquipo();
                jugadorListJugador.setIdEquipo(equipo);
                jugadorListJugador = em.merge(jugadorListJugador);
                if (oldIdEquipoOfJugadorListJugador != null) {
                    oldIdEquipoOfJugadorListJugador.getJugadorList().remove(jugadorListJugador);
                    oldIdEquipoOfJugadorListJugador = em.merge(oldIdEquipoOfJugadorListJugador);
                }
            }
            for (Persona personaListPersona : equipo.getPersonaList()) {
                Equipo oldIdEquipoOfPersonaListPersona = personaListPersona.getIdEquipo();
                personaListPersona.setIdEquipo(equipo);
                personaListPersona = em.merge(personaListPersona);
                if (oldIdEquipoOfPersonaListPersona != null) {
                    oldIdEquipoOfPersonaListPersona.getPersonaList().remove(personaListPersona);
                    oldIdEquipoOfPersonaListPersona = em.merge(oldIdEquipoOfPersonaListPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEquipo(equipo.getIdEquipo()) != null) {
                throw new PreexistingEntityException("Equipo " + equipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Equipo equipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Equipo persistentEquipo = em.find(Equipo.class, equipo.getIdEquipo());
            List<Partido> partidoListOld = persistentEquipo.getPartidoList();
            List<Partido> partidoListNew = equipo.getPartidoList();
            List<Jugador> jugadorListOld = persistentEquipo.getJugadorList();
            List<Jugador> jugadorListNew = equipo.getJugadorList();
            List<Persona> personaListOld = persistentEquipo.getPersonaList();
            List<Persona> personaListNew = equipo.getPersonaList();
            List<String> illegalOrphanMessages = null;
            for (Jugador jugadorListOldJugador : jugadorListOld) {
                if (!jugadorListNew.contains(jugadorListOldJugador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Jugador " + jugadorListOldJugador + " since its idEquipo field is not nullable.");
                }
            }
            for (Persona personaListOldPersona : personaListOld) {
                if (!personaListNew.contains(personaListOldPersona)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Persona " + personaListOldPersona + " since its idEquipo field is not nullable.");
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
            equipo.setPartidoList(partidoListNew);
            List<Jugador> attachedJugadorListNew = new ArrayList<Jugador>();
            for (Jugador jugadorListNewJugadorToAttach : jugadorListNew) {
                jugadorListNewJugadorToAttach = em.getReference(jugadorListNewJugadorToAttach.getClass(), jugadorListNewJugadorToAttach.getIdJugador());
                attachedJugadorListNew.add(jugadorListNewJugadorToAttach);
            }
            jugadorListNew = attachedJugadorListNew;
            equipo.setJugadorList(jugadorListNew);
            List<Persona> attachedPersonaListNew = new ArrayList<Persona>();
            for (Persona personaListNewPersonaToAttach : personaListNew) {
                personaListNewPersonaToAttach = em.getReference(personaListNewPersonaToAttach.getClass(), personaListNewPersonaToAttach.getIdPersona());
                attachedPersonaListNew.add(personaListNewPersonaToAttach);
            }
            personaListNew = attachedPersonaListNew;
            equipo.setPersonaList(personaListNew);
            equipo = em.merge(equipo);
            for (Partido partidoListOldPartido : partidoListOld) {
                if (!partidoListNew.contains(partidoListOldPartido)) {
                    partidoListOldPartido.getEquipoList().remove(equipo);
                    partidoListOldPartido = em.merge(partidoListOldPartido);
                }
            }
            for (Partido partidoListNewPartido : partidoListNew) {
                if (!partidoListOld.contains(partidoListNewPartido)) {
                    partidoListNewPartido.getEquipoList().add(equipo);
                    partidoListNewPartido = em.merge(partidoListNewPartido);
                }
            }
            for (Jugador jugadorListNewJugador : jugadorListNew) {
                if (!jugadorListOld.contains(jugadorListNewJugador)) {
                    Equipo oldIdEquipoOfJugadorListNewJugador = jugadorListNewJugador.getIdEquipo();
                    jugadorListNewJugador.setIdEquipo(equipo);
                    jugadorListNewJugador = em.merge(jugadorListNewJugador);
                    if (oldIdEquipoOfJugadorListNewJugador != null && !oldIdEquipoOfJugadorListNewJugador.equals(equipo)) {
                        oldIdEquipoOfJugadorListNewJugador.getJugadorList().remove(jugadorListNewJugador);
                        oldIdEquipoOfJugadorListNewJugador = em.merge(oldIdEquipoOfJugadorListNewJugador);
                    }
                }
            }
            for (Persona personaListNewPersona : personaListNew) {
                if (!personaListOld.contains(personaListNewPersona)) {
                    Equipo oldIdEquipoOfPersonaListNewPersona = personaListNewPersona.getIdEquipo();
                    personaListNewPersona.setIdEquipo(equipo);
                    personaListNewPersona = em.merge(personaListNewPersona);
                    if (oldIdEquipoOfPersonaListNewPersona != null && !oldIdEquipoOfPersonaListNewPersona.equals(equipo)) {
                        oldIdEquipoOfPersonaListNewPersona.getPersonaList().remove(personaListNewPersona);
                        oldIdEquipoOfPersonaListNewPersona = em.merge(oldIdEquipoOfPersonaListNewPersona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = equipo.getIdEquipo();
                if (findEquipo(id) == null) {
                    throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.");
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
            Equipo equipo;
            try {
                equipo = em.getReference(Equipo.class, id);
                equipo.getIdEquipo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The equipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Jugador> jugadorListOrphanCheck = equipo.getJugadorList();
            for (Jugador jugadorListOrphanCheckJugador : jugadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipo (" + equipo + ") cannot be destroyed since the Jugador " + jugadorListOrphanCheckJugador + " in its jugadorList field has a non-nullable idEquipo field.");
            }
            List<Persona> personaListOrphanCheck = equipo.getPersonaList();
            for (Persona personaListOrphanCheckPersona : personaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Equipo (" + equipo + ") cannot be destroyed since the Persona " + personaListOrphanCheckPersona + " in its personaList field has a non-nullable idEquipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Partido> partidoList = equipo.getPartidoList();
            for (Partido partidoListPartido : partidoList) {
                partidoListPartido.getEquipoList().remove(equipo);
                partidoListPartido = em.merge(partidoListPartido);
            }
            em.remove(equipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Equipo> findEquipoEntities() {
        return findEquipoEntities(true, -1, -1);
    }

    public List<Equipo> findEquipoEntities(int maxResults, int firstResult) {
        return findEquipoEntities(false, maxResults, firstResult);
    }

    private List<Equipo> findEquipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Equipo.class));
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

    public Equipo findEquipo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Equipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEquipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Equipo> rt = cq.from(Equipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
