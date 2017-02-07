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
import javax.persistence.Lob;
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
@Table(name = "categoria")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eCategoria.findAll", query = "SELECT e FROM eCategoria e"),
//    @NamedQuery(name = "eCategoria.findByIdcategoria", query = "SELECT e FROM eCategoria e WHERE e.idcategoria = :idcategoria"),
//    @NamedQuery(name = "eCategoria.findByFechareg", query = "SELECT e FROM eCategoria e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "eCategoria.findByNombre", query = "SELECT e FROM eCategoria e WHERE e.nombre = :nombre")})
public class eCategoria implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategoria")
    private Integer idcategoria;
    @Basic(optional = false)
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Lob
    @Column(name = "datos")
    private String datos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategoria")
    private List<eEjemplar> eEjemplarList;

    public eCategoria() {
    }

    public eCategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public eCategoria(Integer idcategoria, Date fechareg, String nombre, String descripcion, String datos) {
        this.idcategoria = idcategoria;
        this.fechareg = fechareg;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.datos = datos;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    @XmlTransient
    public List<eEjemplar> getEEjemplarList() {
        return eEjemplarList;
    }

    public void setEEjemplarList(List<eEjemplar> eEjemplarList) {
        this.eEjemplarList = eEjemplarList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eCategoria)) {
            return false;
        }
        eCategoria other = (eCategoria) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eCategoria[ idcategoria=" + idcategoria + " ]";
    }
    
}
