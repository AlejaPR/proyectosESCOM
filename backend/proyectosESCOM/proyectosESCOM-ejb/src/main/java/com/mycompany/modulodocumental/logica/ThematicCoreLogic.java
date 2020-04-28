package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.interfaces.ThematicCoreFacadeLocal;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.entity.ThematicCore;
import com.mycompany.modulodocumental.entity.TrainingArea;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.TrainingAreaFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.ThematicCoreLogicLocal;
import com.mycompany.modulodocumental.pojo.ThematicCoreP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is the class in charge of the thematic core logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ThematicCoreLogic implements ThematicCoreLogicLocal {

    /**
     * training area interface injection
     */
    @EJB
    private TrainingAreaFacadeLocal trainingAreaFacade;

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
    private static final String TABLE = "TBL_THEMATIC_CORE";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica nucleo tematico";

    /**
     * method to get the list of thematic core
     *
     * @param program
     * @return
     * @throws GenericException
     */
    @Override
    public List<ThematicCoreP> getList(int program) throws GenericException {
        try {
            Program pro = programFacade.find(program);
            List<ThematicCore> list = thematicCoreFacade.getList(pro.getFkProGeneral().getId());
            List<ThematicCoreP> data = new ArrayList<>();
            for (ThematicCore lis : list) {
                ThematicCoreP aux = new ThematicCoreP(lis.getId(), lis.getName(), lis.getCredits(), lis.getFkTcTrainingArea().getId());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to get a thematic core
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @Override
    public ThematicCoreP get(int id) throws GenericException {
        try {
            ThematicCore aux = thematicCoreFacade.find(id);
            ThematicCoreP data = new ThematicCoreP(aux.getId(), aux.getName(), aux.getCredits(), aux.getFkTcTrainingArea().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to add a thematic core
     *
     * @param thematic
     * @throws GenericException
     */
    @Override
    public void add(ThematicCoreP thematic) throws GenericException {
        try {
            TrainingArea tra = trainingAreaFacade.find(thematic.getIdTrainingArea());
            ThematicCore data = new ThematicCore(thematic.getName(), thematic.getCredits());
            data.setFkTcTrainingArea(tra);
            thematicCoreFacade.create(data);
            thematic.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(thematic.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to edit a thematic core
     *
     * @param thematic
     * @throws GenericException
     */
    @Override
    public void edit(ThematicCoreP thematic) throws GenericException {
        try {
            ThematicCore data = thematicCoreFacade.find(thematic.getId());
            data.setCredits(thematic.getCredits());
            data.setName(thematic.getName());
            thematicCoreFacade.edit(data);
            thematic.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(thematic.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to remove a thematic core
     *
     * @param thematic
     * @throws GenericException
     */
    @Override
    public void delete(ThematicCoreP thematic) throws GenericException {
        try {
            ThematicCore data = thematicCoreFacade.find(thematic.getId());
            if (data != null) {
                thematicCoreFacade.remove(data);
                thematic.getRequestData().setTablaInvolucrada(TABLE);
                bitacora.registrarEnBitacora(thematic.getRequestData());
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
