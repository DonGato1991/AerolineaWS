/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutrave.aerolineal.ws;

import ec.edu.espe.ecutravel.aerolinea.controllers.AsientoController;
import ec.edu.espe.ecutravel.aerolinea.controllers.BoletoController;
import ec.edu.espe.ecutravel.aerolinea.controllers.VueloController;
import ec.edu.espe.ecutravel.aerolinea.entities.Asiento;
import ec.edu.espe.ecutravel.aerolinea.entities.Boleto;
import ec.edu.espe.ecutravel.aerolinea.entities.Vuelo;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JuanAndresCaspi
 */
@WebService(serviceName = "aerolineaws")
public class aerolineaws {

    @EJB
    private VueloController vueloController;

    @EJB
    private AsientoController asientoController;

    @EJB
    private BoletoController boletoController;

    @WebMethod(operationName = "obtenerInfoVuelos")
    public List<Vuelo> obtenerInfoVuelos() {
        return vueloController.retrieveVuelos();
    }

    @WebMethod(operationName = "insertVueloByParam")
    private boolean insertVueloByParam(@WebParam(name = "inicio") String inicio, @WebParam(name = "fin") String fin, @WebParam(name = "origen") String origen, @WebParam(name = "destino") String destino) {

        Vuelo vuelo = new Vuelo();
        vuelo.setFechaFin(new Date(fin));
        vuelo.setFechaInicio(new Date(inicio));
        vuelo.setPrecio(new BigDecimal("65.99"));
        vuelo.setCiudadOrigen(origen);
        vuelo.setCiudadDestino(destino);
        vuelo.setAerolinea("AVIANCA");
        vueloController.registrarVuelo(vuelo);
        return true;
    }

    @WebMethod(operationName = "retrieveVuelosByPara")
    public List<Vuelo> retrieveVuelosByPara(
            @WebParam(name = "inicio") String inicio, 
            @WebParam(name = "fin") String fin, 
            @WebParam(name = "origen") String origen, 
            @WebParam(name = "destino") String destino, 
            @WebParam(name = "numPer") String numPer) {
        return vueloController.retrieveVuelosByPara(inicio, fin, origen, destino, numPer);
    }

    @WebMethod(operationName = "genericMethod")
    private void genericMethod(@WebParam(name = "origen") String origen) {
        System.out.println("");
    }

    @WebMethod(operationName = "registrarBoleto")
    public Boolean registrarBoleto(
            @WebParam(name = "persona") String persona, 
            @WebParam(name = "paquete") String paquete, 
            @WebParam(name = "numPersonas") String numPersonas, 
            @WebParam(name = "idVuelo") Integer idVuelo) {
        boolean success = false;
        Vuelo vuelo = new Vuelo();
        vuelo = vueloController.retrieveVueloBy(idVuelo);
        boletoController.registrarBoleto(vuelo, persona, paquete, numPersonas);
        return success;
    }
}
