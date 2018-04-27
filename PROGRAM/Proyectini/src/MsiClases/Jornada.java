/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MsiClases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "JORNADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jornada.findAll", query = "SELECT j FROM Jornada j")
    , @NamedQuery(name = "Jornada.findByIdJornada", query = "SELECT j FROM Jornada j WHERE j.idJornada = :idJornada")
    , @NamedQuery(name = "Jornada.findByFecIni", query = "SELECT j FROM Jornada j WHERE j.fecIni = :fecIni")
    , @NamedQuery(name = "Jornada.findByFecFin", query = "SELECT j FROM Jornada j WHERE j.fecFin = :fecFin")})
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_JORNADA")
    private BigDecimal idJornada;
    @Basic(optional = false)
    @Column(name = "FEC_INI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecIni;
    @Basic(optional = false)
    @Column(name = "FEC_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecFin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idJornada")
    private List<Partido> partidoList;

    public Jornada() {
    }

    public Jornada(BigDecimal idJornada) {
        this.idJornada = idJornada;
    }

    public Jornada(BigDecimal idJornada, Date fecIni, Date fecFin) {
        this.idJornada = idJornada;
        this.fecIni = fecIni;
        this.fecFin = fecFin;
    }

    public BigDecimal getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(BigDecimal idJornada) {
        this.idJornada = idJornada;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    @XmlTransient
    public List<Partido> getPartidoList() {
        return partidoList;
    }

    public void setPartidoList(List<Partido> partidoList) {
        this.partidoList = partidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jornada)) {
            return false;
        }
        Jornada other = (Jornada) object;
        if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MsiClases.Jornada[ idJornada=" + idJornada + " ]";
    }
    
}
