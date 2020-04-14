package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.DistinctiveFeature;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the distinctive feature class. Contains all the
 * methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface DistinctiveFeatureFacadeLocal {

    void create(DistinctiveFeature distinctiveFeature);

    void edit(DistinctiveFeature distinctiveFeature);

    void remove(DistinctiveFeature distinctiveFeature);

    DistinctiveFeature find(Object id);

    List<DistinctiveFeature> findAll();

    List<DistinctiveFeature> findRange(int[] range);

    int count();

    List<DistinctiveFeature> getList(int general);

}
