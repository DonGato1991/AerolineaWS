/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.controllers;

import ec.edu.espe.ecutravel.aerolinea.entities.Boleto;
import ec.edu.espe.ecutravel.aerolinea.dao.BoletoFacade;
import ec.edu.espe.ecutravel.aerolinea.entities.Asiento;
import ec.edu.espe.ecutravel.aerolinea.entities.Vuelo;
import java.math.BigInteger;
import java.util.ArrayList;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JuanAndresCaspi
 */
@Stateless
public class BoletoController {

    @EJB
    private BoletoFacade facade;
    @EJB
    private VueloController serviceVuelo;
    @EJB
    private AsientoController asientoController;

    public List<Boleto> retrieveBoletos() {
        return facade.findAll();
    }

    public Boolean registrarBoleto(Boleto boleto) {
        facade.create(boleto);
        //TODO
        return true;
    }

    public void editarBoleto(Boleto boleto) {
        facade.edit(boleto);
    }

    public void eliminarBoleto(Boleto boleto) {
        facade.remove(boleto);
    }

    public Boolean registrarBoleto(Integer idVuelo, String persona, String paquete, String numPersonas) {
        Boleto boleto = new Boleto();
        boolean success = false;
        Vuelo vuelo = new Vuelo();
        vuelo = serviceVuelo.retrieveVueloBy(idVuelo);
        Integer asientoActual = retrieveAsientoOcupadosTotalByIdVuelo(idVuelo);
        if (asientoActual != null && vuelo != null) {
            Integer asientoInicial = asientoActual + 1;
            Integer asientoFinal = asientoInicial + Integer.parseInt(numPersonas);
            Asiento asiento;
            if (asientoFinal < 30) {
                for (int i = asientoInicial; i < asientoFinal; i++) {
                    asiento = new Asiento();
                    boleto = new Boleto();
                    asiento = asientoController.retrieveAsientoByNumero(i);
                    boleto.setAsiCodigo(asiento);
                    boleto.setVueCodigo(vuelo);
                    boleto.setPersona(new BigInteger(persona));
                    boleto.setPaquete(new BigInteger(paquete));
                    facade.create(boleto);
                }
                success = true;
            } else {
                success = false;
            }
        } else {
            success = false;
        }
        return success;
    }

    public List<Boleto> retrieveBoletosByIdVuelo(Integer idVuelo) {
        Vuelo vuelo = serviceVuelo.retrieveVueloBy(idVuelo);
        List<Boleto> boletos = new ArrayList<>();
        boletos = facade.retrieveBoletosByIdVuelo(vuelo);
        return boletos;
    }
    public List<Boleto> retrieveBoletosByIdentiPesona(String identificacion) {
        List<Boleto> boletos = new ArrayList<>();
        boletos = facade.retrieveBoletosByCedula(identificacion);
        return boletos;
    }

    public Integer retrieveAsientoLibresTotalByIdVuelo(Integer idVuelo) {
        Vuelo vuelo = new Vuelo();
        Integer asientosDisponibles = 0;
        vuelo = serviceVuelo.retrieveVueloBy(idVuelo);
        if (vuelo != null) {
            List<Boleto> boletos = new ArrayList<>();
            boletos = facade.retrieveBoletosByIdVuelo(vuelo);
            asientosDisponibles = asientoController.retrieveAsientos().size() - boletos.size();
        }
        return asientosDisponibles;
    }

    public Integer retrieveAsientoOcupadosTotalByIdVuelo(Integer idVuelo) {
        Vuelo vuelo = new Vuelo();
        Integer asientosOcupados = 0;
        vuelo = serviceVuelo.retrieveVueloBy(idVuelo);
        if (vuelo != null) {
            List<Boleto> boletos = new ArrayList<>();
            boletos = facade.retrieveBoletosByIdVuelo(vuelo);
            if (boletos != null) {
                asientosOcupados = boletos.size();
            }
        }
        return asientosOcupados;
    }
}
