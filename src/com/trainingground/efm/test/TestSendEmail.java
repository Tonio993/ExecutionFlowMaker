package com.trainingground.efm.test;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class TestSendEmail {
    private static final String SMTP_SERVER = "smtps.aruba.it";
    private static final String USERNAME = "testmail@traininground.it";
    private static final String PASSWORD = "TroppoCorta13";

    private static final String EMAIL_FROM = "testmail@traininground.it";
    private static final String EMAIL_TO = "tonio.iuele@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static void main(String[] args) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));
            msg.setSubject(EMAIL_SUBJECT);
            msg.setText(EMAIL_TEXT);
            msg.setSentDate(new Date());
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
            
            System.out.println("Email sended!");
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
