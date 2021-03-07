package com.epam.brs.util.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private MimeMessage message;
    private String addressee;
    private String topic;
    private String text;
    private Properties properties;

    public MailSender(String addressee, String topic, String text, Properties properties) {
        this.addressee = addressee;
        this.topic = topic;
        this.text = text;
        this.properties = properties;
    }

    public void send() {
        try {
            initMessage();
        } catch (MessagingException e) {
            logger.error("Error while initializing message", e);
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error("Error while sending message" + message, e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = MailSessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(topic);
        message.setContent(text, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(addressee));
    }
}
