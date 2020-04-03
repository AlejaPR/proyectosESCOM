/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Process;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HASHY
 */
@Local
public interface ProcessFacadeLocal {

    void create(Process process);

    void edit(Process process);

    void remove(Process process);

    Process find(Object id);

    List<Process> findAll();

    List<Process> findRange(int[] range);

    int count();
    
    List<Process> listProcess(int idDocument);
    
}
