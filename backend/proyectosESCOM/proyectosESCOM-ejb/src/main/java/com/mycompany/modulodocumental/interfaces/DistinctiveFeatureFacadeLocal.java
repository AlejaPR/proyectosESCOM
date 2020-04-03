/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.DistinctiveFeature;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
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
