/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.ejb.ThematicFacadeLocal;
import com.mycompany.modulodocumental.interfaces.TrainingAreaFacadeLocal;
import com.mycompany.modulodocumental.entity.Competition;
import com.mycompany.modulodocumental.entity.DistinctiveFeature;
import com.mycompany.modulodocumental.entity.GeneralProgram;
import com.mycompany.modulodocumental.entity.OccupationalProfile;
import com.mycompany.modulodocumental.entity.ProfessionalProfile;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.entity.Thematic;
import com.mycompany.modulodocumental.entity.TrainingArea;
import com.mycompany.modulodocumental.interfaces.CompetitionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.DistinctiveFeatureFacadeLocal;
import com.mycompany.modulodocumental.interfaces.GeneralProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.OccupationalProfileFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProfessionalProfileFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.GeneralClassLogicLocal;
import com.mycompany.modulodocumental.pojo.GeneralClassP;
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
public class GeneralClassLogic implements GeneralClassLogicLocal {

    @EJB
    private ThematicFacadeLocal thematicFacade;
    @EJB
    private TrainingAreaFacadeLocal trainingAreaFacade;
    @EJB
    private OccupationalProfileFacadeLocal occupationalProfileFacade;
    @EJB
    private ProfessionalProfileFacadeLocal professionalProfielFacade;
    @EJB
    private DistinctiveFeatureFacadeLocal distinctiveFeatureFacade;
    @EJB
    private CompetitionFacadeLocal competitionFacade;
    @EJB
    private ProgramFacadeLocal programFacade;
    @EJB
    private GeneralProgramFacadeLocal generalProgramFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String CLASS = "Clase logica general";

    @Override
    public List<GeneralClassP> getList(int program, String table) throws GenericException {
        try {
            Program pro = programFacade.find(program);
            List<GeneralClassP> data = new ArrayList<>();
            switch (table) {
                case "Thematic":
                    List<Thematic> listT = pro.getFkProGeneral().getListThematic();
                    for (Thematic list1 : listT) {
                        GeneralClassP aux = new GeneralClassP(list1.getId(), list1.getName());
                        data.add(aux);
                    }
                    return data;
                case "TrainingArea":
                    List<TrainingArea> listTA = pro.getFkProGeneral().getListTrainingArea();
                    for (TrainingArea list1 : listTA) {
                        GeneralClassP aux = new GeneralClassP(list1.getId(), list1.getName());
                        data.add(aux);
                    }
                    return data;
                case "Competition":
                    List<Competition> listC = pro.getFkProGeneral().getListCompetition();
                    for (Competition list1 : listC) {
                        GeneralClassP aux = new GeneralClassP(list1.getId(), list1.getName());
                        data.add(aux);
                    }
                    return data;
                case "DistinctiveFeature":
                    List<DistinctiveFeature> listD = pro.getFkProGeneral().getListDistinctiveFeature();
                    for (DistinctiveFeature list1 : listD) {
                        GeneralClassP aux = new GeneralClassP(list1.getId(), list1.getName());
                        data.add(aux);
                    }
                    return data;
                case "OccupationalProfile":
                    List<OccupationalProfile> listO = pro.getFkProGeneral().getListOccupationalProfile();
                    for (OccupationalProfile list1 : listO) {
                        GeneralClassP aux = new GeneralClassP(list1.getId(), list1.getName());
                        data.add(aux);
                    }
                    return data;
                case "ProfessionalProfile":
                    List<ProfessionalProfile> listP = pro.getFkProGeneral().getListProfessionalProfile();
                    for (ProfessionalProfile list1 : listP) {
                        GeneralClassP aux = new GeneralClassP(list1.getId(), list1.getName());
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
    public GeneralClassP get(int id, String table) throws GenericException {
        try {
            switch (table) {
                case "Thematic":
                    Thematic thematic = thematicFacade.find(id);
                    GeneralClassP data = new GeneralClassP(thematic.getId(), thematic.getName());
                    return data;
                case "TrainingArea":
                    TrainingArea trainingArea = trainingAreaFacade.find(id);
                    GeneralClassP dataT = new GeneralClassP(trainingArea.getId(), trainingArea.getName());
                    return dataT;
                case "Competition":
                    Competition com = competitionFacade.find(id);
                    GeneralClassP dataC = new GeneralClassP(com.getId(), com.getName());
                    return dataC;
                case "DistinctiveFeature":
                    DistinctiveFeature distinctive = distinctiveFeatureFacade.find(id);
                    GeneralClassP dataD = new GeneralClassP(distinctive.getId(), distinctive.getName());
                    return dataD;
                case "OccupationalProfile":
                    OccupationalProfile occupational = occupationalProfileFacade.find(id);
                    GeneralClassP dataO = new GeneralClassP(occupational.getId(), occupational.getName());
                    return dataO;
                case "ProfessionalProfile":
                    ProfessionalProfile professional = professionalProfielFacade.find(id);
                    GeneralClassP dataP = new GeneralClassP(professional.getId(), professional.getName());
                    return dataP;
                default:
                    throw new GenericException("error server");
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public String add(GeneralClassP generalC) throws GenericException {
        try {
            GeneralProgram gen = generalProgramFacade.find(generalC.getIdGeneral());
            switch (generalC.getTable()) {
                case "Thematic":
                    Thematic data = new Thematic(generalC.getName());
                    data.setFkThGeneral(gen);
                    thematicFacade.create(data);
                    generalC.getRequestData().setTablaInvolucrada("TBL_THEMATIC");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "addT";
                case "TrainingArea":
                    TrainingArea dataT = new TrainingArea(generalC.getName());
                    dataT.setFkTaGeneral(gen);
                    trainingAreaFacade.create(dataT);
                    generalC.getRequestData().setTablaInvolucrada("TBL_TRAINING_AREA");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "addTr";
                case "Competition":
                    Competition dataC = new Competition(generalC.getName());
                    dataC.setFkCtGeneral(gen);
                    competitionFacade.create(dataC);
                    generalC.getRequestData().setTablaInvolucrada("TBL_COMPETITION");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "addC";
                case "DistinctiveFeature":
                    DistinctiveFeature dataD = new DistinctiveFeature(generalC.getName());
                    dataD.setFkDfGeneral(gen);
                    distinctiveFeatureFacade.create(dataD);
                    generalC.getRequestData().setTablaInvolucrada("TBL_DISTINCTIVE_FEACTURE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "addD";
                case "OccupationalProfile":
                    OccupationalProfile dataO = new OccupationalProfile(generalC.getName());
                    dataO.setFkOpGeneral(gen);
                    occupationalProfileFacade.create(dataO);
                    generalC.getRequestData().setTablaInvolucrada("TBL_OCCUPATIONAL_PROFILE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "addO";
                case "ProfessionalProfile":
                    ProfessionalProfile dataP = new ProfessionalProfile(generalC.getName());
                    dataP.setFkPpGeneral(gen);
                    professionalProfielFacade.create(dataP);
                    generalC.getRequestData().setTablaInvolucrada("TBL_PROFESSIONAL_PROFILE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "addP";
                default:
                    throw new GenericException("error server");

            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public String edit(GeneralClassP generalC) throws GenericException {
        try {
            String table = "";
            switch (generalC.getTable()) {
                case "Thematic":
                    Thematic data = thematicFacade.find(generalC.getId());
                    data.setName(generalC.getName());
                    thematicFacade.edit(data);
                    generalC.getRequestData().setTablaInvolucrada("TBL_THEMATIC");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "editT";
                case "TrainingArea":
                    TrainingArea dataT = trainingAreaFacade.find(generalC.getId());
                    dataT.setName(generalC.getName());
                    trainingAreaFacade.edit(dataT);
                    generalC.getRequestData().setTablaInvolucrada("TBL_TRAINING_AREA");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "editTr";
                case "Competition":
                    Competition dataC = competitionFacade.find(generalC.getId());
                    dataC.setName(generalC.getName());
                    competitionFacade.edit(dataC);
                    generalC.getRequestData().setTablaInvolucrada("TBL_COMPETITION");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "editC";
                case "DistinctiveFeature":
                    DistinctiveFeature dataD = distinctiveFeatureFacade.find(generalC.getId());
                    dataD.setName(generalC.getName());
                    distinctiveFeatureFacade.edit(dataD);
                    generalC.getRequestData().setTablaInvolucrada("TBL_DISTINCTIVE_FEACTURE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "editD";
                case "OccupationalProfile":
                    OccupationalProfile dataO = occupationalProfileFacade.find(generalC.getId());
                    dataO.setName(generalC.getName());
                    occupationalProfileFacade.edit(dataO);
                    generalC.getRequestData().setTablaInvolucrada("TBL_OCCUPATIONAL_PROFILE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "editO";
                case "ProfessionalProfile":
                    ProfessionalProfile dataP = professionalProfielFacade.find(generalC.getId());
                    dataP.setName(generalC.getName());
                    professionalProfielFacade.edit(dataP);
                    generalC.getRequestData().setTablaInvolucrada("TBL_PROFESSIONAL_PROFILE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "editP";
                default:
                    throw new GenericException("error server");
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener lista", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public String delete(GeneralClassP generalC) throws GenericException {
        try {
            switch (generalC.getTable()) {
                case "Thematic":
                    Thematic data = thematicFacade.find(generalC.getId());
                    thematicFacade.remove(data);
                    generalC.getRequestData().setTablaInvolucrada("TBL_THEMATIC");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "deleteT";
                case "TrainingArea":
                    TrainingArea dataT = trainingAreaFacade.find(generalC.getId());
                    trainingAreaFacade.remove(dataT);
                    generalC.getRequestData().setTablaInvolucrada("TBL_TRAINING_AREA");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "deleteTr";
                case "Competition":
                    Competition dataC = competitionFacade.find(generalC.getId());
                    competitionFacade.remove(dataC);
                    generalC.getRequestData().setTablaInvolucrada("TBL_COMPETITION");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "deleteC";
                case "DistinctiveFeature":
                    DistinctiveFeature dataD = distinctiveFeatureFacade.find(generalC.getId());
                    distinctiveFeatureFacade.remove(dataD);
                    generalC.getRequestData().setTablaInvolucrada("TBL_DISTINCTIVE_FEACTURE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "deleteD";
                case "OccupationalProfile":
                    OccupationalProfile dataO = occupationalProfileFacade.find(generalC.getId());
                    occupationalProfileFacade.remove(dataO);
                    generalC.getRequestData().setTablaInvolucrada("TBL_OCCUPATIONAL_PROFILE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "deleteO";
                case "ProfessionalProfile":
                    ProfessionalProfile dataP = professionalProfielFacade.find(generalC.getId());
                    professionalProfielFacade.remove(dataP);
                    generalC.getRequestData().setTablaInvolucrada("TBL_PROFESSIONAL_PROFILE");
                    bitacora.registrarEnBitacora(generalC.getRequestData());
                    return "deleteP";
                default:
                    throw new GenericException("error server");
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
