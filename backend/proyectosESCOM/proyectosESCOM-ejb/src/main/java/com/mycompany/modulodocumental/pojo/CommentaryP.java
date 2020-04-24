package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the POJO class of the commentary entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class CommentaryP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable message commentary
     */
    private String message;

    /**
     * variable date commentary
     */
    private Date date;

    /**
     * variable id user commentary
     */
    private int idUser;

    /**
     * variable name user commentary
     */
    private String nameUser;

    /**
     * variable id activity commentary
     */
    private int idActivity;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public CommentaryP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param message
     * @param date
     */
    public CommentaryP(int id, String message, Date date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
