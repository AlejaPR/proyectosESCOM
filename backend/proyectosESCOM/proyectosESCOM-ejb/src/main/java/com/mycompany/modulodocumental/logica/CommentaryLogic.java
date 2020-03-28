/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Activity;
import com.mycompany.modulodocumental.entity.Commentary;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.CommentaryFacadeLocal;
import com.mycompany.modulodocumental.interfaces.CommentaryLogicFacadeLocal;
import com.mycompany.modulodocumental.pojo.CommentaryP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author hashy
 */
@Stateless
public class CommentaryLogic implements CommentaryLogicFacadeLocal {

    @EJB
    private CommentaryFacadeLocal commentaryFacade;
    @EJB
    private ActivityFacadeLocal activityFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_COMMENTARY";

    private static final String CLASS = "Clase logica comentario";

    @Override
    public List<CommentaryP> listCommentary(int activity) throws GenericException {
        try {
            List<Commentary> list = commentaryFacade.listCommentary(activity);
            List<CommentaryP> data = new ArrayList<>();
            for (Commentary com : list) {
                List<UsuarioPOJO> userList = bitacora.devolverUsuariosModulo();
                for (UsuarioPOJO user : userList) {
                    if (user.getId() == com.getIdUser()) {
                        CommentaryP aux = new CommentaryP(com.getId(), com.getMessage(), com.getDate());
                        aux.setNameUser(user.getNombre());
                        data.add(aux);
                    }
                }
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista comentarios", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void deleteCommentary(int id, DatosSolicitudPOJO dataS) throws GenericException {
        try {
            Commentary data = commentaryFacade.find(id);
            commentaryFacade.remove(data);
            dataS.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataS);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar comentario", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void addCommentary(CommentaryP commentary) throws GenericException {
        try {
            UsuarioPOJO user = bitacora.devolverInformacionDeUsuario(commentary.getRequestData().getToken());
            Commentary data = new Commentary(commentary.getMessage(), commentary.getDate(), user.getId());
            Activity act = activityFacade.find(commentary.getIdActivity());
            data.setFkComActivity(act);
            commentaryFacade.create(data);
            commentary.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(commentary.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar comentario", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
