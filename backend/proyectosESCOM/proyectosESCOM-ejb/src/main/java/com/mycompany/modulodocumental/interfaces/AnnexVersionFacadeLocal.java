package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.AnnexVersion;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the annex version class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface AnnexVersionFacadeLocal {

    void create(AnnexVersion annexVersion);

    void edit(AnnexVersion annexVersion);

    void remove(AnnexVersion annexVersion);

    AnnexVersion find(Object id);

    List<AnnexVersion> findAll();

    List<AnnexVersion> findRange(int[] range);

    int count();

    List<AnnexVersion> listAnnexVersion(int id);

}
