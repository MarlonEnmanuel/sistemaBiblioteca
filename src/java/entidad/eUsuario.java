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
@Table(name = "usuario")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eUsuario.findAll", query = "SELECT e FROM eUsuario e"),
//    @NamedQuery(name = "eUsuario.findByIdusuario", query = "SELECT e FROM eUsuario e WHERE e.idusuario = :idusuario"),
//    @NamedQuery(name = "eUsuario.findByFechareg", query = "SELECT e FROM eUsuario e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "eUsuario.findByUser", query = "SELECT e FROM eUsuario e WHERE e.user = :user"),
//    @NamedQuery(name = "eUsuario.findByPass", query = "SELECT e FROM eUsuario e WHERE e.pass = :pass"),
//    @NamedQuery(name = "eUsuario.findByTipo", query = "SELECT e FROM eUsuario e WHERE e.tipo = :tipo"),
//    @NamedQuery(name = "eUsuario.findByEstado", query = "SELECT e FROM eUsuario e WHERE e.estado = :estado")})
public class eUsuario implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Basic(optional = false)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private ePersona idpersona;

    public eUsuario() {
    }

    public eUsuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public eUsuario(Integer idusuario, Date fechareg, String user, String pass, String tipo, boolean estado) {
        this.idusuario = idusuario;
        this.fechareg = fechareg;
        this.user = user;
        this.pass = pass;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Date getFechareg() {
        return fechareg;
    }

    public void setFechareg(Date fechareg) {
        this.fechareg = fechareg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ePersona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(ePersona idpersona) {
        this.idpersona = idpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eUsuario)) {
            return false;
        }
        eUsuario other = (eUsuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eUsuario[ idusuario=" + idusuario + " ]";
    }
    
}
