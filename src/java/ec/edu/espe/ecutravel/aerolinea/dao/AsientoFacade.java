/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.dao;

import ec.edu.espe.ecutravel.aerolinea.entities.Asiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JuanAndresCaspi
 */
@Stateless
public class AsientoFacade extends AbstractFacade<Asiento> {
    
    @PersistenceContext(unitName = "AerolineaWSPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public AsientoFacade() {
        super(Asiento.class);
    }
    
    public Asiento retrieveAsientoById(Integer numeroAsiento) {
        Asiento asiento = new Asiento();
        asiento = (Asiento) em.createNamedQuery("Asiento.findByNumero").setParameter("numero", numeroAsiento).getResultList().get(0);
        return asiento;
    }
}
