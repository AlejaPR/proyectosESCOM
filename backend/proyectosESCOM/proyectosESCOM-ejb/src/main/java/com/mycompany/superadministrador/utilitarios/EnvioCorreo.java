package com.mycompany.superadministrador.utilitarios;

import java.util.Properties;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase que envia un correo electronico
 *
 * @author jeison gaona - alejandra pabon
 */
public class EnvioCorreo {

    final String IPSERVIDOR = "http://localhost:3000";

    /**
     * Constructor de la clase
     */
    public EnvioCorreo() {
    }

    /**
     * Funcion que envia el correo
     *
     * @param mensaje
     * @param destinatario
     * @param asunto
     * @throws javax.mail.internet.AddressException
     */
    public void enviarCorreo(String mensaje, String destinatario, String asunto)
            throws AddressException, MessagingException {
        final String username = "admistrativosis6@gmail.com";
        final String password = "AdminSIS678916";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setSubject(asunto);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        message.setText(mensaje);
        message.setContent(mensaje, "text/html; charset=utf-8");
        Transport.send(message);

    }

    public String devolverEstructuraHTML(String usuario, String link) {
        String message = "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "\n"
                + "<head>\n"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "    <title>Recuperar Contraseña</title>\n"
                + "    <style>\n"
                + "        a:link,\n"
                + "        a:visited {\n"
                + "            background-color: #033d38;\n"
                + "            color: white;\n"
                + "            padding: 14px 25px;\n"
                + "            text-align: center;\n"
                + "            text-decoration: none;\n"
                + "            display: inline-block;\n"
                + "            border-radius: 6%;\n"
                + "        }\n"
                + "\n"
                + "        a:hover,\n"
                + "        a:active {\n"
                + "            background-color: #114f49;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>";
        message += "<body>\n"
                + "    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                + "        <tr>\n"
                + "            <td align=\"center\" valign=\"top\" bgcolor=\"#f5d500\" style=\"background-color:white;\"><br>\n"
                + "                <br>\n"
                + "                <table width=\"600\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n"
                + "                    <tr>\n"
                + "                        <td align=\"center\" valign=\"top\" bgcolor=\"#fedd02\"\n"
                + "                            style=\"background-color:#5e918c; padding:6px;\">\n"
                + "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                + "                                <tr>\n"
                + "                                    <td align=\"left\" valign=\"top\">\n"
                + "                                        <table width=\"97%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"7\"\n"
                + "                                            style=\"margin-bottom:10px;\">\n"
                + "                                            <tr style=\"background-color:white\">\n"
                + "                                                <div style=\"font-size:18px;\" align=\"center\"><b>Recuperar contraseña</b></div>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td align=\"left\" valign=\"top\"\n"
                + "                                                    style=\"font-family:Arial, Helvetica, sans-serif; color:#000000; font-size:14px;\">\n"
                + "                                                    <div><br>\n"
                + "                                                        Estimado usuario: " + usuario
                + "                                                        <br/>\n"
                + "                                                        Recibimos una solicitud para restablecer su contraseña del\n"
                + "                                                        sistema administrativo\n"
                + "                                                        tiene 24h para cambiarla atravez de este link:\n"
                + "                                                        <br />\n"
                + "                                                        <div align=\"center\">\n"
                + "                                                            <br><a href=\"" + link + "\">Cambiar\n"
                + "                                                                contraseña</a><br>\n"
                + "                                                        </div>\n"
                + "                                                    </div>\n"
                + "                                                </td>\n"
                + "                                            </tr>\n"
                + "                                        </table>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                            </table>\n"
                + "                        </td></tr></table><br><br></td></tr></table>\n"
                + "</body>\n"
                + "</html>";
        return message;
    }

    public String getIPSERVIDOR() {
        return IPSERVIDOR;
    }
    
    
}
