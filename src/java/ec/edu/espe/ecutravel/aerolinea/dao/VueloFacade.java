/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.dao;

import ec.edu.espe.ecutravel.aerolinea.entities.Vuelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JuanAndresCaspi
 */
@Stateless
public class VueloFacade extends AbstractFacade<Vuelo> {

    @PersistenceContext(unitName = "AerolineaWSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VueloFacade() {
        super(Vuelo.class);
    }
//inicio= fechaPartida  fin=fechaRegreso    origen=OrigenDePartida  destino=Destino

    public List<Vuelo> retrieveVuelosByPara(String inicio, String fin, String origen, String destino) {
        List<Vuelo> vuelos = new ArrayList<>();
        try {
//            vuelos = em.createQuery("SELECT v from Vuelo v where v.ciudadOrigen=:origen AND FUNC('TO_CHAR', v.fechaInicio) like :inicio").setParameter("origen", origen).setParameter("inicio", '%' + inicio + '%')
//                    .getResultList();
            //vuelos = em.createQuery("SELECT v from Vuelo v where FUNC('TO_CHAR', v.fechaInicio) like :fin ").setParameter("fin", '%' + fin + '%').getResultList();
            vuelos = em.createQuery("SELECT v from Vuelo v where v.ciudadOrigen=:origen and FUNC('TO_CHAR', v.fechaInicio) like :inicio or v.ciudadDestino=:destino and FUNC('TO_CHAR', v.fechaFin) like :fin ").setParameter("origen", origen).setParameter("inicio", '%' + inicio + '%')
                    .setParameter("fin", '%' + fin + '%').setParameter("destino", destino)
                    .getResultList();
        } catch (Exception ex) {
            System.out.println("Error al traer vuelos: " + ex);
        }
        return vuelos;
    }

    public Vuelo retrieveVueloById(Integer idVuelo) {
        Vuelo vuelo = new Vuelo();
        vuelo = (Vuelo) em.createNamedQuery("Vuelo.findByVueCodigo").setParameter("vueCodigo", idVuelo).getResultList().get(0);
        return vuelo;
    }
}
