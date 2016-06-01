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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JuanAndresCaspi
 */
@WebService(serviceName = "aerolineaTamews")
//aerolineaTamews victor
public class aerolineaws {

    @EJB
    private VueloController vueloController;

    @EJB
    private AsientoController asientoController;

    @EJB
    private BoletoController boletoController;

    @WebMethod(operationName = "obtenerAllInfoVuelos")
    private List<Vuelo> obtenerAllInfoVuelos() {
        return vueloController.retrieveVuelos();
    }

    @WebMethod(operationName = "insertVueloByParam")
    private boolean insertVueloByParam(
            @WebParam(name = "mes") String mesE,
            @WebParam(name = "anio") String anioE,
            @WebParam(name = "origen") String origen,
            @WebParam(name = "destino") String destino) {
        int anio = Integer.parseInt(anioE);
        //mes le toma desde el 0
        int mes = Integer.parseInt(mesE);
        for (int i = 0; i < 30; i++) {
            int hora = 8;
            int dia = i + 1;
            for (int j = 0; j < 5; j++) {
                Calendar c = Calendar.getInstance();
                c.set(anio, mes, dia, hora, 0);
                Vuelo vuelo = new Vuelo();
                vuelo.setFechaInicio(c.getTime());
                hora = hora + 3;
                c.set(anio, mes, dia, hora, 0);
                vuelo.setFechaFin(c.getTime());
                vuelo.setPrecio(new BigDecimal("75"));
                vuelo.setCiudadOrigen(origen);
                vuelo.setCiudadDestino(destino);
                vuelo.setAerolinea("TAME");
                vueloController.registrarVuelo(vuelo);
            }
        }

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

    @WebMethod(operationName = "populateSits")
    private boolean populateSits() {
        Asiento asiento;
        for (int i = 0; i < 30; i++) {
            asiento = new Asiento();
            asiento.setNumero(BigInteger.valueOf(i + 1));
            asientoController.registrarAsiento(asiento);
        }
        System.out.println("");
        return true;
    }

    @WebMethod(operationName = "registrarBoleto")
    public Boolean registrarBoleto(
            @WebParam(name = "persona") String persona,
            @WebParam(name = "paquete") String paquete,
            @WebParam(name = "numPersonas") String numPersonas,
            @WebParam(name = "idVuelo") Integer idVuelo) {
        boolean success = false;
        success = boletoController.registrarBoleto(idVuelo, persona, paquete, numPersonas);
        return success;
    }

    @WebMethod(operationName = "traerBoletosPorPersona")
    public List<Boleto> traerBoletosPorPersona(
            @WebParam(name = "persona") String persona) {
        List<Boleto> boletosRegistrados = new ArrayList();
        boletosRegistrados = boletoController.retrieveBoletosByIdentiPesona(persona);
        return boletosRegistrados;
    }
}
