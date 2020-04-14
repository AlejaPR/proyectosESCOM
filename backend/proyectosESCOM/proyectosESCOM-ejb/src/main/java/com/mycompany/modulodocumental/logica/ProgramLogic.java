package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.GeneralProgram;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.interfaces.GeneralProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.ProgramLogicLocal;
import com.mycompany.modulodocumental.pojo.ProgramP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is the class in charge of the program logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ProgramLogic implements ProgramLogicLocal {

    /**
     * program interface injection
     */
    @EJB
    private ProgramFacadeLocal programFacade;

    /**
     * general program interface injection
     */
    @EJB
    private GeneralProgramFacadeLocal generalprogramFacade;

    /**
     * bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_PROGRAM";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica programa";

    /**
     * method to get the program list
     *
     * @return
     * @throws GenericException
     */
    @Override
    public List<ProgramP> getList() throws GenericException {
        try {
            List<Program> list = programFacade.findAll();
            List<ProgramP> data = new ArrayList<>();
            for (Program pro : list) {
                if (pro.getState() > 0) {
                    ProgramP proP = new ProgramP(pro.getId(), pro.getName(), pro.getLevelEducation(), pro.getInstitution(), pro.getAcademicCredits(), pro.getDuration(), pro.getMethodology(), pro.getCampus(), pro.getState());
                    data.add(proP);
                }
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista programas", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of get a program
     *
     * @param idProgram
     * @return
     * @throws GenericException
     */
    @Override
    public ProgramP get(int idProgram) throws GenericException {
        try {
            Program pro = programFacade.find(idProgram);
            ProgramP data = new ProgramP(pro.getId(), pro.getName(), pro.getLevelEducation(), pro.getInstitution(), pro.getAcademicCredits(), pro.getDuration(), pro.getMethodology(), pro.getCampus(), pro.getState());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener programa id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of adding a program
     *
     * @param program
     * @throws GenericException
     */
    @Override
    public void add(ProgramP program) throws GenericException {
        try {
            Program data = new Program(program.getName(), program.getLevelEducation(), program.getInstitution(), program.getAcademicCredits(), program.getDuration(), program.getMethodology(), program.getCampus(), program.getState());
            GeneralProgram general = generalprogramFacade.find(program.getIdGeneral());
            data.setFkProGeneral(general);
            programFacade.create(data);
            program.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(program.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar programa", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of editing a program
     *
     * @param program
     * @throws GenericException
     */
    @Override
    public void edit(ProgramP program) throws GenericException {
        try {
            Program data = programFacade.find(program.getId());
            data.setAcademicCredits(program.getAcademicCredits());
            data.setDuration(program.getDuration());
            data.setInstitution(program.getInstitution());
            data.setLevelEducation(program.getLevelEducation());
            data.setMethodology(program.getMethodology());
            data.setCampus(program.getCampus());
            data.setName(program.getName());
            programFacade.edit(data);
            program.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(program.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar programa", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to disable a program
     *
     * @param id
     * @param dataS
     * @throws GenericException
     */
    @Override
    public void disable(int id, DatosSolicitudPOJO dataS) throws GenericException {
        try {
            Program data = programFacade.find(id);
            data.setState(-1);
            programFacade.edit(data);
            dataS.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataS);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar programa", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
