/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.ecutravel.aerolinea.controllers;

import ec.edu.espe.ecutravel.aerolinea.dao.AsientoFacade;
import ec.edu.espe.ecutravel.aerolinea.entities.Asiento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JuanAndresCaspi
 */
@Stateless
public class AsientoController {

    @EJB
    private AsientoFacade facade;

    public List<Asiento> retrieveAsientos() {
        return facade.findAll();
    }

    public Boolean registrarAsiento(Asiento asiento) {
        facade.create(asiento);
        //TODO
        return true;
    }

    public void editarAsiento(Asiento asiento) {
        facade.edit(asiento);
    }

    public void eliminarAsiento(Asiento asiento) {
        facade.remove(asiento);
    }

    public Asiento retrieveAsientoByNumero(Integer idAsiento) {
        return facade.retrieveAsientoById(idAsiento);
    }
}
