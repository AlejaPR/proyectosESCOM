/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadminisrador.logica;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author aleja
 */
@Stateless
public class LogicaModulo implements LogicaModuloFacadeLocal {

    @EJB
    ModuloFacadeLocal moduloDB;
    
    @EJB
    ActividadFacadeLocal actividadDB;
    
     /**Metodo que llama a la consulta para obtener la lista de modulos
     * @return 
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     **/
    @Override
    public List<ModuloPOJO> devolverModulos() throws ExcepcionGenerica {
         try{
          List<ModuloPOJO> modulosResultado=moduloDB.listarModulos();
            if(!modulosResultado.isEmpty()){
                return modulosResultado;
            }else{
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    /**Metodo que llama a la consulta para buscar la lista de actividades de un modulo
     * 
     * @param idModulo
     * @return 
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     **/
    @Override
    public List<ActividadPOJO> listarActividadesModulo(int idModulo) throws ExcepcionGenerica {
        try{    
                List<ActividadPOJO> listaActividadesM = new ArrayList();
                listaActividadesM = actividadDB.listarActividadesModulo(idModulo);
                if(listaActividadesM.size() >=0 ){
                    
                  return listaActividadesM;   
                }else{
                     throw new NoResultException("Error en la consulta");
                }  
        }catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    
}
