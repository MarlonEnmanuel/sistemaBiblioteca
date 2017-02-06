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
//@Table(name = "ejemplar")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eEjemplar.findAll", query = "SELECT e FROM eEjemplar e"),
//    @NamedQuery(name = "eEjemplar.findByEjemplarId", query = "SELECT e FROM eEjemplar e WHERE e.ejemplarId = :ejemplarId"),
//    @NamedQuery(name = "eEjemplar.findByEjemplarCodigo", query = "SELECT e FROM eEjemplar e WHERE e.ejemplarCodigo = :ejemplarCodigo"),
//    @NamedQuery(name = "eEjemplar.findByEjemplarPublicacion", query = "SELECT e FROM eEjemplar e WHERE e.ejemplarPublicacion = :ejemplarPublicacion"),
//    @NamedQuery(name = "eEjemplar.findByCategoriaId", query = "SELECT e FROM eEjemplar e WHERE e.categoriaId = :categoriaId")})
public class eEjemplar implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ejemplar_id")
    private Integer ejemplarId;
    @Basic(optional = false)
    @Column(name = "ejemplar_codigo")
    private String ejemplarCodigo;
    @Basic(optional = false)
    @Lob
    @Column(name = "ejemplar_titulo")
    private String ejemplarTitulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "ejemplar_autores")
    private String ejemplarAutores;
    @Basic(optional = false)
    @Column(name = "ejemplar_publicacion")
    @Temporal(TemporalType.DATE)
    private Date ejemplarPublicacion;
    @Basic(optional = false)
    @Column(name = "categoria_id")
    private int categoriaId;

    public eEjemplar() {
    }

    public eEjemplar(Integer ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    public eEjemplar(Integer ejemplarId, String ejemplarCodigo, String ejemplarTitulo, String ejemplarAutores, Date ejemplarPublicacion, int categoriaId) {
        this.ejemplarId = ejemplarId;
        this.ejemplarCodigo = ejemplarCodigo;
        this.ejemplarTitulo = ejemplarTitulo;
        this.ejemplarAutores = ejemplarAutores;
        this.ejemplarPublicacion = ejemplarPublicacion;
        this.categoriaId = categoriaId;
    }

    public Integer getEjemplarId() {
        return ejemplarId;
    }

    public void setEjemplarId(Integer ejemplarId) {
        this.ejemplarId = ejemplarId;
    }

    public String getEjemplarCodigo() {
        return ejemplarCodigo;
    }

    public void setEjemplarCodigo(String ejemplarCodigo) {
        this.ejemplarCodigo = ejemplarCodigo;
    }

    public String getEjemplarTitulo() {
        return ejemplarTitulo;
    }

    public void setEjemplarTitulo(String ejemplarTitulo) {
        this.ejemplarTitulo = ejemplarTitulo;
    }

    public String getEjemplarAutores() {
        return ejemplarAutores;
    }

    public void setEjemplarAutores(String ejemplarAutores) {
        this.ejemplarAutores = ejemplarAutores;
    }

    public Date getEjemplarPublicacion() {
        return ejemplarPublicacion;
    }

    public void setEjemplarPublicacion(Date ejemplarPublicacion) {
        this.ejemplarPublicacion = ejemplarPublicacion;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ejemplarId != null ? ejemplarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eEjemplar)) {
            return false;
        }
        eEjemplar other = (eEjemplar) object;
        if ((this.ejemplarId == null && other.ejemplarId != null) || (this.ejemplarId != null && !this.ejemplarId.equals(other.ejemplarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eEjemplar[ ejemplarId=" + ejemplarId + " ]";
    }
    
}
