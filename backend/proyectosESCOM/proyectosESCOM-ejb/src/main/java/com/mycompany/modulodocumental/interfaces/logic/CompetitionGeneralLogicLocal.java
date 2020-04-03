/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.CompetitionGeneralP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface CompetitionGeneralLogicLocal {

    List<CompetitionGeneralP> getList(int program) throws GenericException;

    CompetitionGeneralP get(int id) throws GenericException;

    void add(CompetitionGeneralP competitionGeneral) throws GenericException;
    
    void edit(CompetitionGeneralP competitionGeneral) throws GenericException;

    void delete(CompetitionGeneralP competitionGeneral) throws GenericException;

}
