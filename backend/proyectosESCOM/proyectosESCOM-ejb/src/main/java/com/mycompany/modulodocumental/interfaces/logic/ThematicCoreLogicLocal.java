/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ThematicCoreP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ThematicCoreLogicLocal {
    
    List<ThematicCoreP> getList(int program) throws GenericException;

    ThematicCoreP get(int id) throws GenericException;

    void add(ThematicCoreP thematic) throws GenericException;
    
    void edit(ThematicCoreP thematic) throws GenericException;

    void delete(ThematicCoreP thematic) throws GenericException;
    
}
