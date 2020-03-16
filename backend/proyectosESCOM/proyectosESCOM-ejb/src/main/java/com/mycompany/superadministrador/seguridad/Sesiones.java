package com.mycompany.superadministrador.seguridad;
import com.google.gson.Gson;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import io.jsonwebtoken.Jwts;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.ejb.Singleton;
/**
 * Esta es la clase encargada del manejo de sesiones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Singleton
public class Sesiones implements SesionesFacadeLocal {

    /**Mapa de sesiones de usuario*/
    Map<String, Calendar> mapaSesiones = new HashMap<String, Calendar>();

    /**Constructo vacio de la clase**/
    public Sesiones() {
    }

    /**Metodo que modifica el vencimiento del token
     * @param llave
     * @return 
     **/
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

    /**Metodo que valida la fecha del token
     * @param fechaToken
     * @return 
     **/
    @Override
    public  int validacionDeFecha(Calendar fechaToken) {
        Calendar fechaHoy = new GregorianCalendar();
        String fechaHoyTransformada = formarFechaString(fechaHoy);
        String fechaTokenTransformada = formarFechaString(fechaToken);
        int minutos = minutesDiff(GetItemDate(fechaHoyTransformada), GetItemDate(fechaTokenTransformada));
        return minutos;
    }

    /**Metodo para formar la fecha de vencimiento del token
     * @param sameDate
     * @return 
     **/
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

    /**Metodo para generar formato de fecha
     * @param date
     * @return 
     **/
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

    /**Metodo para agregar minutos a fecha
     * @param earlierDate
     * @param laterDate
     * @return 
     **/
    public static int minutesDiff(Date earlierDate, Date laterDate) {
        if (earlierDate == null || laterDate == null) {
            return 0;
        }
        return (int) ((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000));
    }

    /**Metodo que suma o resta dias a la fecha del token
     * @param fecha
     * @param minutos
     * @return 
     **/
    public static Calendar sumarRestarDiasFecha(Date fecha, int minutos) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe

        calendar.add(Calendar.MINUTE, minutos);  // numero de minutos a añadir

        return calendar;// Devuelve el objeto Date con los nuevos días añadidos
    }
    
    /**Metodo que valida los permisos
     * @param llave
     * @param permisoRequerido
     * @return 
     **/
    @Override
    public boolean validarPermiso(String llave,String permisoRequerido){
        Gson gson=new Gson();
        String claim = Jwts.parser().setSigningKey("A4J7A3prcc20").parseClaimsJws(llave).getBody().get("actividades", String.class);
        String[] actividadesAsignadas = gson.fromJson(claim,
                String[].class); 
        for(String ap : actividadesAsignadas){
            if(ap.equals(permisoRequerido)){
                return true;
            }
        }
        return false;
    }

    /**Metodos get y set del mapa de sesiones**/
    @Override
    public Map<String, Calendar> getMapaSesiones() {
        return mapaSesiones;
    }

    public void setMapaSesiones(Map<String, Calendar> mapaSesiones) {
        this.mapaSesiones = mapaSesiones;
    }
}
