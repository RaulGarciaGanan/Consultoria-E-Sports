/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MsiClases;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aitor Alday
 */
@Entity
@Table(name = "JUGADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j")
    , @NamedQuery(name = "Jugador.findByIdJugador", query = "SELECT j FROM Jugador j WHERE j.idJugador = :idJugador")
    , @NamedQuery(name = "Jugador.findByNickname", query = "SELECT j FROM Jugador j WHERE j.nickname = :nickname")
    , @NamedQuery(name = "Jugador.findByNombre", query = "SELECT j FROM Jugador j WHERE j.nombre = :nombre")
    , @NamedQuery(name = "Jugador.findBySueldo", query = "SELECT j FROM Jugador j WHERE j.sueldo = :sueldo")})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_JUGADOR")
    private BigDecimal idJugador;
    @Basic(optional = false)
    @Column(name = "NICKNAME")
    private String nickname;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "SUELDO")
    private BigDecimal sueldo;
    @JoinColumn(name = "ID_EQUIPO", referencedColumnName = "ID_EQUIPO")
    @ManyToOne(optional = false)
    private Equipo idEquipo;

    public Jugador() {
    }

    public Jugador(BigDecimal idJugador) {
        this.idJugador = idJugador;
    }

    public Jugador(BigDecimal idJugador, String nickname, String nombre, BigDecimal sueldo) {
        this.idJugador = idJugador;
        this.nickname = nickname;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public BigDecimal getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(BigDecimal idJugador) {
        this.idJugador = idJugador;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public Equipo getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipo idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJugador != null ? idJugador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.idJugador == null && other.idJugador != null) || (this.idJugador != null && !this.idJugador.equals(other.idJugador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MsiClases.Jugador[ idJugador=" + idJugador + " ]";
    }
    
}
