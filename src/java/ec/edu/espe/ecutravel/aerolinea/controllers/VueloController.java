/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.controllers;

import ec.edu.espe.ecutravel.aerolinea.dao.VueloFacade;
import ec.edu.espe.ecutravel.aerolinea.entities.Boleto;
import ec.edu.espe.ecutravel.aerolinea.entities.Vuelo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JuanAndresCaspi
 */
@Stateless
public class VueloController {

    @EJB
    private VueloFacade facade;

    @EJB
    private BoletoController boletoController;

    public List<Vuelo> retrieveVuelos() {
        return facade.findAll();
    }

    public Vuelo retrieveVueloBy(Vuelo vuelo) {
        return facade.find(vuelo);
    }

    public Vuelo retrieveVueloBy(Integer idVuelo) {
        return facade.retrieveVueloById(idVuelo);
    }

    public void registrarVuelo(Vuelo vuelo) {
        try {
            facade.create(vuelo);
        } catch (Exception ex) {
            System.out.println("Error Insertar vuelo:" + ex);
        }

    }

    public void editarVuelo(Vuelo vuelo) {
        try {
            facade.edit(vuelo);
        } catch (Exception ex) {
            System.out.println("Error Editar vuelo:" + ex);
        }
    }

    public void eliminarVuelo(Vuelo vuelo) {
        facade.remove(vuelo);
    }

    public List<Vuelo> retrieveVuelosByPara(String inicio, String fin, String origen, String destino, String numPersonas) {
        Date fechaInicio = new Date(inicio);
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MMM-yy");
        Date fechaFin = new Date(fin);
        List<Vuelo> vuelos = new ArrayList();
        System.out.println("fechaInicio: " + dt1.format(fechaInicio).toUpperCase());
        System.out.println("fechaFin: " + dt1.format(fechaFin).toUpperCase());
        Integer numPerson = Integer.parseInt(numPersonas);
        vuelos = facade.retrieveVuelosByPara(dt1.format(fechaInicio).toUpperCase(), dt1.format(fechaFin).toUpperCase(), origen, destino);
        for (Vuelo vuelo : vuelos) {
            List<Boleto> boletosTmp = new ArrayList();
            boletosTmp = boletoController.retrieveBoletosByIdVuelo(vuelo.getVueCodigo());
            if (boletosTmp != null && boletosTmp.size() > 0) {
                if (numPerson > boletosTmp.size()) {
                    vuelos.remove(vuelo);
                }
            }
        }
        return vuelos;
    }

}
