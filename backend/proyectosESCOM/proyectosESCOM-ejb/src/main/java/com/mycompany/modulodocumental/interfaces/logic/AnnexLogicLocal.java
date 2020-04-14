package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.AnnexP;
import com.mycompany.modulodocumental.pojo.SearchAnnP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical annex class. Contains all the methods
 * required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
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
