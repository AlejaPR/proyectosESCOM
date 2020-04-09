/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtOccupational;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface PtOccupationalFacadeLocal {

    void create(PtOccupational ptOccupational);

    void edit(PtOccupational ptOccupational);

    void remove(PtOccupational ptOccupational);

    PtOccupational find(Object id);

    List<PtOccupational> findAll();

    List<PtOccupational> findRange(int[] range);

    int count();
    
    List<PtOccupational> getList(int programT);
    
}
