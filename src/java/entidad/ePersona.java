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
@Table(name = "persona")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ePersona.findAll", query = "SELECT e FROM ePersona e"),
//    @NamedQuery(name = "ePersona.findByIdpersona", query = "SELECT e FROM ePersona e WHERE e.idpersona = :idpersona"),
//    @NamedQuery(name = "ePersona.findByFechareg", query = "SELECT e FROM ePersona e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "ePersona.findByCodigo", query = "SELECT e FROM ePersona e WHERE e.codigo = :codigo"),
//    @NamedQuery(name = "ePersona.findByNombres", query = "SELECT e FROM ePersona e WHERE e.nombres = :nombres"),
//    @NamedQuery(name = "ePersona.findByApellidos", query = "SELECT e FROM ePersona e WHERE e.apellidos = :apellidos"),
//    @NamedQuery(name = "ePersona.findByEscuela", query = "SELECT e FROM ePersona e WHERE e.escuela = :escuela"),
//    @NamedQuery(name = "ePersona.findByFacultad", query = "SELECT e FROM ePersona e WHERE e.facultad = :facultad"),
//    @NamedQuery(name = "ePersona.findByTipo", query = "SELECT e FROM ePersona e WHERE e.tipo = :tipo")})
public class ePersona implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersona")
    private Integer idpersona;
    @Basic(optional = false)
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "escuela")
    private String escuela;
    @Basic(optional = false)
    @Column(name = "facultad")
    private String facultad;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersona")
    private List<eUsuario> eUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersona")
    private List<ePrestamo> ePrestamoList;

    public ePersona() {
    }

    public ePersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public ePersona(Integer idpersona, Date fechareg, String codigo, String nombres, String apellidos, String escuela, String facultad, String tipo) {
        this.idpersona = idpersona;
        this.fechareg = fechareg;
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.escuela = escuela;
        this.facultad = facultad;
        this.tipo = tipo;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<eUsuario> getEUsuarioList() {
        return eUsuarioList;
    }

    public void setEUsuarioList(List<eUsuario> eUsuarioList) {
        this.eUsuarioList = eUsuarioList;
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
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ePersona)) {
            return false;
        }
        ePersona other = (ePersona) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.ePersona[ idpersona=" + idpersona + " ]";
    }
    
}
