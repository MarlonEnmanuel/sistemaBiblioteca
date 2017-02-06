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
@Table(name = "usuario")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eUsuario.findAll", query = "SELECT e FROM eUsuario e"),
//    @NamedQuery(name = "eUsuario.findByUsuarioId", query = "SELECT e FROM eUsuario e WHERE e.usuarioId = :usuarioId"),
//    @NamedQuery(name = "eUsuario.findByUsuarioFecreg", query = "SELECT e FROM eUsuario e WHERE e.usuarioFecreg = :usuarioFecreg"),
//    @NamedQuery(name = "eUsuario.findByUsuarioUser", query = "SELECT e FROM eUsuario e WHERE e.usuarioUser = :usuarioUser"),
//    @NamedQuery(name = "eUsuario.findByUsuarioPass", query = "SELECT e FROM eUsuario e WHERE e.usuarioPass = :usuarioPass"),
//    @NamedQuery(name = "eUsuario.findByUsuarioEmail", query = "SELECT e FROM eUsuario e WHERE e.usuarioEmail = :usuarioEmail"),
//    @NamedQuery(name = "eUsuario.findByUsuarioTipo", query = "SELECT e FROM eUsuario e WHERE e.usuarioTipo = :usuarioTipo"),
//    @NamedQuery(name = "eUsuario.findByUsuarioEstado", query = "SELECT e FROM eUsuario e WHERE e.usuarioEstado = :usuarioEstado"),
//    @NamedQuery(name = "eUsuario.findByPersonaId", query = "SELECT e FROM eUsuario e WHERE e.personaId = :personaId")})
public class eUsuario implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private Integer usuarioId;
    @Basic(optional = false)
    @Column(name = "usuario_fecreg")
    @Temporal(TemporalType.DATE)
    private Date usuarioFecreg;
    @Basic(optional = false)
    @Column(name = "usuario_user")
    private String usuarioUser;
    @Basic(optional = false)
    @Column(name = "usuario_pass")
    private String usuarioPass;
    @Basic(optional = false)
    @Column(name = "usuario_email")
    private String usuarioEmail;
    @Basic(optional = false)
    @Column(name = "usuario_tipo")
    private String usuarioTipo;
    @Basic(optional = false)
    @Column(name = "usuario_estado")
    private boolean usuarioEstado;
    @Basic(optional = false)
    @Column(name = "persona_id")
    private int personaId;

    public eUsuario() {
    }

    public eUsuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public eUsuario(Integer usuarioId, Date usuarioFecreg, String usuarioUser, String usuarioPass, String usuarioEmail, String usuarioTipo, boolean usuarioEstado, int personaId) {
        this.usuarioId = usuarioId;
        this.usuarioFecreg = usuarioFecreg;
        this.usuarioUser = usuarioUser;
        this.usuarioPass = usuarioPass;
        this.usuarioEmail = usuarioEmail;
        this.usuarioTipo = usuarioTipo;
        this.usuarioEstado = usuarioEstado;
        this.personaId = personaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getUsuarioFecreg() {
        return usuarioFecreg;
    }

    public void setUsuarioFecreg(Date usuarioFecreg) {
        this.usuarioFecreg = usuarioFecreg;
    }

    public String getUsuarioUser() {
        return usuarioUser;
    }

    public void setUsuarioUser(String usuarioUser) {
        this.usuarioUser = usuarioUser;
    }

    public String getUsuarioPass() {
        return usuarioPass;
    }

    public void setUsuarioPass(String usuarioPass) {
        this.usuarioPass = usuarioPass;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(String usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    public boolean getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(boolean usuarioEstado) {
        this.usuarioEstado = usuarioEstado;
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
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eUsuario)) {
            return false;
        }
        eUsuario other = (eUsuario) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eUsuario[ usuarioId=" + usuarioId + " ]";
    }
    
}
