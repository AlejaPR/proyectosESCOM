package com.mycompany.superadministrador.POJO;
import java.util.Date;
/**
 * Esta es la clase POJO de la entidad usuario
 * contiene las variables de la tabla sin anotaciones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class UsuarioPOJO {
    
   /**Variable id de usuario*/
   private int id;
   /**Variable correo de usuario*/
   private String correoElectronico;
   /**Variable estado de usuario*/
   private String estado;
   /**Variable numero de documento de usuario*/
   private int numeroDocumento;
   /**Variable nombre de usuario*/
   private String nombre;
   /**Variable apellido de usuario*/
   private String apellido;
   /**Variable clave de usuario*/
   private String contrasena;
   /**Variable fecha de nacimiento */
   private Date fechaNacimiento;
   /**Variable para tipo de documento*/
   private int tipoDocumento;
   /**Variable token de usuario*/
   private String token;
   /**Variable para generar formato a fecha nacimiento*/
   private String fechaDeNacimiento;
   /**Variable fecha de ingreso*/
   private Date fechaIngreso;
   /**Variable numero de intentos*/
   private int numeroIntentos;
   /**Variable datos solicitud para registrar bitacora*/
   private DatosSolicitudPOJO datosSolicitud;

   /**Constructor vacio de clase*/
    public UsuarioPOJO() {
    }
   
    /**Constructor con variables
     * @param correoElectronico
     * @param numeroDocumento
     * @param nombre
     * @param apellido
     * @param contrasena
     * @param fechaNacimiento
     * @param tipoDocumento
     * @param fechaIngreso
     * @param numeroIntentos
     */
    public UsuarioPOJO(String correoElectronico, int numeroDocumento, String nombre, String apellido, String contrasena, 
            Date fechaNacimiento, int tipoDocumento, Date fechaIngreso, int numeroIntentos) {
        this.correoElectronico = correoElectronico;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.fechaIngreso = fechaIngreso;
        this.numeroIntentos = numeroIntentos;
    }

    /**Metodos get y set de las variables*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public DatosSolicitudPOJO getDatosSolicitud() {
        return datosSolicitud;
    }

    public void setDatosSolicitud(DatosSolicitudPOJO datosSolicitud) {
        this.datosSolicitud = datosSolicitud;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(int numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }  
}
