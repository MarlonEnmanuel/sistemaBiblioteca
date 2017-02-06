/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author MiguelSc
 */
@Entity
@Table(name = "ejemplartema")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eEjemplartema.findAll", query = "SELECT e FROM eEjemplartema e"),
//    @NamedQuery(name = "eEjemplartema.findByEjemplartemaId", query = "SELECT e FROM eEjemplartema e WHERE e.ejemplartemaId = :ejemplartemaId"),
//    @NamedQuery(name = "eEjemplartema.findByEjemplarId", query = "SELECT e FROM eEjemplartema e WHERE e.ejemplarId = :ejemplarId"),
//    @NamedQuery(name = "eEjemplartema.findByTemaId", query = "SELECT e FROM eEjemplartema e WHERE e.temaId = :temaId")})
public class eEjemplartema implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ejemplartema_id")
    private Integer ejemplartemaId;
    @Basic(optional = false)
    @Column(name = "ejemplar_id")
    private int ejemplarId;
    @Basic(optional = false)
    @Column(name = "tema_id")
    private int temaId;

    public eEjemplartema() {
    }

    public eEjemplartema(Integer ejemplartemaId) {
        this.ejemplartemaId = ejemplartemaId;
    }

    public eEjemplartema(Integer ejemplartemaId, int ejemplarId, int temaId) {
        this.ejemplartemaId = ejemplartemaId;
        this.ejemplarId = ejemplarId;
        this.temaId = temaId;
    }

    public Integer getEjemplartemaId() {
        return ejemplartemaId;
    }

    public void setEjemplartemaId(Integer ejemplartemaId) {
        this.ejemplartemaId = ejemplartemaId;
    }

    public int getEjemplarId() {
        return ejemplarId;
    }

    public void setEjemplarId(int ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    public int getTemaId() {
        return temaId;
    }

    public void setTemaId(int temaId) {
        this.temaId = temaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejemplartemaId != null ? ejemplartemaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eEjemplartema)) {
            return false;
        }
        eEjemplartema other = (eEjemplartema) object;
        if ((this.ejemplartemaId == null && other.ejemplartemaId != null) || (this.ejemplartemaId != null && !this.ejemplartemaId.equals(other.ejemplartemaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eEjemplartema[ ejemplartemaId=" + ejemplartemaId + " ]";
    }
    
}
