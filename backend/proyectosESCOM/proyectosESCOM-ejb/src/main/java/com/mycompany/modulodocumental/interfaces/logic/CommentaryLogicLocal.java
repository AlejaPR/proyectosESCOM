/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

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
public interface CommentaryLogicLocal {

    List<CommentaryP> getList(int activity) throws GenericException;

    void delete(int idCommentary, DatosSolicitudPOJO dataS) throws GenericException;

    void add(CommentaryP commentary) throws GenericException;

}
