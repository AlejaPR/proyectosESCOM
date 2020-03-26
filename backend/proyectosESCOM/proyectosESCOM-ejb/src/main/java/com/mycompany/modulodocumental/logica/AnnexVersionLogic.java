/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.AnnexVersion;
import com.mycompany.modulodocumental.interfaces.AnnexFacadeLocal;
import com.mycompany.modulodocumental.interfaces.AnnexVersionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.AnnexVersionLogicFacadeLocal;
import com.mycompany.modulodocumental.pojo.AnnexVersionP;
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
public class AnnexVersionLogic implements AnnexVersionLogicFacadeLocal {

    @EJB
    private AnnexVersionFacadeLocal annexVersionFacade;
    @EJB
    private AnnexFacadeLocal annexFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_ANNEX_VERSION";
    
    private static final String CLASS = "Clase logica version anexo";

    @Override
    public List<AnnexVersionP> listAnnexVersion(int id) throws GenericException {
        try {
            List<AnnexVersion> list = annexVersionFacade.listAnnexVersion(id);
            List<AnnexVersionP> data = new ArrayList<>();
            for (AnnexVersion aux : list) {
                AnnexVersionP version = new AnnexVersionP(aux.getId(), aux.getDate(), aux.getLocation(), aux.getState(), aux.getVersion(), aux.getDescription());
                data.add(version);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista versiones anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void addAnnexVersion(AnnexVersionP annexV) throws GenericException {
        try {
            List<AnnexVersion> list = annexVersionFacade.listAnnexVersion(annexV.getAnnex());
            int value = 0;
            if (list.size() > 0) {
                value = list.get(0).getVersion() + 1;
            } else {
                value = 1;
            }
            if (list.size() > 0) {
                AnnexVersion fin = list.get(0);
                fin.setState(2);
                annexVersionFacade.edit(fin);
            }
            AnnexVersion aux = new AnnexVersion(annexV.getDate(), annexV.getLocation(), annexV.getState(), value, annexV.getDescription());
            aux.setFkAvAnnex(annexFacade.find(annexV.getAnnex()));
            annexVersionFacade.create(aux);
            annexV.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(annexV.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar version anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void deleteVersion(int id, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            AnnexVersion del = annexVersionFacade.find(id);
            annexVersionFacade.remove(del);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar version", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
