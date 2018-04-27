/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MsiClases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aitor Alday
 */
@Entity
@Table(name = "PARTIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p")
    , @NamedQuery(name = "Partido.findByIdPartido", query = "SELECT p FROM Partido p WHERE p.idPartido = :idPartido")
    , @NamedQuery(name = "Partido.findByFecha", query = "SELECT p FROM Partido p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Partido.findByPuntosLoc", query = "SELECT p FROM Partido p WHERE p.puntosLoc = :puntosLoc")
    , @NamedQuery(name = "Partido.findByPuntosVis", query = "SELECT p FROM Partido p WHERE p.puntosVis = :puntosVis")})
public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PARTIDO")
    private BigDecimal idPartido;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "PUNTOS_LOC")
    private BigInteger puntosLoc;
    @Basic(optional = false)
    @Column(name = "PUNTOS_VIS")
    private BigInteger puntosVis;
    @ManyToMany(mappedBy = "partidoList")
    private List<Equipo> equipoList;
    @JoinColumn(name = "ID_JORNADA", referencedColumnName = "ID_JORNADA")
    @ManyToOne(optional = false)
    private Jornada idJornada;

    public Partido() {
    }

    public Partido(BigDecimal idPartido) {
        this.idPartido = idPartido;
    }

    public Partido(BigDecimal idPartido, Date fecha, BigInteger puntosLoc, BigInteger puntosVis) {
        this.idPartido = idPartido;
        this.fecha = fecha;
        this.puntosLoc = puntosLoc;
        this.puntosVis = puntosVis;
    }

    public BigDecimal getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(BigDecimal idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigInteger getPuntosLoc() {
        return puntosLoc;
    }

    public void setPuntosLoc(BigInteger puntosLoc) {
        this.puntosLoc = puntosLoc;
    }

    public BigInteger getPuntosVis() {
        return puntosVis;
    }

    public void setPuntosVis(BigInteger puntosVis) {
        this.puntosVis = puntosVis;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public Jornada getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Jornada idJornada) {
        this.idJornada = idJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartido != null ? idPartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.idPartido == null && other.idPartido != null) || (this.idPartido != null && !this.idPartido.equals(other.idPartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MsiClases.Partido[ idPartido=" + idPartido + " ]";
    }
    
}
