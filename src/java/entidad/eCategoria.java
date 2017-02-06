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
@Table(name = "categoria")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eCategoria.findAll", query = "SELECT e FROM eCategoria e"),
//    @NamedQuery(name = "eCategoria.findByCategoriaId", query = "SELECT e FROM eCategoria e WHERE e.categoriaId = :categoriaId"),
//    @NamedQuery(name = "eCategoria.findByCategoriaFecreg", query = "SELECT e FROM eCategoria e WHERE e.categoriaFecreg = :categoriaFecreg"),
//    @NamedQuery(name = "eCategoria.findByCategoriaNombre", query = "SELECT e FROM eCategoria e WHERE e.categoriaNombre = :categoriaNombre")})
public class eCategoria implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoria_id")
    private Integer categoriaId;
    @Basic(optional = false)
    @Column(name = "categoria_fecreg")
    @Temporal(TemporalType.DATE)
    private Date categoriaFecreg;
    @Basic(optional = false)
    @Column(name = "categoria_nombre")
    private String categoriaNombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "categoria_descripcion")
    private String categoriaDescripcion;

    public eCategoria() {
    }

    public eCategoria(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public eCategoria(Integer categoriaId, Date categoriaFecreg, String categoriaNombre, String categoriaDescripcion) {
        this.categoriaId = categoriaId;
        this.categoriaFecreg = categoriaFecreg;
        this.categoriaNombre = categoriaNombre;
        this.categoriaDescripcion = categoriaDescripcion;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Date getCategoriaFecreg() {
        return categoriaFecreg;
    }

    public void setCategoriaFecreg(Date categoriaFecreg) {
        this.categoriaFecreg = categoriaFecreg;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getCategoriaDescripcion() {
        return categoriaDescripcion;
    }

    public void setCategoriaDescripcion(String categoriaDescripcion) {
        this.categoriaDescripcion = categoriaDescripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoriaId != null ? categoriaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eCategoria)) {
            return false;
        }
        eCategoria other = (eCategoria) object;
        if ((this.categoriaId == null && other.categoriaId != null) || (this.categoriaId != null && !this.categoriaId.equals(other.categoriaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eCategoria[ categoriaId=" + categoriaId + " ]";
    }
    
}
