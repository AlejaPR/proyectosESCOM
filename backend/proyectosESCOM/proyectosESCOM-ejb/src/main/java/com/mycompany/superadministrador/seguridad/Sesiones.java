/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.seguridad;

import com.google.gson.Gson;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import io.jsonwebtoken.Jwts;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import javax.faces.bean.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jeiso
 */
@Singleton
public class Sesiones implements SesionesFacadeLocal {

    Map<String, Calendar> mapaSesiones = new HashMap<String, Calendar>();

    public Sesiones() {
    }

    @Override
    public boolean modificarVencimiento(String llave) {
        if (mapaSesiones.get(llave) == null) {
            return false;
        } else {
            if (validacionDeFecha(mapaSesiones.get(llave)) < 0) {
                mapaSesiones.remove(llave);
                return false;
            } else {
                Calendar fechaActualizada = sumarRestarDiasFecha(mapaSesiones.get(llave).getTime(), 30);
                mapaSesiones.put(llave, fechaActualizada);
                return true;
            }
        }
    }

    @Override
    public  int validacionDeFecha(Calendar fechaToken) {
        Calendar fechaHoy = new GregorianCalendar();
        String fechaHoyTransformada = formarFechaString(fechaHoy);
        String fechaTokenTransformada = formarFechaString(fechaToken);
        int minutos = minutesDiff(GetItemDate(fechaHoyTransformada), GetItemDate(fechaTokenTransformada));
        return minutos;
    }

    public String formarFechaString(Calendar sameDate) {
        String am_pm = "";
        if (sameDate.get(Calendar.AM_PM) == 1) {
            am_pm = "PM";
        } else {
            am_pm = "AM";
        }
        String second = "";
        if ((sameDate.get(Calendar.SECOND) >= 0) & (sameDate.get(Calendar.SECOND) <= 9)) {
            second = sameDate.get(Calendar.SECOND) + "" + sameDate.get(Calendar.SECOND);
        } else {
            second = sameDate.get(Calendar.SECOND)+"";
        }
        return sameDate.get(Calendar.MONTH) + 1 + "/" + sameDate.get(Calendar.DAY_OF_MONTH) + "/" + sameDate.get(Calendar.YEAR) + " "
                + "" + sameDate.get(Calendar.HOUR_OF_DAY) + ":" + sameDate.get(Calendar.MINUTE) + ":" +second+ " " + am_pm;
    }

    public static Date GetItemDate(final String date) {
        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
        format.setCalendar(cal);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static int minutesDiff(Date earlierDate, Date laterDate) {
        if (earlierDate == null || laterDate == null) {
            return 0;
        }
        return (int) ((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000));
    }

    public static Calendar sumarRestarDiasFecha(Date fecha, int minutos) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe

        calendar.add(Calendar.MINUTE, minutos);  // numero de minutos a añadir

        return calendar;// Devuelve el objeto Date con los nuevos días añadidos
    }
    
    @Override
    public boolean validarPermiso(String llave,String permisoRequerido){
        Gson gson=new Gson();
        String claim = Jwts.parser().setSigningKey("A4J7A3prcc20").parseClaimsJws(llave).getBody().get("actividades", String.class);
        ActividadPOJO[] actividadesAsignadas = gson.fromJson(claim,
                ActividadPOJO[].class); 
        for(ActividadPOJO ap : actividadesAsignadas){
            if(ap.getNombre().equals(permisoRequerido)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, Calendar> getMapaSesiones() {
        return mapaSesiones;
    }

    public void setMapaSesiones(Map<String, Calendar> mapaSesiones) {
        this.mapaSesiones = mapaSesiones;
    }
}
