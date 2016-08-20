package problem2.notification.handler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;


public class Handle1 implements Handler{
	private String Message;
	
	public Handle1(String Message)
	{
		this.Message=Message;
	}

	@Override
	public void generateNotificationMessage() {
		// TODO Auto-generated method stub
		final String username = "sakthivel.k22@gmail.com";
		final String password = "$U&@@2388";

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

		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sakthivel.k22@gmail.com"));
			message.setRecipients(javax.mail.Message.RecipientType.TO,InternetAddress.parse("sakthivel.k22@gmail.com"));
			message.setSubject("Alert");
			message.setText("sakthivel.k22@gmail.com,"
					+"\n\n "+Message);

			// Transport.send(message);  -- Got blocked by GMAIL :HAHA funny --
			// Writing messages to a file
			
			BufferedWriter out;
			try {
				out = new BufferedWriter(new FileWriter("resource/dead.letter",true));
				out.write(Message.toString()+"\n");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
