/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.ThematicCore;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ThematicCoreFacadeLocal {

    void create(ThematicCore thematicCore);

    void edit(ThematicCore thematicCore);

    void remove(ThematicCore thematicCore);

    ThematicCore find(Object id);

    List<ThematicCore> findAll();

    List<ThematicCore> findRange(int[] range);

    int count();
    
}
