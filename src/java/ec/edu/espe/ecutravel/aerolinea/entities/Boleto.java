/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JuanAndresCaspi
 */
@Entity
@Table(name = "BOLETO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b"),
    @NamedQuery(name = "Boleto.findByCity", query = "SELECT b FROM Boleto b"),
    @NamedQuery(name = "Boleto.findByVuelo", query = "SELECT b FROM Boleto b WHERE b.vueCodigo = :vuelo"),
    @NamedQuery(name = "Boleto.findByBolCodigo", query = "SELECT b FROM Boleto b WHERE b.bolCodigo = :bolCodigo"),
    @NamedQuery(name = "Boleto.findByPersona", query = "SELECT b FROM Boleto b WHERE b.persona = :persona"),
    @NamedQuery(name = "Boleto.findByPaquete", query = "SELECT b FROM Boleto b WHERE b.paquete = :paquete")})
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "bolIncre", sequenceName = "BOL_INCREM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bolIncre")
    @Basic(optional = false)
    @NotNull
    @Column(name = "BOL_CODIGO")
    private Integer bolCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERSONA")
    private BigInteger persona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAQUETE")
    private BigInteger paquete;
    @JoinColumn(name = "ASI_CODIGO", referencedColumnName = "ASI_CODIGO")
    @ManyToOne
    private Asiento asiCodigo;
    @JoinColumn(name = "VUE_CODIGO", referencedColumnName = "VUE_CODIGO")
    @ManyToOne
    private Vuelo vueCodigo;

    public Boleto() {
    }

    public Boleto(Integer bolCodigo) {
        this.bolCodigo = bolCodigo;
    }

    public Boleto(Integer bolCodigo, BigInteger persona, BigInteger paquete) {
        this.bolCodigo = bolCodigo;
        this.persona = persona;
        this.paquete = paquete;
    }

    public Integer getBolCodigo() {
        return bolCodigo;
    }

    public void setBolCodigo(Integer bolCodigo) {
        this.bolCodigo = bolCodigo;
    }

    public BigInteger getPersona() {
        return persona;
    }

    public void setPersona(BigInteger persona) {
        this.persona = persona;
    }

    public BigInteger getPaquete() {
        return paquete;
    }

    public void setPaquete(BigInteger paquete) {
        this.paquete = paquete;
    }

    public Asiento getAsiCodigo() {
        return asiCodigo;
    }

    public void setAsiCodigo(Asiento asiCodigo) {
        this.asiCodigo = asiCodigo;
    }

    public Vuelo getVueCodigo() {
        return vueCodigo;
    }

    public void setVueCodigo(Vuelo vueCodigo) {
        this.vueCodigo = vueCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bolCodigo != null ? bolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.bolCodigo == null && other.bolCodigo != null) || (this.bolCodigo != null && !this.bolCodigo.equals(other.bolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.ecutravel.aerolinea.entities.Boleto[ bolCodigo=" + bolCodigo + " ]";
    }
    
}
