package com.epam.brs.model.service.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionFactory {
    private static final String EMAIL_KEY = "mail.user.email";
    private static final String PASSWORD_KEY = "mail.user.password";

    public static Session createSession(Properties configProperties) {
        String userEmail = configProperties.getProperty(EMAIL_KEY);
        String userPassword = configProperties.getProperty(PASSWORD_KEY);
        return Session.getDefaultInstance(configProperties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, userPassword);
            }
        });
    }
}
