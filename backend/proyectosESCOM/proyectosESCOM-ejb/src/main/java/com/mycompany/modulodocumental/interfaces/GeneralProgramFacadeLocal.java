/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.GeneralProgram;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface GeneralProgramFacadeLocal {

    void create(GeneralProgram generalProgram);

    void edit(GeneralProgram generalProgram);

    void remove(GeneralProgram generalProgram);

    GeneralProgram find(Object id);

    List<GeneralProgram> findAll();

    List<GeneralProgram> findRange(int[] range);

    int count();
    
}
