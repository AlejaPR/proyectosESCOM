package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.CommentaryP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical commentary class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface CommentaryLogicLocal {

    List<CommentaryP> getList(int activity) throws GenericException;

    void delete(int idCommentary, DatosSolicitudPOJO dataS) throws GenericException;

    void add(CommentaryP commentary) throws GenericException;

}
