/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtProfessional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface PtProfessionalFacadeLocal {

    void create(PtProfessional ptProfessional);

    void edit(PtProfessional ptProfessional);

    void remove(PtProfessional ptProfessional);

    PtProfessional find(Object id);

    List<PtProfessional> findAll();

    List<PtProfessional> findRange(int[] range);

    int count();
    
    List<PtProfessional> getList(int programT);
    
}
