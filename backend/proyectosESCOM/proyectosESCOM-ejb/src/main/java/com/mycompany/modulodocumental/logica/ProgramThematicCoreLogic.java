/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.ejb.ThematicCoreFacadeLocal;
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
 *
 * @author hashy
 */
@Stateless
public class ProgramThematicCoreLogic implements ProgramThematicCoreLogicLocal {

    @EJB
    private ProgramThematicCoreFacadeLocal programThematicCoreFacade;
    @EJB
    private ThematicCoreFacadeLocal thematicCoreFacade;
    @EJB
    private ProgramFacadeLocal programFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_PROGRAM_THEMATIC_CORE";

    private static final String CLASS = "Clase logica programa nucleo temactico";
    
    @Override
    public List<ProgramThematicCoreP> getList(int program) throws GenericException {
        try {
            Program pro = programFacade.find(program);
            List<ProgramThematicCore> listAux = pro.getListProgramThematicCore();
            List<ProgramThematicCoreP> data = new ArrayList<>();
            for (ProgramThematicCore list : listAux) {
                ProgramThematicCoreP aux = new ProgramThematicCoreP(list.getId(), list.getFkPtThematicCore().getName(), list.getFkPtThematicCore().getObjective());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public ProgramThematicCoreP get(int id) throws GenericException {
        try {
            ProgramThematicCore pro = programThematicCoreFacade.find(id);
            ProgramThematicCoreP data = new ProgramThematicCoreP(pro.getId(), pro.getContributeObjetive(),pro.getContributeProfessional(), pro.getContributeOccupational(),pro.getObjectiveOutput() ,pro.getTeamContribution() , pro.getObservationFinal() , pro.getFkPtProgram().getId(), pro.getFkPtThematicCore().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void add(ProgramThematicCoreP programT) throws GenericException {
        try {
            Program pro = programFacade.find(programT.getIdProgram());
            ThematicCore them = thematicCoreFacade.find(programT.getIdThematicCore());
            ProgramThematicCore data = new ProgramThematicCore();
            data.setFkPtProgram(pro);
            data.setFkPtThematicCore(them);
            thematicCoreFacade.create(them);
            programT.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(programT.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

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
            programThematicCoreFacade.edit(data);
            programT.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(programT.getRequestData());                   
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
