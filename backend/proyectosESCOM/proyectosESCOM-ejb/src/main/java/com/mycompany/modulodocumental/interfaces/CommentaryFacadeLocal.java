package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Commentary;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the commentary class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface CommentaryFacadeLocal {

    void create(Commentary commentary);

    void edit(Commentary commentary);

    void remove(Commentary commentary);

    Commentary find(Object id);

    List<Commentary> findAll();

    List<Commentary> findRange(int[] range);

    int count();

    List<Commentary> listCommentary(int activity);
}
