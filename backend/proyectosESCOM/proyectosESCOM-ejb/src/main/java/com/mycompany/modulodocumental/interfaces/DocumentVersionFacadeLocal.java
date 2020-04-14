package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.DocumentVersion;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the document version class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface DocumentVersionFacadeLocal {

    void create(DocumentVersion documentVersion);

    void edit(DocumentVersion documentVersion);

    void remove(DocumentVersion documentVersion);

    DocumentVersion find(Object id);

    List<DocumentVersion> findAll();

    List<DocumentVersion> findRange(int[] range);

    int count();

    List<DocumentVersion> listCurrentVersions(int IdDocument);

    List<DocumentVersion> listOldVersions(int idProgram);

}
