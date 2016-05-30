/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JuanAndresCaspi
 */
@Entity
@Table(name = "ASIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asiento.findAll", query = "SELECT a FROM Asiento a"),
    @NamedQuery(name = "Asiento.findByAsiCodigo", query = "SELECT a FROM Asiento a WHERE a.asiCodigo = :asiCodigo"),
    @NamedQuery(name = "Asiento.findByNumero", query = "SELECT a FROM Asiento a WHERE a.numero = :numero")})
public class Asiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "asiIncre", sequenceName = "ASI_INCREM", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asiIncre")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ASI_CODIGO")
    private Integer asiCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private BigInteger numero;
    @OneToMany(mappedBy = "asiCodigo")
    private List<Boleto> boletoList;

    public Asiento() {
    }

    public Asiento(Integer asiCodigo) {
        this.asiCodigo = asiCodigo;
    }

    public Asiento(Integer asiCodigo, BigInteger numero) {
        this.asiCodigo = asiCodigo;
        this.numero = numero;
    }

    public Integer getAsiCodigo() {
        return asiCodigo;
    }

    public void setAsiCodigo(Integer asiCodigo) {
        this.asiCodigo = asiCodigo;
    }

    public BigInteger getNumero() {
        return numero;
    }

    public void setNumero(BigInteger numero) {
        this.numero = numero;
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
        hash += (asiCodigo != null ? asiCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asiento)) {
            return false;
        }
        Asiento other = (Asiento) object;
        if ((this.asiCodigo == null && other.asiCodigo != null) || (this.asiCodigo != null && !this.asiCodigo.equals(other.asiCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.espe.ecutravel.aerolinea.entities.Asiento[ asiCodigo=" + asiCodigo + " ]";
    }
    
}
