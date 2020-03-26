/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Annex;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HASHY
 */
@Local
public interface AnnexFacadeLocal {

    void create(Annex annex);

    void edit(Annex annex);

    void remove(Annex annex);

    Annex find(Object id);

    List<Annex> findAll();

    List<Annex> findRange(int[] range);

    int count();
    
    List<Annex> searchAnnex(int idProgram, String name);
    
}
