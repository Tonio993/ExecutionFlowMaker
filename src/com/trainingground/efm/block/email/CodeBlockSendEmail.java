package com.trainingground.efm.block.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.datastruct.SafeEntry;

public class CodeBlockSendEmail extends CodeBlock {
	
	private static final String SMTP_SERVER = "SMTP_SERVER";
	private static final String USERNAME = "USERNAME";
	private static final String PASSWORD = "PASSWORD";
	private static final String EMAIL_FROM = "EMAIL_FROM";
	private static final String EMAIL_TO = "EMAIL_TO";
	private static final String EMAIL_TO_CC = "EMAIL_TO_CC";
	private static final String EMAIL_SUBJECT = "EMAIL_SUBJECT";
	private static final String EMAIL_TEXT = "EMAIL_TEXT";

	@Override
	protected void execute() throws InterruptedException {
		SafeEntry<String, Object> smtpServer = parameters.get(SMTP_SERVER);
		SafeEntry<String, Object> username = parameters.get(USERNAME);
		SafeEntry<String, Object> password = parameters.get(PASSWORD);
		SafeEntry<String, Object> emailFrom = parameters.get(EMAIL_FROM);
		SafeEntry<String, Object> emailTo = parameters.get(EMAIL_TO);
		SafeEntry<String, Object> emailToCc = parameters.get(EMAIL_TO_CC);
		SafeEntry<String, Object> emailSubject = parameters.get(EMAIL_SUBJECT);
		SafeEntry<String, Object> emailText = parameters.get(EMAIL_TEXT);
		
        Session session = Session.getInstance(System.getProperties(), null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(emailFrom.get().toString()));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo.get().toString(), false));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailToCc.get().toString(), false));
            msg.setSubject(emailSubject.get().toString());
            msg.setDataHandler(new DataHandler(new HTMLDataSource(emailText.get().toString())));
            msg.setSentDate(new Date());
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
            t.connect(smtpServer.get().toString(), username.get().toString(), password.get().toString());
            t.sendMessage(msg, msg.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        smtpServer.release();
        username.release();
        password.release();
        emailFrom.release();
        emailTo.release();
        emailToCc.release();
        emailSubject.release();
        emailText.release();
        
        System.out.println("Email sended!");
		
	}
	
    static class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) throw new IOException("html message is null!");
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "HTMLDataSource";
        }
    }

}
