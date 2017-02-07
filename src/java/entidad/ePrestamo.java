/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author MiguelSc
 */
@Entity
@Table(name = "prestamo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ePrestamo.findAll", query = "SELECT e FROM ePrestamo e"),
//    @NamedQuery(name = "ePrestamo.findByIdprestamo", query = "SELECT e FROM ePrestamo e WHERE e.idprestamo = :idprestamo"),
//    @NamedQuery(name = "ePrestamo.findByFechareg", query = "SELECT e FROM ePrestamo e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "ePrestamo.findByFechaini", query = "SELECT e FROM ePrestamo e WHERE e.fechaini = :fechaini"),
//    @NamedQuery(name = "ePrestamo.findByFechafin", query = "SELECT e FROM ePrestamo e WHERE e.fechafin = :fechafin"),
//    @NamedQuery(name = "ePrestamo.findByDevuelto", query = "SELECT e FROM ePrestamo e WHERE e.devuelto = :devuelto")})
public class ePrestamo implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprestamo")
    private Integer idprestamo;
    @Basic(optional = false)
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Basic(optional = false)
    @Column(name = "fechaini")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaini;
    @Basic(optional = false)
    @Column(name = "fechafin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Basic(optional = false)
    @Column(name = "devuelto")
    private boolean devuelto;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private ePersona idpersona;
    @JoinColumn(name = "idcopia", referencedColumnName = "idcopia")
    @ManyToOne(optional = false)
    private eCopia idcopia;

    public ePrestamo() {
    }

    public ePrestamo(Integer idprestamo) {
        this.idprestamo = idprestamo;
    }

    public ePrestamo(Integer idprestamo, Date fechareg, Date fechaini, Date fechafin, boolean devuelto) {
        this.idprestamo = idprestamo;
        this.fechareg = fechareg;
        this.fechaini = fechaini;
        this.fechafin = fechafin;
        this.devuelto = devuelto;
    }

    public Integer getIdprestamo() {
        return idprestamo;
    }

    public void setIdprestamo(Integer idprestamo) {
        this.idprestamo = idprestamo;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public Date getFechaini() {
        return fechaini;
    }

    public void setFechaini(Date fechaini) {
        this.fechaini = fechaini;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public boolean getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public ePersona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(ePersona idpersona) {
        this.idpersona = idpersona;
    }

    public eCopia getIdcopia() {
        return idcopia;
    }

    public void setIdcopia(eCopia idcopia) {
        this.idcopia = idcopia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprestamo != null ? idprestamo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ePrestamo)) {
            return false;
        }
        ePrestamo other = (ePrestamo) object;
        if ((this.idprestamo == null && other.idprestamo != null) || (this.idprestamo != null && !this.idprestamo.equals(other.idprestamo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.ePrestamo[ idprestamo=" + idprestamo + " ]";
    }
    
}
