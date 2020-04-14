package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.AnnexVersionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical annex version class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface AnnexVersionLogicLocal {

    public List<AnnexVersionP> getList(int idAnnex) throws GenericException;

    public void add(AnnexVersionP annexV) throws GenericException;

    public void delete(int idAnnexV, DatosSolicitudPOJO dataR) throws GenericException;

}
