/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.ejb.ThematicCoreFacadeLocal;
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
 *
 * @author hashy
 */
@Stateless
public class ThematicCoreLogic implements ThematicCoreLogicLocal {

    @EJB
    private TrainingAreaFacadeLocal trainingAreaFacade;
    @EJB
    private ThematicCoreFacadeLocal thematicCoreFacade;
    @EJB
    private ProgramFacadeLocal programFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_THEMATIC_CORE";

    private static final String CLASS = "Clase logica nucleo tematico";

    @Override
    public List<ThematicCoreP> getList(int program) throws GenericException {
        try {
            Program pro = programFacade.find(program);
            List<ThematicCore> list = thematicCoreFacade.getList(pro.getFkProGeneral().getId());
            List<ThematicCoreP> data = new ArrayList<>();
            for (ThematicCore lis : list) {
                ThematicCoreP aux = new ThematicCoreP(lis.getId(), lis.getName(), lis.getCredits(), lis.getObjective(), lis.getFkTcTrainingArea().getId());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public ThematicCoreP get(int id) throws GenericException {
        try {
            ThematicCore aux = thematicCoreFacade.find(id);
            ThematicCoreP data = new ThematicCoreP(aux.getId(), aux.getName(), aux.getCredits(), aux.getObjective(), aux.getFkTcTrainingArea().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void add(ThematicCoreP thematic) throws GenericException {
        try {
            TrainingArea tra = trainingAreaFacade.find(thematic.getIdTrainingArea());
            ThematicCore data = new ThematicCore(thematic.getName(), thematic.getCredits(), thematic.getObjective());
            data.setFkTcTrainingArea(tra);
            thematicCoreFacade.create(data);
            thematic.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(thematic.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void edit(ThematicCoreP thematic) throws GenericException {
        try {
            ThematicCore data = thematicCoreFacade.find(thematic.getId());
            data.setCredits(thematic.getCredits());
            data.setName(thematic.getName());
            data.setObjective(thematic.getObjective());
            thematicCoreFacade.edit(data);
            thematic.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(thematic.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void delete(ThematicCoreP thematic) throws GenericException {
        try {
            ThematicCore data = thematicCoreFacade.find(thematic.getId());
            thematicCoreFacade.remove(data);
            thematic.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(thematic.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
