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
@Table(name = "pesona")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ePesona.findAll", query = "SELECT e FROM ePesona e"),
//    @NamedQuery(name = "ePesona.findByPersonaId", query = "SELECT e FROM ePesona e WHERE e.personaId = :personaId"),
//    @NamedQuery(name = "ePesona.findByPersonaCodigo", query = "SELECT e FROM ePesona e WHERE e.personaCodigo = :personaCodigo"),
//    @NamedQuery(name = "ePesona.findByPersonaNombres", query = "SELECT e FROM ePesona e WHERE e.personaNombres = :personaNombres"),
//    @NamedQuery(name = "ePesona.findByPersonaApellidos", query = "SELECT e FROM ePesona e WHERE e.personaApellidos = :personaApellidos"),
//    @NamedQuery(name = "ePesona.findByPersonaEscuela", query = "SELECT e FROM ePesona e WHERE e.personaEscuela = :personaEscuela"),
//    @NamedQuery(name = "ePesona.findByPersonaFacultad", query = "SELECT e FROM ePesona e WHERE e.personaFacultad = :personaFacultad"),
//    @NamedQuery(name = "ePesona.findByPersonaTipo", query = "SELECT e FROM ePesona e WHERE e.personaTipo = :personaTipo")})
public class ePesona implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "persona_id")
    private Integer personaId;
    @Basic(optional = false)
    @Column(name = "persona_codigo")
    private String personaCodigo;
    @Basic(optional = false)
    @Column(name = "persona_nombres")
    private String personaNombres;
    @Basic(optional = false)
    @Column(name = "persona_apellidos")
    private String personaApellidos;
    @Basic(optional = false)
    @Column(name = "persona_escuela")
    private String personaEscuela;
    @Basic(optional = false)
    @Column(name = "persona_facultad")
    private String personaFacultad;
    @Basic(optional = false)
    @Column(name = "persona_tipo")
    private String personaTipo;

    public ePesona() {
    }

    public ePesona(Integer personaId) {
        this.personaId = personaId;
    }

    public ePesona(Integer personaId, String personaCodigo, String personaNombres, String personaApellidos, String personaEscuela, String personaFacultad, String personaTipo) {
        this.personaId = personaId;
        this.personaCodigo = personaCodigo;
        this.personaNombres = personaNombres;
        this.personaApellidos = personaApellidos;
        this.personaEscuela = personaEscuela;
        this.personaFacultad = personaFacultad;
        this.personaTipo = personaTipo;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getPersonaCodigo() {
        return personaCodigo;
    }

    public void setPersonaCodigo(String personaCodigo) {
        this.personaCodigo = personaCodigo;
    }

    public String getPersonaNombres() {
        return personaNombres;
    }

    public void setPersonaNombres(String personaNombres) {
        this.personaNombres = personaNombres;
    }

    public String getPersonaApellidos() {
        return personaApellidos;
    }

    public void setPersonaApellidos(String personaApellidos) {
        this.personaApellidos = personaApellidos;
    }

    public String getPersonaEscuela() {
        return personaEscuela;
    }

    public void setPersonaEscuela(String personaEscuela) {
        this.personaEscuela = personaEscuela;
    }

    public String getPersonaFacultad() {
        return personaFacultad;
    }

    public void setPersonaFacultad(String personaFacultad) {
        this.personaFacultad = personaFacultad;
    }

    public String getPersonaTipo() {
        return personaTipo;
    }

    public void setPersonaTipo(String personaTipo) {
        this.personaTipo = personaTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaId != null ? personaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ePesona)) {
            return false;
        }
        ePesona other = (ePesona) object;
        if ((this.personaId == null && other.personaId != null) || (this.personaId != null && !this.personaId.equals(other.personaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.ePesona[ personaId=" + personaId + " ]";
    }
    
}
