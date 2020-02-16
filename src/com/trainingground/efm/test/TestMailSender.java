package com.trainingground.efm.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.trainingground.efm.block.CodeBlock;
import com.trainingground.efm.block.CodeBlockSendEmail;
import com.trainingground.efm.datastruct.SafeMap;
import com.trainingground.efm.datastruct.graph.Graph;
import com.trainingground.efm.flow.CodeFlow;

public class TestMailSender {
	
    private static final String SMTP_SERVER = "smtps.aruba.it";
    private static final String USERNAME = "testmail@traininground.it";
    private static final String PASSWORD = "TroppoCorta13";

    private static final String EMAIL_FROM = "testmail@traininground.it";
    private static final String EMAIL_TO = "tonio.iuele@gmail.com";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";
	
	public static void main(String[] args) throws InterruptedException {
		Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER);
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Map<String, String> sendEmailMapping = new HashMap<String, String>();
		sendEmailMapping.put("SMTP_SERVER", "SMTP_SERVER");
		sendEmailMapping.put("USERNAME", "USERNAME");
		sendEmailMapping.put("PASSWORD", "PASSWORD");
		sendEmailMapping.put("EMAIL_FROM", "EMAIL_FROM");
		sendEmailMapping.put("EMAIL_TO", "EMAIL_TO");
		sendEmailMapping.put("EMAIL_TO_CC", "EMAIL_TO_CC");
		sendEmailMapping.put("EMAIL_SUBJECT", "EMAIL_SUBJECT");
		sendEmailMapping.put("EMAIL_TEXT", "EMAIL_TEXT");
		
		Graph<CodeBlock> graph = new Graph<>();

        CodeBlockSendEmail sendEmailBlock = new CodeBlockSendEmail();
        sendEmailBlock.setMapping(sendEmailMapping);
        
        graph.addNode(sendEmailBlock);
        
		CodeFlow cf = new CodeFlow();
		cf.setFlow(graph);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("SMTP_SERVER", SMTP_SERVER);
		parameters.put("USERNAME", USERNAME);
		parameters.put("PASSWORD", PASSWORD);
		parameters.put("EMAIL_FROM", EMAIL_FROM);
		parameters.put("EMAIL_TO", EMAIL_TO);
		parameters.put("EMAIL_TO_CC", EMAIL_TO_CC);
		parameters.put("EMAIL_SUBJECT", EMAIL_SUBJECT);
		parameters.put("EMAIL_TEXT", EMAIL_TEXT);
		cf.setParameters(new SafeMap<String, Object>(parameters));

		cf.launch();
	}

}
