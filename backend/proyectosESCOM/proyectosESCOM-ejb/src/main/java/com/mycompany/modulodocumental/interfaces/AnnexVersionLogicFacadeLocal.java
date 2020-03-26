/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.AnnexVersionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface AnnexVersionLogicFacadeLocal {

    public List<AnnexVersionP> listAnnexVersion(int id) throws GenericException;

    public void addAnnexVersion(AnnexVersionP annexV) throws GenericException;

    public void deleteVersion(int id, DatosSolicitudPOJO dataR) throws GenericException;

}
