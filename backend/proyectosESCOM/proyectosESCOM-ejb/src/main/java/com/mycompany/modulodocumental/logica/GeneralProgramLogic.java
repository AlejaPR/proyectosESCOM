/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.GeneralProgram;
import com.mycompany.modulodocumental.interfaces.GeneralProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.GeneralProgramLogicLocal;
import com.mycompany.modulodocumental.pojo.GeneralProgramP;
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
public class GeneralProgramLogic implements GeneralProgramLogicLocal {

    @EJB
    private GeneralProgramFacadeLocal generalProgramFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_GENERAL_PROGRAM";

    private static final String CLASS = "Clase logica programa general";

    @Override
    public List<GeneralProgramP> getList() throws GenericException {
        try {
            List<GeneralProgram> list = generalProgramFacade.findAll();
            List<GeneralProgramP> data = new ArrayList<>();
            for (GeneralProgram lis : list) {
                if (lis.getState() > 0) {
                    GeneralProgramP aux = new GeneralProgramP(lis.getId(), lis.getName(), lis.getDescription(), lis.getState());
                    data.add(aux);
                }
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public GeneralProgramP get(int idGeneralP) throws GenericException {
        try {
            GeneralProgram aux = generalProgramFacade.find(idGeneralP);
            GeneralProgramP data = new GeneralProgramP(aux.getId(), aux.getName(), aux.getDescription(), aux.getState());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void add(GeneralProgramP generalP) throws GenericException {
        try {
            GeneralProgram data = new GeneralProgram(generalP.getName(), generalP.getDescription(), generalP.getState());
            generalProgramFacade.create(data);
            generalP.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(generalP.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void edit(GeneralProgramP generalP) throws GenericException {
        try {
            GeneralProgram data = generalProgramFacade.find(generalP.getId());
            data.setName(generalP.getName());
            data.setDescription(generalP.getDescription());
            generalProgramFacade.edit(data);
            generalP.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(generalP.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void disable(GeneralProgramP generalP) throws GenericException {
        try {
            GeneralProgram data = generalProgramFacade.find(generalP.getId());
            data.setState(-1);
            generalP.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(generalP.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
