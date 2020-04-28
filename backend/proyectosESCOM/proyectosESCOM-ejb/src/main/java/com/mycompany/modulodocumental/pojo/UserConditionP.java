package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the thematic core entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class UserConditionP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable id condition
     */
    private int idCondition;

    /**
     * variable id user
     */
    private int idUser;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public UserConditionP() {
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(int idCondition) {
        this.idCondition = idCondition;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
