/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.RelationalClassP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface RelationalClassLogicLocal {

    List<RelationalClassP> getList(int programT, String table) throws GenericException;

    String add(RelationalClassP relation) throws GenericException;

    String delete(RelationalClassP relation) throws GenericException;

}
