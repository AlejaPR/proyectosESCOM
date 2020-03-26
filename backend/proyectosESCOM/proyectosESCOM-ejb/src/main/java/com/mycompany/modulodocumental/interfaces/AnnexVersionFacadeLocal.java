/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.AnnexVersion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HASHY
 */
@Local
public interface AnnexVersionFacadeLocal {

    void create(AnnexVersion annexVersion);

    void edit(AnnexVersion annexVersion);

    void remove(AnnexVersion annexVersion);

    AnnexVersion find(Object id);

    List<AnnexVersion> findAll();

    List<AnnexVersion> findRange(int[] range);

    int count();
    
    List<AnnexVersion> listAnnexVersion(int id);
    
}
