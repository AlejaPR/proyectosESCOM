/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.ProfessionalProfile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ProfessionalProfileFacadeLocal {

    void create(ProfessionalProfile professionalProfile);

    void edit(ProfessionalProfile professionalProfile);

    void remove(ProfessionalProfile professionalProfile);

    ProfessionalProfile find(Object id);

    List<ProfessionalProfile> findAll();

    List<ProfessionalProfile> findRange(int[] range);

    int count();
    
    List<ProfessionalProfile> getList(int general);
}
