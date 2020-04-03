/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Competition;
import com.mycompany.modulodocumental.entity.CompetitionGeneral;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.interfaces.CompetitionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.CompetitionGeneralFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.CompetitionGeneralLogicLocal;
import com.mycompany.modulodocumental.pojo.CompetitionGeneralP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author hashy
 */
@Stateless
public class CompetitionGeneralLogic implements CompetitionGeneralLogicLocal {

    @EJB
    private CompetitionGeneralFacadeLocal competitionGeneralFacade;
    @EJB
    private CompetitionFacadeLocal competitionFacade;
    @EJB
    private ProgramFacadeLocal programFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_COMPETITION_GENERAL";

    private static final String CLASS = "Clase logica competencia general";

    @Override
    public List<CompetitionGeneralP> getList(int program) throws GenericException {
        try {
            Program pro = programFacade.find(program);
            List<CompetitionGeneral> listAux = competitionGeneralFacade.getList(pro.getFkProGeneral().getId());
            List<CompetitionGeneralP> data = new ArrayList<>();
            for (CompetitionGeneral list : listAux) {
                CompetitionGeneralP aux = new CompetitionGeneralP(list.getId(), list.getName(), list.getFkCgCompetition().getId());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public CompetitionGeneralP get(int id) throws GenericException {
        try {
            CompetitionGeneral aux = competitionGeneralFacade.find(id);
            CompetitionGeneralP data = new CompetitionGeneralP(aux.getId(), aux.getName(), aux.getFkCgCompetition().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void add(CompetitionGeneralP competitionGeneral) throws GenericException {
        try {
            Competition com = competitionFacade.find(competitionGeneral.getIdCompetition());
            CompetitionGeneral data = new CompetitionGeneral(competitionGeneral.getName(), com);
            competitionGeneralFacade.create(data);
            competitionGeneral.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(competitionGeneral.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    @Override
    public void edit(CompetitionGeneralP competitionGeneral) throws GenericException {
        try {
            CompetitionGeneral data = competitionGeneralFacade.find(competitionGeneral.getId());
            data.setName(competitionGeneral.getName());
            competitionGeneral.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(competitionGeneral.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void delete(CompetitionGeneralP competitionGeneral) throws GenericException {
        try {
            CompetitionGeneral data = competitionGeneralFacade.find(competitionGeneral.getId());
            competitionGeneralFacade.remove(data);
            competitionGeneral.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(competitionGeneral.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
