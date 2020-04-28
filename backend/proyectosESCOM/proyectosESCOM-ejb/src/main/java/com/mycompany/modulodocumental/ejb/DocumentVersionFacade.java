package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.DocumentVersionFacadeLocal;
import com.mycompany.modulodocumental.entity.DocumentVersion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the document version entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class DocumentVersionFacade extends AbstractFacade<DocumentVersion> implements DocumentVersionFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentVersionFacade() {
        super(DocumentVersion.class);
    }

    /**
     * This method returns the list of versions of the current document
     *
     * @param idDocument
     * @return
     */
    @Override
    public List<DocumentVersion> listCurrentVersions(int idDocument) {
        Query query = em.createQuery("SELECT v FROM DocumentVersion v WHERE v.fkDvDocument.id = ?1 and v.state <> 2 ORDER BY V.state DESC");
        query.setParameter(1, idDocument);
        List<DocumentVersion> list = query.getResultList();
        return list;
    }

    /**
     * This method returns the list of previous document versions
     *
     * @param idProgram
     * @return
     */
    @Override
    public List<DocumentVersion> listOldVersions(int idProgram) {
        Query query = em.createQuery("SELECT v FROM DocumentVersion v WHERE v.fkDvDocument.fkDocProgram.id = ?1 and v.state = 2 ORDER BY V.date DESC");
        query.setParameter(1, idProgram);
        List<DocumentVersion> list = query.getResultList();
        return list;
    }

}
