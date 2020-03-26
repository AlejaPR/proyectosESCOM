/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramLogicFacadeLocal;
import com.mycompany.modulodocumental.pojo.ProgramP;
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
public class ProgramLogic implements ProgramLogicFacadeLocal {
    
    @EJB
    ProgramFacadeLocal programFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;
    
    private static final String TABLE = "TBL_PROGRAM";
    
    private static final String CLASS = "Clase logica programa";
    
    @Override
    public List<ProgramP> listProgram() throws GenericException {
        try {
            List<Program> list = programFacade.findAll();
            List<ProgramP> data = new ArrayList<>();
            for (Program pro : list) {
                ProgramP proP = new ProgramP(pro.getId(), pro.getName(), pro.getLevelEducation(), pro.getInstitution(), pro.getAcademicCredits(), pro.getDuration(), pro.getMethodology(), pro.getCampus());
                data.add(proP);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista programas", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }
    
    @Override
    public ProgramP getProgramId(int id) throws GenericException {
        try {
            Program pro = programFacade.find(id);
            ProgramP data = new ProgramP(pro.getId(), pro.getName(), pro.getLevelEducation(), pro.getInstitution(), pro.getAcademicCredits(), pro.getDuration(), pro.getMethodology(), pro.getCampus());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener programa id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }
    
    @Override
    public void addProgram(ProgramP program) throws GenericException {
        try {
            Program data = new Program(program.getName(), program.getLevelEducation(), program.getInstitution(), program.getAcademicCredits(), program.getDuration(), program.getMethodology(), program.getCampus());
            programFacade.create(data);
            program.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(program.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar programa", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }
    
    @Override
    public void editProgram(ProgramP program) throws GenericException {
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
    
}
