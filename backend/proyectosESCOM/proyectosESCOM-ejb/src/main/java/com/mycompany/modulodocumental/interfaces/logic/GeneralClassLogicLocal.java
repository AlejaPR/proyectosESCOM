/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.GeneralClassP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface GeneralClassLogicLocal {
    
    List<GeneralClassP> getList(int program, String table) throws GenericException;

    GeneralClassP get(int id, String table) throws GenericException;

    String add(GeneralClassP generalC) throws GenericException;
    
    String edit(GeneralClassP generalC) throws GenericException;

    String delete(GeneralClassP generalC) throws GenericException;
    
}
