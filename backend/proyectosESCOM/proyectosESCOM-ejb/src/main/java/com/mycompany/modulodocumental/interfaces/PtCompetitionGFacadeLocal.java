package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtCompetitionG;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program thematic - competition general class.
 * Contains all the methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface PtCompetitionGFacadeLocal {

    void create(PtCompetitionG ptCompetitionG);

    void edit(PtCompetitionG ptCompetitionG);

    void remove(PtCompetitionG ptCompetitionG);

    PtCompetitionG find(Object id);

    List<PtCompetitionG> findAll();

    List<PtCompetitionG> findRange(int[] range);

    int count();

    List<PtCompetitionG> getList(int programT);
}
