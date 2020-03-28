/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.CommentaryP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface CommentaryLogicFacadeLocal {

    List<CommentaryP> listCommentary(int activity) throws GenericException;

    void deleteCommentary(int id, DatosSolicitudPOJO dataS) throws GenericException;

    void addCommentary(CommentaryP commentary) throws GenericException;

}
