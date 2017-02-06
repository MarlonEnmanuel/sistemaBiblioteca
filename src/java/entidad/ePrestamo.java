/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author MiguelSc
 */
@Entity
@Table(name = "prestamo")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ePrestamo.findAll", query = "SELECT e FROM ePrestamo e"),
//    @NamedQuery(name = "ePrestamo.findByPrestamoId", query = "SELECT e FROM ePrestamo e WHERE e.prestamoId = :prestamoId"),
//    @NamedQuery(name = "ePrestamo.findByPrestamoFecreg", query = "SELECT e FROM ePrestamo e WHERE e.prestamoFecreg = :prestamoFecreg"),
//    @NamedQuery(name = "ePrestamo.findByPrestamoFecini", query = "SELECT e FROM ePrestamo e WHERE e.prestamoFecini = :prestamoFecini"),
//    @NamedQuery(name = "ePrestamo.findByPrestamoFecfin", query = "SELECT e FROM ePrestamo e WHERE e.prestamoFecfin = :prestamoFecfin"),
//    @NamedQuery(name = "ePrestamo.findByPrestamoDevuelto", query = "SELECT e FROM ePrestamo e WHERE e.prestamoDevuelto = :prestamoDevuelto"),
//    @NamedQuery(name = "ePrestamo.findByCopiaId", query = "SELECT e FROM ePrestamo e WHERE e.copiaId = :copiaId"),
//    @NamedQuery(name = "ePrestamo.findByPersonaId", query = "SELECT e FROM ePrestamo e WHERE e.personaId = :personaId")})
public class ePrestamo implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prestamo_id")
    private Integer prestamoId;
    @Basic(optional = false)
    @Column(name = "prestamo_fecreg")
    @Temporal(TemporalType.DATE)
    private Date prestamoFecreg;
    @Basic(optional = false)
    @Column(name = "prestamo_fecini")
    @Temporal(TemporalType.DATE)
    private Date prestamoFecini;
    @Basic(optional = false)
    @Column(name = "prestamo_fecfin")
    @Temporal(TemporalType.DATE)
    private Date prestamoFecfin;
    @Basic(optional = false)
    @Column(name = "prestamo_devuelto")
    private boolean prestamoDevuelto;
    @Basic(optional = false)
    @Column(name = "copia_id")
    private int copiaId;
    @Basic(optional = false)
    @Column(name = "persona_id")
    private int personaId;

    public ePrestamo() {
    }

    public ePrestamo(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }

    public ePrestamo(Integer prestamoId, Date prestamoFecreg, Date prestamoFecini, Date prestamoFecfin, boolean prestamoDevuelto, int copiaId, int personaId) {
        this.prestamoId = prestamoId;
        this.prestamoFecreg = prestamoFecreg;
        this.prestamoFecini = prestamoFecini;
        this.prestamoFecfin = prestamoFecfin;
        this.prestamoDevuelto = prestamoDevuelto;
        this.copiaId = copiaId;
        this.personaId = personaId;
    }

    public Integer getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Integer prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Date getPrestamoFecreg() {
        return prestamoFecreg;
    }

    public void setPrestamoFecreg(Date prestamoFecreg) {
        this.prestamoFecreg = prestamoFecreg;
    }

    public Date getPrestamoFecini() {
        return prestamoFecini;
    }

    public void setPrestamoFecini(Date prestamoFecini) {
        this.prestamoFecini = prestamoFecini;
    }

    public Date getPrestamoFecfin() {
        return prestamoFecfin;
    }

    public void setPrestamoFecfin(Date prestamoFecfin) {
        this.prestamoFecfin = prestamoFecfin;
    }

    public boolean getPrestamoDevuelto() {
        return prestamoDevuelto;
    }

    public void setPrestamoDevuelto(boolean prestamoDevuelto) {
        this.prestamoDevuelto = prestamoDevuelto;
    }

    public int getCopiaId() {
        return copiaId;
    }

    public void setCopiaId(int copiaId) {
        this.copiaId = copiaId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prestamoId != null ? prestamoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ePrestamo)) {
            return false;
        }
        ePrestamo other = (ePrestamo) object;
        if ((this.prestamoId == null && other.prestamoId != null) || (this.prestamoId != null && !this.prestamoId.equals(other.prestamoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.ePrestamo[ prestamoId=" + prestamoId + " ]";
    }
    
}
