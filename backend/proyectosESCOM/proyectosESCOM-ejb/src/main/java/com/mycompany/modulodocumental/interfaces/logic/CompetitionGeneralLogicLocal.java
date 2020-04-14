package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.CompetitionGeneralP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical competition general class. Contains all
 * the methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface CompetitionGeneralLogicLocal {

    List<CompetitionGeneralP> getList(int program) throws GenericException;

    CompetitionGeneralP get(int id) throws GenericException;

    void add(CompetitionGeneralP competitionGeneral) throws GenericException;

    void edit(CompetitionGeneralP competitionGeneral) throws GenericException;

    void delete(CompetitionGeneralP competitionGeneral) throws GenericException;

}
