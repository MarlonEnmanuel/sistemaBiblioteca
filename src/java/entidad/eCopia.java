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
@Table(name = "copia")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eCopia.findAll", query = "SELECT e FROM eCopia e"),
//    @NamedQuery(name = "eCopia.findByCopiaId", query = "SELECT e FROM eCopia e WHERE e.copiaId = :copiaId"),
//    @NamedQuery(name = "eCopia.findByCopiaFecreg", query = "SELECT e FROM eCopia e WHERE e.copiaFecreg = :copiaFecreg"),
//    @NamedQuery(name = "eCopia.findByCopiaMod", query = "SELECT e FROM eCopia e WHERE e.copiaMod = :copiaMod"),
//    @NamedQuery(name = "eCopia.findByCopiaEstado", query = "SELECT e FROM eCopia e WHERE e.copiaEstado = :copiaEstado"),
//    @NamedQuery(name = "eCopia.findByCopiaDisponible", query = "SELECT e FROM eCopia e WHERE e.copiaDisponible = :copiaDisponible"),
//    @NamedQuery(name = "eCopia.findByEjemplarId", query = "SELECT e FROM eCopia e WHERE e.ejemplarId = :ejemplarId")})
public class eCopia implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "copia_id")
    private Integer copiaId;
    @Basic(optional = false)
    @Column(name = "copia_fecreg")
    @Temporal(TemporalType.DATE)
    private Date copiaFecreg;
    @Basic(optional = false)
    @Column(name = "copia_mod")
    @Temporal(TemporalType.DATE)
    private Date copiaMod;
    @Basic(optional = false)
    @Lob
    @Column(name = "copia_codigo")
    private String copiaCodigo;
    @Basic(optional = false)
    @Column(name = "copia_estado")
    private boolean copiaEstado;
    @Basic(optional = false)
    @Column(name = "copia_disponible")
    private boolean copiaDisponible;
    @Basic(optional = false)
    @Column(name = "ejemplar_id")
    private int ejemplarId;

    public eCopia() {
    }

    public eCopia(Integer copiaId) {
        this.copiaId = copiaId;
    }

    public eCopia(Integer copiaId, Date copiaFecreg, Date copiaMod, String copiaCodigo, boolean copiaEstado, boolean copiaDisponible, int ejemplarId) {
        this.copiaId = copiaId;
        this.copiaFecreg = copiaFecreg;
        this.copiaMod = copiaMod;
        this.copiaCodigo = copiaCodigo;
        this.copiaEstado = copiaEstado;
        this.copiaDisponible = copiaDisponible;
        this.ejemplarId = ejemplarId;
    }

    public Integer getCopiaId() {
        return copiaId;
    }

    public void setCopiaId(Integer copiaId) {
        this.copiaId = copiaId;
    }

    public Date getCopiaFecreg() {
        return copiaFecreg;
    }

    public void setCopiaFecreg(Date copiaFecreg) {
        this.copiaFecreg = copiaFecreg;
    }

    public Date getCopiaMod() {
        return copiaMod;
    }

    public void setCopiaMod(Date copiaMod) {
        this.copiaMod = copiaMod;
    }

    public String getCopiaCodigo() {
        return copiaCodigo;
    }

    public void setCopiaCodigo(String copiaCodigo) {
        this.copiaCodigo = copiaCodigo;
    }

    public boolean getCopiaEstado() {
        return copiaEstado;
    }

    public void setCopiaEstado(boolean copiaEstado) {
        this.copiaEstado = copiaEstado;
    }

    public boolean getCopiaDisponible() {
        return copiaDisponible;
    }

    public void setCopiaDisponible(boolean copiaDisponible) {
        this.copiaDisponible = copiaDisponible;
    }

    public int getEjemplarId() {
        return ejemplarId;
    }

    public void setEjemplarId(int ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (copiaId != null ? copiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eCopia)) {
            return false;
        }
        eCopia other = (eCopia) object;
        if ((this.copiaId == null && other.copiaId != null) || (this.copiaId != null && !this.copiaId.equals(other.copiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eCopia[ copiaId=" + copiaId + " ]";
    }
    
}
