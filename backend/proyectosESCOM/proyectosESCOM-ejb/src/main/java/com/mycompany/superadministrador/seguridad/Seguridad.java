package com.mycompany.superadministrador.seguridad;

import com.mycompany.superadministrador.ejb.UsuarioFacade;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Alejandra Pabon Rodriguez 461 215 234 Clase seguridad que genera
 * token con jwt
 */
public class Seguridad {

    private static final long tiempo = System.currentTimeMillis();
    private static final long expiraToken = TimeUnit.MINUTES.toMillis(7200);

    /**
     * metodo que genera el token
     *
     * @param usuarios
     *
     * @param usuario
     * @param clave
     * @return
     */
    public String generarToken(Usuario usuarios) {
        String token = Jwts.builder()
                .setSubject(usuarios.getNombre())
                .setIssuedAt(new Date(tiempo))
                .setExpiration(new Date(tiempo + expiraToken))
                .setIssuer(usuarios.getNombre())
                .signWith(SignatureAlgorithm.HS256, "A4J7A3prcc20")
                .compact();
        return token;
    }

    /**
     * metodo que desencripta el token
     *
     * @param token
     *
     * @return
     */
    public static String desencriptar(String token) {
        Jws parseClaimJws = Jwts.parser().setSigningKey("A4J7A3prcc20").parseClaimsJws(token);
        System.out.println("Header   " + parseClaimJws.getHeader());
        System.out.println("Body     " + parseClaimJws.getBody());
        System.out.println("Signature   " + parseClaimJws.getSignature());

        String tokencito = Jwts.parser().setSigningKey("A4J7A3prcc20").parseClaimsJws(token).getBody().getIssuer();

        return tokencito;
    }
}
