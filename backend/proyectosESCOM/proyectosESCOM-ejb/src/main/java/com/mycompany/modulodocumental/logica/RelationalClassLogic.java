/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.ejb.PtThematicFacadeLocal;
import com.mycompany.modulodocumental.ejb.ThematicFacadeLocal;
import com.mycompany.modulodocumental.entity.CompetitionGeneral;
import com.mycompany.modulodocumental.entity.DistinctiveFeature;
import com.mycompany.modulodocumental.entity.OccupationalProfile;
import com.mycompany.modulodocumental.entity.ProfessionalProfile;
import com.mycompany.modulodocumental.entity.ProgramThematicCore;
import com.mycompany.modulodocumental.entity.PtCompetitionG;
import com.mycompany.modulodocumental.entity.PtDistinctive;
import com.mycompany.modulodocumental.entity.PtOccupational;
import com.mycompany.modulodocumental.entity.PtProfessional;
import com.mycompany.modulodocumental.entity.PtThematic;
import com.mycompany.modulodocumental.entity.Thematic;
import com.mycompany.modulodocumental.interfaces.CompetitionGeneralFacadeLocal;
import com.mycompany.modulodocumental.interfaces.DistinctiveFeatureFacadeLocal;
import com.mycompany.modulodocumental.interfaces.OccupationalProfileFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProfessionalProfileFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramThematicCoreFacadeLocal;
import com.mycompany.modulodocumental.interfaces.PtCompetitionGFacadeLocal;
import com.mycompany.modulodocumental.interfaces.PtDistinctiveFacadeLocal;
import com.mycompany.modulodocumental.interfaces.PtOccupationalFacadeLocal;
import com.mycompany.modulodocumental.interfaces.PtProfessionalFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.RelationalClassLogicLocal;
import com.mycompany.modulodocumental.pojo.RelationalClassP;
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
public class RelationalClassLogic implements RelationalClassLogicLocal {

    @EJB
    private ThematicFacadeLocal thematicFacade;
    @EJB
    private OccupationalProfileFacadeLocal occupationalProfileFacade;
    @EJB
    private ProfessionalProfileFacadeLocal professionalProfielFacade;
    @EJB
    private DistinctiveFeatureFacadeLocal distinctiveFeatureFacade;
    @EJB
    private CompetitionGeneralFacadeLocal competitionGerenelFacade;
    @EJB
    private ProgramThematicCoreFacadeLocal programThematicCoreFacade;
    @EJB
    private PtCompetitionGFacadeLocal ptCompetitionGFacade;
    @EJB
    private PtDistinctiveFacadeLocal ptDistinctiveFacade;
    @EJB
    private PtOccupationalFacadeLocal ptOccupationalFacade;
    @EJB
    private PtProfessionalFacadeLocal ptProfessionalFacade;
    @EJB
    private PtThematicFacadeLocal ptThematicFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String CLASS = "Clase logica relacional";

    @Override
    public List<RelationalClassP> getList(int programT, String table) throws GenericException {
        try {
            List<RelationalClassP> data = new ArrayList<>();
            switch (table) {
                case "PtCompetitionG":
                    List<PtCompetitionG> listC = ptCompetitionGFacade.getList(programT);
                    for (PtCompetitionG col : listC) {
                        RelationalClassP aux = new RelationalClassP(col.getId(), col.getFkPtcCompetitionG().getId(), col.getFkPtcCompetitionG().getName(), programT);
                        data.add(aux);
                    }
                    return data;
                case "PtDistinctive":
                    List<PtDistinctive> listD = ptDistinctiveFacade.getList(programT);
                    for (PtDistinctive col : listD) {
                        RelationalClassP aux = new RelationalClassP(col.getId(), col.getFkPtdDistinctive().getId(), col.getFkPtdDistinctive().getName(), programT);
                        data.add(aux);
                    }
                    return data;
                case "PtOccupational":
                    List<PtOccupational> listO = ptOccupationalFacade.getList(programT);
                    for (PtOccupational col : listO) {
                        RelationalClassP aux = new RelationalClassP(col.getId(), col.getFkPtoOccupational().getId(), col.getFkPtoOccupational().getName(), programT);
                        data.add(aux);
                    }
                    return data;
                case "PtProfessional":
                    List<PtProfessional> listP = ptProfessionalFacade.getList(programT);
                    for (PtProfessional col : listP) {
                        RelationalClassP aux = new RelationalClassP(col.getId(), col.getFkPtpProfessional().getId(), col.getFkPtpProfessional().getName(), programT);
                        data.add(aux);
                    }
                    return data;
                case "PtThematic":
                    List<PtThematic> listT = ptThematicFacade.getList(programT);
                    for (PtThematic col : listT) {
                        RelationalClassP aux = new RelationalClassP(col.getId(), col.getFkPttThematic().getId(), col.getFkPttThematic().getName(), programT);
                        data.add(aux);
                    }
                    return data;
                default:
                    throw new GenericException("error server");

            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public String add(RelationalClassP relation) throws GenericException {
        try {
            ProgramThematicCore pro = programThematicCoreFacade.find(relation.getIdProgramaThematic());
            boolean valid = false;
            switch (relation.getTable()) {
                case "PtCompetitionG":
                    List<PtCompetitionG> listC = ptCompetitionGFacade.getList(pro.getId());
                    for (PtCompetitionG lis : listC) {
                        if (lis.getFkPtcCompetitionG().getId() == relation.getIdRelation()) {
                            valid = true;
                        }
                    }
                    if (!valid) {
                        CompetitionGeneral com = competitionGerenelFacade.find(relation.getIdRelation());
                        PtCompetitionG dataP = new PtCompetitionG(com, pro);
                        ptCompetitionGFacade.create(dataP);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_COMPETITION_G");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "addC";
                case "PtDistinctive":
                    List<PtDistinctive> listD = ptDistinctiveFacade.getList(pro.getId());
                    for (PtDistinctive lis : listD) {
                        if (lis.getFkPtdDistinctive().getId() == relation.getIdRelation()) {
                            valid = true;
                        }
                    }
                    if (!valid) {
                        DistinctiveFeature dis = distinctiveFeatureFacade.find(relation.getIdRelation());
                        PtDistinctive dataD = new PtDistinctive(dis, pro);
                        ptDistinctiveFacade.create(dataD);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_DISTINCTIVE");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "addD";
                case "PtOccupational":
                    List<PtOccupational> listO = ptOccupationalFacade.getList(pro.getId());
                    for (PtOccupational lis : listO) {
                        if (lis.getFkPtoOccupational().getId() == relation.getIdRelation()) {
                            valid = true;
                        }
                    }
                    if (!valid) {
                        OccupationalProfile occ = occupationalProfileFacade.find(relation.getIdRelation());
                        PtOccupational dataO = new PtOccupational(occ, pro);
                        ptOccupationalFacade.create(dataO);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_OCCUPATIONAL");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "addO";
                case "PtProfessional":
                    List<PtProfessional> listP = ptProfessionalFacade.getList(pro.getId());
                    for (PtProfessional lis : listP) {
                        if (lis.getFkPtpProfessional().getId() == relation.getIdRelation()) {
                            valid = true;
                        }
                    }
                    if (!valid) {
                        ProfessionalProfile prof = professionalProfielFacade.find(relation.getIdRelation());
                        PtProfessional dataPf = new PtProfessional(prof, pro);
                        ptProfessionalFacade.create(dataPf);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_PROFESSIONAL");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "addP";
                case "PtThematic":
                    List<PtThematic> listT = ptThematicFacade.getList(pro.getId());
                    for (PtThematic lis : listT) {
                        if (lis.getFkPttThematic().getId() == relation.getIdRelation()) {
                            valid = true;
                        }
                    }
                    if (!valid) {
                        Thematic the = thematicFacade.find(relation.getIdRelation());
                        PtThematic dataT = new PtThematic(the, pro);
                        ptThematicFacade.create(dataT);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_THEMATIC");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "addT";
                default:
                    throw new GenericException("error server");
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public String delete(RelationalClassP relation) throws GenericException {
        try {
            switch (relation.getTable()) {
                case "PtCompetitionG":;
                    PtCompetitionG dataC = ptCompetitionGFacade.find(relation.getId());
                    if (dataC != null) {
                        ptCompetitionGFacade.remove(dataC);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_COMPETITION_G");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "deleteC";
                case "PtDistinctive":
                    PtDistinctive dataD = ptDistinctiveFacade.find(relation.getId());
                    if (dataD != null) {
                        ptDistinctiveFacade.remove(dataD);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_DISTINCTIVE");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "deleteD";
                case "PtOccupational":
                    PtOccupational dataO = ptOccupationalFacade.find(relation.getId());
                    if (dataO != null) {
                        ptOccupationalFacade.remove(dataO);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_OCCUPATIONAL");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "deleteO";
                case "PtProfessional":
                    PtProfessional dataP = ptProfessionalFacade.find(relation.getId());
                    if (dataP != null) {
                        ptProfessionalFacade.remove(dataP);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_PROFESSIONAL");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "deleteP";
                case "PtThematic":
                    PtThematic dataT = ptThematicFacade.find(relation.getId());
                    if (dataT != null) {
                        ptThematicFacade.remove(dataT);
                        relation.getRequestData().setTablaInvolucrada("TBL_PT_THEMATIC");
                        bitacora.registrarEnBitacora(relation.getRequestData());
                    }
                    return "deleteT";
                default:
                    throw new GenericException("error server");
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
