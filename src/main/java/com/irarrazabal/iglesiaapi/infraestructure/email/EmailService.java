package com.irarrazabal.iglesiaapi.infraestructure.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    private void sendMail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }


    public void sendVerificationMail(String to, String verificationLink) {


        String body = """
        Bienvenido a Iglesia Cristiana Amanecer.

        Para activar tu cuenta haz clic en el siguiente enlace:

        %s

        Si no solicitaste esta cuenta puedes ignorar este correo.
        """
                .formatted(verificationLink);

        sendMail(to,
                "Confirma tu cuenta",
                to);
    }


    public void sendResetPasswordEmail(String to, String resetLink) {

        String body = """
                Hola.
                
                Recibimos una solicitud para restablecer tu contraseña.
                
                Haz clic en el siguiente enlace:
                
                %s
                
                Si no realizaste esta solicitud, puedes ignorar este correo.
        
        """
                .formatted(resetLink);

        sendMail(to,
                "Recuperación de contraseña",
                body);


    }

}
