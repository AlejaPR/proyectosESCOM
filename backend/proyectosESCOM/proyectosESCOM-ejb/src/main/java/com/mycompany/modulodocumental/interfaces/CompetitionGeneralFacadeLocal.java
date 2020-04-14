package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.CompetitionGeneral;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the competition general class. Contains all the methods required
 * for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface CompetitionGeneralFacadeLocal {

    void create(CompetitionGeneral competitionGeneral);

    void edit(CompetitionGeneral competitionGeneral);

    void remove(CompetitionGeneral competitionGeneral);

    CompetitionGeneral find(Object id);

    List<CompetitionGeneral> findAll();

    List<CompetitionGeneral> findRange(int[] range);

    int count();

    List<CompetitionGeneral> getList(int general);

}
