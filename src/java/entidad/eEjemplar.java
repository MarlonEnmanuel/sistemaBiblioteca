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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "ejemplar")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "eEjemplar.findAll", query = "SELECT e FROM eEjemplar e"),
//    @NamedQuery(name = "eEjemplar.findByIdejemplar", query = "SELECT e FROM eEjemplar e WHERE e.idejemplar = :idejemplar"),
//    @NamedQuery(name = "eEjemplar.findByFechareg", query = "SELECT e FROM eEjemplar e WHERE e.fechareg = :fechareg"),
//    @NamedQuery(name = "eEjemplar.findByCodigo", query = "SELECT e FROM eEjemplar e WHERE e.codigo = :codigo"),
//    @NamedQuery(name = "eEjemplar.findByPublicacion", query = "SELECT e FROM eEjemplar e WHERE e.publicacion = :publicacion")})
public class eEjemplar implements Serializable {
//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idejemplar")
    private Integer idejemplar;
    @Basic(optional = false)
    @Column(name = "fechareg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechareg;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Lob
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "autores")
    private String autores;
    @Basic(optional = false)
    @Column(name = "publicacion")
    @Temporal(TemporalType.DATE)
    private Date publicacion;
    @Basic(optional = false)
    @Lob
    @Column(name = "json")
    private String json;
    @Basic(optional = false)
    @Lob
    @Column(name = "urlpdf")
    private String urlpdf;
    @JoinTable(name = "ejemplar_tema", joinColumns = {
        @JoinColumn(name = "idejemplar", referencedColumnName = "idejemplar")}, inverseJoinColumns = {
        @JoinColumn(name = "idtema", referencedColumnName = "idtema")})
    @ManyToMany
    private List<eTema> eTemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idejemplar")
    private List<eCopia> eCopiaList;
    @JoinColumn(name = "idcategoria", referencedColumnName = "idcategoria")
    @ManyToOne(optional = false)
    private eCategoria idcategoria;

    public eEjemplar() {
    }

    public eEjemplar(Integer idejemplar) {
        this.idejemplar = idejemplar;
    }

    public eEjemplar(Integer idejemplar, Date fechareg, String codigo, String titulo, String autores, Date publicacion, String json, String urlpdf) {
        this.idejemplar = idejemplar;
        this.fechareg = fechareg;
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.publicacion = publicacion;
        this.json = json;
        this.urlpdf = urlpdf;
    }
    
    public eEjemplar(Integer idejemplar, Date fechareg, String codigo, String titulo, String autores, Date publicacion, String json, String urlpdf,eCategoria cat) {
        this.idejemplar = idejemplar;
        this.fechareg = fechareg;
        this.codigo = codigo;
        this.titulo = titulo;
        this.autores = autores;
        this.publicacion = publicacion;
        this.json = json;
        this.urlpdf = urlpdf;
        this.idcategoria=cat;
    }

    public Integer getIdejemplar() {
        return idejemplar;
    }

    public void setIdejemplar(Integer idejemplar) {
        this.idejemplar = idejemplar;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public Date getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Date publicacion) {
        this.publicacion = publicacion;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getUrlpdf() {
        return urlpdf;
    }

    public void setUrlpdf(String urlpdf) {
        this.urlpdf = urlpdf;
    }

    @XmlTransient
    public List<eTema> getETemaList() {
        return eTemaList;
    }

    public void setETemaList(List<eTema> eTemaList) {
        this.eTemaList = eTemaList;
    }

    @XmlTransient
    public List<eCopia> getECopiaList() {
        return eCopiaList;
    }

    public void setECopiaList(List<eCopia> eCopiaList) {
        this.eCopiaList = eCopiaList;
    }

    public eCategoria getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(eCategoria idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idejemplar != null ? idejemplar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof eEjemplar)) {
            return false;
        }
        eEjemplar other = (eEjemplar) object;
        if ((this.idejemplar == null && other.idejemplar != null) || (this.idejemplar != null && !this.idejemplar.equals(other.idejemplar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidad.eEjemplar[ idejemplar=" + idejemplar + " ]";
    }
    
}
