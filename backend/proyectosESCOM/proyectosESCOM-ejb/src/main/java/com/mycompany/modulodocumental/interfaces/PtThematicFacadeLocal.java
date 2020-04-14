/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtThematic;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program thematic - thematic class.
 * Contains all the methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface PtThematicFacadeLocal {

    void create(PtThematic ptThematic);

    void edit(PtThematic ptThematic);

    void remove(PtThematic ptThematic);

    PtThematic find(Object id);

    List<PtThematic> findAll();

    List<PtThematic> findRange(int[] range);

    int count();

    List<PtThematic> getList(int programT);

}
