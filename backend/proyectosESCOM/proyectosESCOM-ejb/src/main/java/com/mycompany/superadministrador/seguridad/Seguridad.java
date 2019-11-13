package com.mycompany.superadministrador.seguridad;

import com.google.gson.Gson;
import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.entity.Actividad;
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
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Alejandra Pabon Rodriguez 461 215 234 Clase seguridad que genera
 * token con jwt
 */
public class Seguridad implements SeguridadFacadeLocal{


    /**
     * 
     * @param usuario
     * @param actividades
     * @return 
     */
    public String generarToken(Usuario usuario,String actividades) {
        Date fechaExpiracion = sumarRestarDiasFecha(new Date(), 10);
        String token = Jwts.builder()
                .setSubject(usuario.getNombre())
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .setIssuer(usuario.getCorreoElectronico())
                .signWith(SignatureAlgorithm.HS256, "A4J7A3prcc20")
                .claim("actividades",actividades)
                .compact();
        return token;
    }
    

    public static Token desencriptar(String token) {
        String clave="A4J7A3prcc20";
        Token tokenResultado = new Token();
        Jws parseClaimsJws = Jwts.parser().setSigningKey(clave).parseClaimsJws(token);
        
        
        
        //System.out.println("header "+parseClaimsJws.getHeader());
        tokenResultado.setHeader(parseClaimsJws.getHeader().toString());
        //System.out.println("body " + Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody());
        tokenResultado.setBody(parseClaimsJws.getBody().toString());
        //System.out.println("issuer " + Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody().getIssuer());
        tokenResultado.setIssuer(Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody().getIssuer());
        //System.out.println("signature " + parseClaimsJws.getSignature());
        tokenResultado.setFirma(parseClaimsJws.getSignature());
        
        return tokenResultado;
    }

    
    
    public static Date sumarRestarDiasFecha(Date fecha, int anos) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha); // Configuramos la fecha que se recibe

        calendar.add(Calendar.YEAR, anos);  // numero de minutos a añadir

        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public static String generarHash(String texto) {
        String respuesta = "";
        try {
            // Generamos una clave de 128 bits adecuada para AES
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();

            // Alternativamente, una clave que queramos que tenga al menos 16 bytes
            // y nos quedamos con los bytes 0 a 15
            key = new SecretKeySpec("h4eBMfS!Nr^.E8:Ye12".getBytes(), 0, 16, "AES");

            // Se obtiene un cifrador AES
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Se inicializa para encriptacion y se encripta el texto,
            // que debemos pasar como bytes.
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] encriptado = aes.doFinal(texto.getBytes());

            // Se escribe byte a byte en hexadecimal el texto
            // encriptado para ver su pinta.
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
