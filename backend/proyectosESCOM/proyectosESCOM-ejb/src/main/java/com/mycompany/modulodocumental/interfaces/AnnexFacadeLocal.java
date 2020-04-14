package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Annex;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the annex class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
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
