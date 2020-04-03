/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtCompetitionG;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface PtCompetitionGFacadeLocal {

    void create(PtCompetitionG ptCompetitionG);

    void edit(PtCompetitionG ptCompetitionG);

    void remove(PtCompetitionG ptCompetitionG);

    PtCompetitionG find(Object id);

    List<PtCompetitionG> findAll();

    List<PtCompetitionG> findRange(int[] range);

    int count();
    
}
