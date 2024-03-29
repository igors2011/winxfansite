package com.winxfansite.admin.dao;

import idao.admin.ILogAccess;
import idao.admin.IMailAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
@Component
public class MailAccess implements IMailAccess {
    @Autowired
    private ILogAccess logAccess;
    public void sendEmail(List<String> to, String subject, String body) throws MessagingException, IOException {
        // Настройки для подключения к почтовому серверу
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("admin/src/main/resources/settings/smtp.properties"))){
            props.load(in);
        }

        // Данные для аутентификации на почтовом сервере
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        // Создание сессии для отправки сообщений
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Создание сообщения
        Message message = new MimeMessage(session);
        for (String recipient : to) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        }
        message.setSubject(subject);
        message.setText(body);
        try {
            // Отправка сообщения
            Transport.send(message);
            logAccess.logInfo("Рассылка отправлена");
        }
        catch (Exception e) {
            logAccess.logError("Ошибка при отправке рассылки");
        }
    }
}
