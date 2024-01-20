package com.winxfansite.admin.dao;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
@Component
public class MailAccess {
    public void sendEmail(List<String> to, String subject, String body) throws MessagingException{
        // Настройки для подключения к почтовому серверу
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Данные для аутентификации на почтовом сервере
        String username = "igors20111@gmail.com";
        String password = "lixg obsb ggmv dogt";

        // Создание сессии для отправки сообщений
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Создание сообщения
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("igors20111@gmail.com"));
        for (String recipient : to) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        message.setSubject(subject);
        message.setText(body);

        // Отправка сообщения
        Transport.send(message);
    }
}
