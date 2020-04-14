package com.mycompany.superadministrador.seguridad;

import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.SeguridadFacadeLocal;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Calendar;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Esta es la clase encargada de generar el token con jwt
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
public class Seguridad implements SeguridadFacadeLocal {

    /**
     * Metodo que genera el token
     *
     * @param usuario
     * @param actividades
     * @return
     */
    public String generarToken(Usuario usuario, String actividades) {
        Date fechaExpiracion = sumarRestarDiasFecha(new Date(), 10);
        String token = Jwts.builder()
                .setSubject(usuario.getNombre())
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .setIssuer(usuario.getCorreoElectronico())
                .signWith(SignatureAlgorithm.HS256, "A4J7A3prcc20")
                .claim("actividades", actividades)
                .compact();
        return token;
    }
    
    /**
     * Metodo que genera el token
     *
     * @param usuario
     * @return
     */
    public String generarTokenRecuperar(String correoElectronico) {
        Date fechaExpiracion = sumarRestarDiasFecha(new Date());
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .setIssuer(correoElectronico)
                .signWith(SignatureAlgorithm.HS256, "A4J7A3prcc20")
                .compact();
        return token;
    }

    /**
     * Metodo que desencripta el token
     *
     * @param token
     * @return
     */
    public static Token desencriptar(String token) {
        String clave = "A4J7A3prcc20";
        Token tokenResultado = new Token();
        Jws parseClaimsJws = Jwts.parser().setSigningKey(clave).parseClaimsJws(token);
        tokenResultado.setHeader(parseClaimsJws.getHeader().toString());
        tokenResultado.setBody(parseClaimsJws.getBody().toString());
        tokenResultado.setIssuer(Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody().getIssuer());
        tokenResultado.setFirma(parseClaimsJws.getSignature());
        return tokenResultado;
    }

    /**
     * Metodo que suma o resta dias a la fecha del token
     *
     * @param fecha
     * @param anos
     * @return 
     *
     */
    public static Date sumarRestarDiasFecha(Date fecha, int anos) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe

        calendar.add(Calendar.YEAR, anos);  // numero de minutos a añadir

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    /**
     * Metodo que suma o resta dias a la fecha del token
     *
     * @param fecha
     * @return 
     *
     */
    public static Date sumarRestarDiasFecha(Date fecha) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe

        calendar.add(Calendar.HOUR, 24);  // numero de minutos a añadir

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    /**
     * Metodo que genera un hash al token para doble encriptacion
     *
     * @param texto
     * @return 
     *
     */
    public static String generarHash(String texto) {
        String respuesta = "";
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();
            key = new SecretKeySpec("h4eBMfS!Nr^.E8:Ye12".getBytes(), 0, 16, "AES");
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] encriptado = aes.doFinal(texto.getBytes());

            for (byte b : encriptado) {
                respuesta += Integer.toHexString(0xFF & b).toString();
            }
            return respuesta;

        } catch (IllegalBlockSizeException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }
}
