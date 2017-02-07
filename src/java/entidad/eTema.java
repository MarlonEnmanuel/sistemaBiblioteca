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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MiguelSc
 */
@Entity
@Table(name = "tema")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eTema.findAll", query = "SELECT e FROM eTema e"),
//    @NamedQuery(name = "eTema.findByIdtema", query = "SELECT e FROM eTema e WHERE e.idtema = :idtema"),
//    @NamedQuery(name = "eTema.findByFechareg", query = "SELECT e FROM eTema e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "eTema.findByNombre", query = "SELECT e FROM eTema e WHERE e.nombre = :nombre")})
public class eTema implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtema")
    private Integer idtema;
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
    @ManyToMany(mappedBy = "eTemaList")
    private List<eEjemplar> eEjemplarList;

    public eTema() {
    }

    public eTema(Integer idtema) {
        this.idtema = idtema;
    }

    public eTema(Integer idtema, Date fechareg, String nombre, String descripcion) {
        this.idtema = idtema;
        this.fechareg = fechareg;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdtema() {
        return idtema;
    }

    public void setIdtema(Integer idtema) {
        this.idtema = idtema;
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
        hash += (idtema != null ? idtema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eTema)) {
            return false;
        }
        eTema other = (eTema) object;
        if ((this.idtema == null && other.idtema != null) || (this.idtema != null && !this.idtema.equals(other.idtema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eTema[ idtema=" + idtema + " ]";
    }
    
}
