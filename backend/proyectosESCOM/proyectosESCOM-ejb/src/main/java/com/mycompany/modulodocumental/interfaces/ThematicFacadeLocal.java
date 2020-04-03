/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.Thematic;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ThematicFacadeLocal {

    void create(Thematic thematic);

    void edit(Thematic thematic);

    void remove(Thematic thematic);

    Thematic find(Object id);

    List<Thematic> findAll();

    List<Thematic> findRange(int[] range);

    int count();
    
    List<Thematic> getList(int general);
}
