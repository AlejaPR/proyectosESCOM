/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

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
public interface AnnexVersionLogicLocal {

    public List<AnnexVersionP> getList(int idAnnex) throws GenericException;

    public void add(AnnexVersionP annexV) throws GenericException;

    public void delete(int idAnnexV, DatosSolicitudPOJO dataR) throws GenericException;

}
