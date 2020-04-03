/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

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
public interface AnnexLogicLocal {

    public void add(AnnexP annex) throws GenericException;

    public void edit(AnnexP annex) throws GenericException;

    public void disable(int id, DatosSolicitudPOJO dataR) throws GenericException;

    public List<AnnexP> getList(int idProgram) throws GenericException;

    public AnnexP get(int idAnnex) throws GenericException;

    public List<AnnexP> searchAnnexS(SearchAnnP search) throws GenericException;

}
