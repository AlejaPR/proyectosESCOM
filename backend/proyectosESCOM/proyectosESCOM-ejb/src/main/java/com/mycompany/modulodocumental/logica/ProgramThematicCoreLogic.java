package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.interfaces.ThematicCoreFacadeLocal;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.entity.ProgramThematicCore;
import com.mycompany.modulodocumental.entity.ThematicCore;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramThematicCoreFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.ProgramThematicCoreLogicLocal;
import com.mycompany.modulodocumental.pojo.ProgramThematicCoreP;
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
public class ProgramThematicCoreLogic implements ProgramThematicCoreLogicLocal {

    /**
     * program thematic core interface injection
     */
    @EJB
    private ProgramThematicCoreFacadeLocal programThematicCoreFacade;

    /**
     * thematic core interface injection
     */
    @EJB
    private ThematicCoreFacadeLocal thematicCoreFacade;

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
    private static final String TABLE = "TBL_PROGRAM_THEMATIC_CORE";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica programa nucleo temactico";

    /**
     * method to list thematic core program
     *
     * @param program
     * @return
     * @throws GenericException
     */
    @Override
    public List<ProgramThematicCoreP> getList(int program) throws GenericException {
        try {
            List<ProgramThematicCore> listAux = programThematicCoreFacade.getList(program);
            List<ProgramThematicCoreP> data = new ArrayList<>();
            for (ProgramThematicCore list : listAux) {
                ProgramThematicCoreP aux = new ProgramThematicCoreP(list.getId(), list.getFkPtThematicCore().getName(), list.getObjective());
                aux.setNameThematicCore(list.getFkPtThematicCore().getName());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to obtain thematic core program
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @Override
    public ProgramThematicCoreP get(int id) throws GenericException {
        try {
            ProgramThematicCore pro = programThematicCoreFacade.find(id);
            ProgramThematicCoreP data = new ProgramThematicCoreP(pro.getId(), pro.getContributeObjetive(), pro.getContributeProfessional(), pro.getContributeOccupational(), pro.getObjectiveOutput(), pro.getTeamContribution(), pro.getObservationFinal(), pro.getFkPtProgram().getId(), pro.getFkPtThematicCore().getId());
            data.setNameThematicCore(pro.getFkPtThematicCore().getName());
            data.setObjetive(pro.getObjective());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to add a thematic core program
     *
     * @param programT
     * @throws GenericException
     */
    @Override
    public void add(ProgramThematicCoreP programT) throws GenericException {
        try {
            Program pro = programFacade.find(programT.getIdProgram());
            ThematicCore them = thematicCoreFacade.find(programT.getIdThematicCore());
            ProgramThematicCore data = new ProgramThematicCore();
            data.setFkPtProgram(pro);
            data.setFkPtThematicCore(them);
            programThematicCoreFacade.create(data);
            programT.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(programT.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to edit a thematic core program
     *
     * @param programT
     * @throws GenericException
     */
    @Override
    public void edit(ProgramThematicCoreP programT) throws GenericException {
        try {
            ProgramThematicCore data = programThematicCoreFacade.find(programT.getId());
            data.setContributeObjetive(programT.getContributeObjetive());
            data.setContributeOccupational(programT.getContributeOccupational());
            data.setContributeProfessional(programT.getContributeProfessional());
            data.setObjectiveOutput(programT.getObjectiveOutput());
            data.setObservationFinal(programT.getObservationFinal());
            data.setTeamContribution(programT.getTeamContribution());
            data.setObjective(programT.getObjetive());
            programThematicCoreFacade.edit(data);
            programT.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(programT.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to remove a core theme program
     *
     * @param id
     * @param dataS
     * @throws GenericException
     */
    @Override
    public void delete(int id, DatosSolicitudPOJO dataS) throws GenericException {
        try {
            ProgramThematicCore data = programThematicCoreFacade.find(id);
            programThematicCoreFacade.remove(data);
            dataS.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataS);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
