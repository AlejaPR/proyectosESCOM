package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.entity.TipoDocumento;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase tipo documento
 * Contiene todos los metodos requeridos para la entidad tipo documento
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface TipoDocumentoFacadeLocal {

    void create(TipoDocumento tipoDocumento);

    void edit(TipoDocumento tipoDocumento);

    void remove(TipoDocumento tipoDocumento);

    TipoDocumento find(Object id);

    List<TipoDocumento> findAll();

    List<TipoDocumento> findRange(int[] range);

    int count();
    
    List<TipoDocumentoPOJO> consultaTipoDocumento();
  
}
