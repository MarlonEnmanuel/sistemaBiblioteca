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
@Table(name = "tema")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eTema.findAll", query = "SELECT e FROM eTema e"),
//    @NamedQuery(name = "eTema.findByTemaId", query = "SELECT e FROM eTema e WHERE e.temaId = :temaId"),
//    @NamedQuery(name = "eTema.findByTemaFecreg", query = "SELECT e FROM eTema e WHERE e.temaFecreg = :temaFecreg"),
//    @NamedQuery(name = "eTema.findByTemaNombre", query = "SELECT e FROM eTema e WHERE e.temaNombre = :temaNombre")})
public class eTema implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tema_id")
    private Integer temaId;
    @Basic(optional = false)
    @Column(name = "tema_fecreg")
    @Temporal(TemporalType.DATE)
    private Date temaFecreg;
    @Basic(optional = false)
    @Column(name = "tema_nombre")
    private String temaNombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "tema_descripcion")
    private String temaDescripcion;

    public eTema() {
    }

    public eTema(Integer temaId) {
        this.temaId = temaId;
    }

    public eTema(Integer temaId, Date temaFecreg, String temaNombre, String temaDescripcion) {
        this.temaId = temaId;
        this.temaFecreg = temaFecreg;
        this.temaNombre = temaNombre;
        this.temaDescripcion = temaDescripcion;
    }

    public Integer getTemaId() {
        return temaId;
    }

    public void setTemaId(Integer temaId) {
        this.temaId = temaId;
    }

    public Date getTemaFecreg() {
        return temaFecreg;
    }

    public void setTemaFecreg(Date temaFecreg) {
        this.temaFecreg = temaFecreg;
    }

    public String getTemaNombre() {
        return temaNombre;
    }

    public void setTemaNombre(String temaNombre) {
        this.temaNombre = temaNombre;
    }

    public String getTemaDescripcion() {
        return temaDescripcion;
    }

    public void setTemaDescripcion(String temaDescripcion) {
        this.temaDescripcion = temaDescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (temaId != null ? temaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eTema)) {
            return false;
        }
        eTema other = (eTema) object;
        if ((this.temaId == null && other.temaId != null) || (this.temaId != null && !this.temaId.equals(other.temaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eTema[ temaId=" + temaId + " ]";
    }
    
}
