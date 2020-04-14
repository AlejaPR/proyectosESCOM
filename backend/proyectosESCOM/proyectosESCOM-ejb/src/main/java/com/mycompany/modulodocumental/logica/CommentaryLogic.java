package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Activity;
import com.mycompany.modulodocumental.entity.Commentary;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.CommentaryFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.CommentaryLogicLocal;
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
 * This is the class in charge of the commentary logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class CommentaryLogic implements CommentaryLogicLocal {

    /**
     * Commentary interface injection
     */
    @EJB
    private CommentaryFacadeLocal commentaryFacade;

    /**
     * Activity interface injection
     */
    @EJB
    private ActivityFacadeLocal activityFacade;

    /**
     * Bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_COMMENTARY";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica comentario";

    /**
     * *
     * method to get the list of comments
     *
     * @param activity
     * @return
     * @throws GenericException
     */
    @Override
    public List<CommentaryP> getList(int activity) throws GenericException {
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

    /**
     * method to delete a comment
     *
     * @param idCommentary
     * @param dataS
     * @throws GenericException
     */
    @Override
    public void delete(int idCommentary, DatosSolicitudPOJO dataS) throws GenericException {
        try {
            Commentary data = commentaryFacade.find(idCommentary);
            commentaryFacade.remove(data);
            dataS.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataS);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar comentario", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of adding a comment
     *
     * @param commentary
     * @throws GenericException
     */
    @Override
    public void add(CommentaryP commentary) throws GenericException {
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
