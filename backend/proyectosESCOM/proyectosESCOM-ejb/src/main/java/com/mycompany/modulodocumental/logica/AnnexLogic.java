/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Annex;
import com.mycompany.modulodocumental.entity.AnnexVersion;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.interfaces.AnnexFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.AnnexLogicLocal;
import com.mycompany.modulodocumental.interfaces.AnnexVersionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.pojo.AnnexP;
import com.mycompany.modulodocumental.pojo.SearchAnnP;
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
public class AnnexLogic implements AnnexLogicLocal {

    @EJB
    private AnnexFacadeLocal annexFacade;
    @EJB
    private ProgramFacadeLocal programFacade;
    @EJB
    private AnnexVersionFacadeLocal annexVersionFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_ANNEX";
    
    private static final String CLASS = "Clase logica anexo";
    

    @Override
    public void add(AnnexP annex) throws GenericException {
        try {
            Annex add = new Annex(annex.getKeywords(), annex.getDescription(), annex.getName());
            add.setState(annex.getState());
            Program con = programFacade.find(annex.getProgram());
            add.setFkAxProgram(con);
            annexFacade.create(add);
            annex.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(annex.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void edit(AnnexP annex) throws GenericException {
        try {
            Annex data = annexFacade.find(annex.getId());
            data.setDescription(annex.getDescription());
            data.setKeywords(annex.getKeywords());
            data.setName(annex.getName());
            annexFacade.edit(data);
            annex.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(annex.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void disable(int id, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Annex disable = annexFacade.find(id);
            disable.setState(-1);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
            annexFacade.edit(disable);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public List<AnnexP> getList(int idProgram) throws GenericException {
        try {
            List<Annex> listAnnex = annexFacade.searchAnnex(idProgram, null);
            List<AnnexP> data = new ArrayList<>();
            for (Annex annex : listAnnex) {
                AnnexP add = new AnnexP(annex.getId(), annex.getKeywords(), annex.getDescription(), annex.getName());
                List<AnnexVersion> list = annexVersionFacade.listAnnexVersion(annex.getId());
                if (list.size() > 0) {
                    add.setLink(list.get(0).getLocation());
                }
                data.add(add);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista anexos", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public AnnexP get(int idAnnex) throws GenericException {
        try {
            Annex annex = annexFacade.find(idAnnex);
            AnnexP data = new AnnexP(annex.getId(), annex.getKeywords(), annex.getDescription(), annex.getName());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener anexo id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public List<AnnexP> searchAnnexS(SearchAnnP search) throws GenericException {
        try {
            List<Annex> listAnnex = annexFacade.searchAnnex(search.getIdProgram(), search.getName());
            List<AnnexP> data = new ArrayList<>();
            for (Annex annex : listAnnex) {
                AnnexP add = new AnnexP(annex.getId(), annex.getKeywords(), annex.getDescription(), annex.getName());
                List<AnnexVersion> list = annexVersionFacade.listAnnexVersion(annex.getId());
                if (list.size() > 0) {
                    add.setLink(list.get(0).getLocation());
                }
                data.add(add);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Buscar anexos", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
