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
 * This is the class in charge of the competition general logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class CompetitionGeneralLogic implements CompetitionGeneralLogicLocal {

    /**
     * competition general interface injection
     */
    @EJB
    private CompetitionGeneralFacadeLocal competitionGeneralFacade;

    /**
     * competition interface injection
     */
    @EJB
    private CompetitionFacadeLocal competitionFacade;

    /**
     * program interface injection
     */
    @EJB
    private ProgramFacadeLocal programFacade;

    /**
     * bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_COMPETITION_GENERAL";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica competencia general";

    /**
     * method for obtaining the list of general competencies
     *
     * @param program
     * @return
     * @throws GenericException
     */
    @Override
    public List<CompetitionGeneralP> getList(int program) throws GenericException {
        try {
            Program pro = programFacade.find(program);
            List<CompetitionGeneral> listAux = competitionGeneralFacade.getList(pro.getFkProGeneral().getId());
            List<CompetitionGeneralP> data = new ArrayList<>();
            for (CompetitionGeneral list : listAux) {
                CompetitionGeneralP aux = new CompetitionGeneralP(list.getId(), list.getName(), list.getFkCgCompetition().getId());
                aux.setNameCompetition(list.getFkCgCompetition().getName());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of obtaining general competence
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @Override
    public CompetitionGeneralP get(int id) throws GenericException {
        try {
            CompetitionGeneral aux = competitionGeneralFacade.find(id);
            CompetitionGeneralP data = new CompetitionGeneralP(aux.getId(), aux.getName(), aux.getFkCgCompetition().getId());
            data.setNameCompetition(aux.getFkCgCompetition().getName());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to add general competence
     *
     * @param competitionGeneral
     * @throws GenericException
     */
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

    /**
     * method for editing a general competency
     *
     * @param competitionGeneral
     * @throws GenericException
     */
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

    /**
     * method to delete general competition
     * @param competitionGeneral
     * @throws GenericException 
     */
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
