/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.dao;

import ec.edu.espe.ecutravel.aerolinea.entities.Boleto;
import ec.edu.espe.ecutravel.aerolinea.entities.Vuelo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JuanAndresCaspi
 */
@Stateless
public class BoletoFacade extends AbstractFacade<Boleto> {

    @PersistenceContext(unitName = "AerolineaTameWSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoletoFacade() {
        super(Boleto.class);
    }

    public List<Boleto> retrieveBoletosByIdVuelo(Vuelo vuelo) {
        List<Boleto> boletos = new ArrayList<>();
        boletos = (List<Boleto>) em.createNamedQuery("Boleto.findByVuelo")
                .setParameter("vuelo", vuelo).getResultList();
        return boletos;
    }

    public List<Boleto> retrieveBoletosByCedula(String identificacionPersona) {
        List<Boleto> boletos = new ArrayList<>();
        try {
            boletos = (List<Boleto>) em.createNamedQuery("Boleto.findByPersona")
                    .setParameter("persona", new BigInteger(identificacionPersona)).getResultList();
        } catch (Exception ex) {
            System.out.println("Error al traer boletos de: " + identificacionPersona + "desde facade" + ex);
        }
        return boletos;
    }

}
