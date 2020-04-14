package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.AnnexVersion;
import com.mycompany.modulodocumental.interfaces.AnnexFacadeLocal;
import com.mycompany.modulodocumental.interfaces.AnnexVersionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.AnnexVersionLogicLocal;
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
 * This is the class in charge of the annex version logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class AnnexVersionLogic implements AnnexVersionLogicLocal {

    /**
     * Annex version interface injection
     */
    @EJB
    private AnnexVersionFacadeLocal annexVersionFacade;

    /**
     * Annex interface injection
     */
    @EJB
    private AnnexFacadeLocal annexFacade;

    /**
     * Bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_ANNEX_VERSION";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica version anexo";

    /**
     * method to get list of annex versions
     *
     * @param idAnnex
     * @return
     * @throws GenericException
     */
    @Override
    public List<AnnexVersionP> getList(int idAnnex) throws GenericException {
        try {
            List<AnnexVersion> list = annexVersionFacade.listAnnexVersion(idAnnex);
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

    /**
     * method to add an annex versions
     *
     * @param annexV
     * @throws GenericException
     */
    @Override
    public void add(AnnexVersionP annexV) throws GenericException {
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
                fin.setState(-1);
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

    /**
     * method to remove an annex versions
     *
     * @param idAnnexVersion
     * @param dataR
     * @throws GenericException
     */
    @Override
    public void delete(int idAnnexVersion, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            AnnexVersion del = annexVersionFacade.find(idAnnexVersion);
            annexVersionFacade.remove(del);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar version", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
