/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.CompetitionGeneral;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface CompetitionGeneralFacadeLocal {

    void create(CompetitionGeneral competitionGeneral);

    void edit(CompetitionGeneral competitionGeneral);

    void remove(CompetitionGeneral competitionGeneral);

    CompetitionGeneral find(Object id);

    List<CompetitionGeneral> findAll();

    List<CompetitionGeneral> findRange(int[] range);

    int count();
    
    List<CompetitionGeneral> getList(int general);
    
}
