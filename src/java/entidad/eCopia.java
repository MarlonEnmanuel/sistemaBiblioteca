/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MiguelSc
 */
@Entity
@Table(name = "copia")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eCopia.findAll", query = "SELECT e FROM eCopia e"),
//    @NamedQuery(name = "eCopia.findByIdcopia", query = "SELECT e FROM eCopia e WHERE e.idcopia = :idcopia"),
//    @NamedQuery(name = "eCopia.findByFechareg", query = "SELECT e FROM eCopia e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "eCopia.findByCodigo", query = "SELECT e FROM eCopia e WHERE e.codigo = :codigo"),
//    @NamedQuery(name = "eCopia.findByEstado", query = "SELECT e FROM eCopia e WHERE e.estado = :estado"),
//    @NamedQuery(name = "eCopia.findByDisponible", query = "SELECT e FROM eCopia e WHERE e.disponible = :disponible")})
public class eCopia implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcopia")
    private Integer idcopia;
    @Basic(optional = false)
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @Column(name = "disponible")
    private boolean disponible;
    @JoinColumn(name = "idejemplar", referencedColumnName = "idejemplar")
    @ManyToOne(optional = false)
    private eEjemplar idejemplar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcopia")
    private List<ePrestamo> ePrestamoList;

    public eCopia() {
    }

    public eCopia(Integer idcopia) {
        this.idcopia = idcopia;
    }

    public eCopia(Integer idcopia, Date fechareg, String codigo, boolean estado, boolean disponible) {
        this.idcopia = idcopia;
        this.fechareg = fechareg;
        this.codigo = codigo;
        this.estado = estado;
        this.disponible = disponible;
    }

    public Integer getIdcopia() {
        return idcopia;
    }

    public void setIdcopia(Integer idcopia) {
        this.idcopia = idcopia;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public eEjemplar getIdejemplar() {
        return idejemplar;
    }

    public void setIdejemplar(eEjemplar idejemplar) {
        this.idejemplar = idejemplar;
    }

    @XmlTransient
    public List<ePrestamo> getEPrestamoList() {
        return ePrestamoList;
    }

    public void setEPrestamoList(List<ePrestamo> ePrestamoList) {
        this.ePrestamoList = ePrestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcopia != null ? idcopia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eCopia)) {
            return false;
        }
        eCopia other = (eCopia) object;
        if ((this.idcopia == null && other.idcopia != null) || (this.idcopia != null && !this.idcopia.equals(other.idcopia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eCopia[ idcopia=" + idcopia + " ]";
    }
    
}
