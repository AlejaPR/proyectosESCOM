/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.AnnexP;
import com.mycompany.modulodocumental.pojo.SearchAnnP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface AnnexLogicFacadeLocal {

    public void addAnnex(AnnexP annex) throws GenericException;

    public void editAnnex(AnnexP annex) throws GenericException;

    public void disableAnnex(int id, DatosSolicitudPOJO dataR) throws GenericException;

    public List<AnnexP> listAnnex(int id) throws GenericException;

    public AnnexP getAnnexId(int id) throws GenericException;

    public List<AnnexP> searchAnnexS(SearchAnnP search) throws GenericException;

}
