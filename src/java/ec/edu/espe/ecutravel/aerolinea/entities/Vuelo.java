/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JuanAndresCaspi
 */
@Entity
@Table(name = "VUELO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vuelo.findAll", query = "SELECT v FROM Vuelo v"),
    @NamedQuery(name = "Vuelo.findByVueCodigo", query = "SELECT v FROM Vuelo v WHERE v.vueCodigo = :vueCodigo"),
    @NamedQuery(name = "Vuelo.findByFechaInicio", query = "SELECT v FROM Vuelo v WHERE v.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Vuelo.findByFechaFin", query = "SELECT v FROM Vuelo v WHERE v.fechaFin = :fechaFin"),
    @NamedQuery(name = "Vuelo.findByPrecio", query = "SELECT v FROM Vuelo v WHERE v.precio = :precio"),
    @NamedQuery(name = "Vuelo.findByCiudadOrigen", query = "SELECT v FROM Vuelo v WHERE v.ciudadOrigen = :ciudadOrigen"),
    @NamedQuery(name = "Vuelo.findByCiudadDestino", query = "SELECT v FROM Vuelo v WHERE v.ciudadDestino = :ciudadDestino"),
    @NamedQuery(name = "Vuelo.findByParameters", query = "SELECT v FROM Vuelo v where v.fechaInicio LIKE ':fechaInicio%'"),
    @NamedQuery(name = "Vuelo.findByAerolinea", query = "SELECT v FROM Vuelo v WHERE v.aerolinea = :aerolinea")})
public class Vuelo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "vueIncre", sequenceName = "VUE_INCREM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vueIncre")
    @Basic(optional = false)
    @NotNull
    @Column(name = "VUE_CODIGO")
    private Integer vueCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CIUDAD_ORIGEN")
    private String ciudadOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CIUDAD_DESTINO")
    private String ciudadDestino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AEROLINEA")
    private String aerolinea;
    @OneToMany(mappedBy = "vueCodigo")
    private List<Boleto> boletoList;

    public Vuelo() {
    }

    public Vuelo(Integer vueCodigo) {
        this.vueCodigo = vueCodigo;
    }

    public Vuelo(Integer vueCodigo, Date fechaInicio, Date fechaFin, BigDecimal precio, String ciudadOrigen, String ciudadDestino, String aerolinea) {
        this.vueCodigo = vueCodigo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.aerolinea = aerolinea;
    }

    public Integer getVueCodigo() {
        return vueCodigo;
    }

    public void setVueCodigo(Integer vueCodigo) {
        this.vueCodigo = vueCodigo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(String ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(String ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vueCodigo != null ? vueCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vuelo)) {
            return false;
        }
        Vuelo other = (Vuelo) object;
        if ((this.vueCodigo == null && other.vueCodigo != null) || (this.vueCodigo != null && !this.vueCodigo.equals(other.vueCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.ecutravel.aerolinea.entities.Vuelo[ vueCodigo=" + vueCodigo + " ]";
    }

}
