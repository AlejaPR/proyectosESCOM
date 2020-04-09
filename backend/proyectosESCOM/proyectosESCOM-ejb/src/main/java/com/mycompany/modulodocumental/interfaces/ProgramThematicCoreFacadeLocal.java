/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.ProgramThematicCore;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ProgramThematicCoreFacadeLocal {

    void create(ProgramThematicCore programThematicCore);

    void edit(ProgramThematicCore programThematicCore);

    void remove(ProgramThematicCore programThematicCore);

    ProgramThematicCore find(Object id);

    List<ProgramThematicCore> findAll();

    List<ProgramThematicCore> findRange(int[] range);

    int count();
    
    List<ProgramThematicCore> getList(int program);
    
}
