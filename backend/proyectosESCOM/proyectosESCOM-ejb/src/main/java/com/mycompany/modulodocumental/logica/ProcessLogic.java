package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Document;
import com.mycompany.modulodocumental.entity.Process;
import com.mycompany.modulodocumental.interfaces.DocumentFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProcessFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.ConditionLogicLocal;
import com.mycompany.modulodocumental.interfaces.logic.ProcessLogicLocal;
import com.mycompany.modulodocumental.pojo.ConditionP;
import com.mycompany.modulodocumental.pojo.ProcessP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is the class in charge of the process logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ProcessLogic implements ProcessLogicLocal {

    /**
     * process interface injection
     */
    @EJB
    private ProcessFacadeLocal processFacade;

    /**
     * document interface injection
     */
    @EJB
    private DocumentFacadeLocal documentFacade;
    
    /**
     * Condition logical interface injection
     */
    @EJB
    private ConditionLogicLocal conditionLogicFacade;

    /**
     * bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_PROCESS";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica proceso";

    /**
     * method to get the process list
     *
     * @param idDocument
     * @return
     * @throws GenericException
     */
    @Override
    public List<ProcessP> getList(int idDocument) throws GenericException {
        try {
            List<Process> list = processFacade.listProcess(idDocument);
            List<ProcessP> data = new ArrayList<>();
            for (Process pro : list) {
                ProcessP proP = new ProcessP(pro.getId(), pro.getName(), pro.getDescription());
                data.add(proP);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "lista procesos", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to get a process
     *
     * @param idProcess
     * @return
     * @throws GenericException
     */
    @Override
    public ProcessP get(int idProcess) throws GenericException {
        try {
            Process pro = processFacade.find(idProcess);
            ProcessP data = new ProcessP(pro.getId(), pro.getName(), pro.getDescription());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtiene proceso id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to add a process
     *
     * @param process
     * @throws GenericException
     */
    @Override
    public void add(ProcessP process) throws GenericException {
        try {
            Process data = new Process(process.getName(), process.getDescription(), process.getState());
            Document doc = documentFacade.find(Integer.parseInt(process.getDocument()));
            data.setFkPrcDocument(doc);
            processFacade.create(data);
            process.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(process.getRequestData());
            ConditionP condition = new ConditionP(0,"General", "Esta condición se define para la contrición de elementos básicos que van al inicio como la portada", 1, Date.from(Instant.EPOCH), Date.from(Instant.EPOCH));
            condition.setProcess(data.getId());
            condition.setRequestData(process.getRequestData());
            conditionLogicFacade.add(condition);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar proceso", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of editing a process
     *
     * @param process
     * @throws GenericException
     */
    @Override
    public void edit(ProcessP process) throws GenericException {
        try {
            Process data = processFacade.find(process.getId());
            data.setDescription(process.getDescription());
            data.setName(process.getName());
            processFacade.edit(data);
            process.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(process.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar proceso", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to disable a process
     *
     * @param idProcess
     * @param dataR
     * @throws GenericException
     */
    @Override
    public void disable(int idProcess, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Process data = processFacade.find(idProcess);
            data.setState(-1);
            processFacade.edit(data);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar proceso", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
