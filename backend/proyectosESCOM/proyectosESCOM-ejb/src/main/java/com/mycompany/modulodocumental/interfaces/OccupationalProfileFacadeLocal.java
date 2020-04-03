/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.OccupationalProfile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface OccupationalProfileFacadeLocal {

    void create(OccupationalProfile occupationalProfile);

    void edit(OccupationalProfile occupationalProfile);

    void remove(OccupationalProfile occupationalProfile);

    OccupationalProfile find(Object id);

    List<OccupationalProfile> findAll();

    List<OccupationalProfile> findRange(int[] range);

    int count();
    
}
