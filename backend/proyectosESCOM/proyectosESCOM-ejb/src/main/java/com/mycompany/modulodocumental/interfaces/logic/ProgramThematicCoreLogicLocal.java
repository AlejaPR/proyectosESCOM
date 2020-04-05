/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ProgramThematicCoreP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ProgramThematicCoreLogicLocal {

    List<ProgramThematicCoreP> getList(int program) throws GenericException;

    ProgramThematicCoreP get(int id) throws GenericException;

    void add(ProgramThematicCoreP programT) throws GenericException;

    void edit(ProgramThematicCoreP programT) throws GenericException;

    void delete(int id, DatosSolicitudPOJO dataS) throws GenericException;

}
