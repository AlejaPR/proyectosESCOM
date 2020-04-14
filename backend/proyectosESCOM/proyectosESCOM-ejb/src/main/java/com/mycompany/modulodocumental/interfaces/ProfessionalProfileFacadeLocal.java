package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.ProfessionalProfile;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the professional profile class. Contains all the
 * methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProfessionalProfileFacadeLocal {

    void create(ProfessionalProfile professionalProfile);

    void edit(ProfessionalProfile professionalProfile);

    void remove(ProfessionalProfile professionalProfile);

    ProfessionalProfile find(Object id);

    List<ProfessionalProfile> findAll();

    List<ProfessionalProfile> findRange(int[] range);

    int count();

    List<ProfessionalProfile> getList(int general);
}
