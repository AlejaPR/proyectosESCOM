package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtProfessional;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program thematic - professional profile class.
 * Contains all the methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface PtProfessionalFacadeLocal {

    void create(PtProfessional ptProfessional);

    void edit(PtProfessional ptProfessional);

    void remove(PtProfessional ptProfessional);

    PtProfessional find(Object id);

    List<PtProfessional> findAll();

    List<PtProfessional> findRange(int[] range);

    int count();

    List<PtProfessional> getList(int programT);

}
