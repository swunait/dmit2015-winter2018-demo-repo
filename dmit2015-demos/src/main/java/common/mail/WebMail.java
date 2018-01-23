package common.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class WebMail {

	public static void main(String[] args) {
		// Steps to send a message using JavaMail API.
		// 1) Place properties the session a "Properties" object
		Properties props = new Properties();
		props.setProperty("mail.debug", "true");
		// 2) Start a mail session with the "Session.getInstance()" method.
		Session currentSesion = Session.getInstance(props);
		// 3) Create a new "Message" object
		MimeMessage currentMessage = new MimeMessage(currentSesion);
		Transport currentTransport = null;
		try {
			// 4) Set the message's From: address
			Address fromAddress = new InternetAddress("yourUsername@gmail.com","Your Name");
			currentMessage.setFrom(fromAddress);
			// 5) Set the message's To: address
			Address toAddress = new InternetAddress("someone@somedomain.com", "Receipient Name");
			currentMessage.setRecipient(Message.RecipientType.TO, toAddress);
			// 6) Set the message's subject
			currentMessage.setSubject("DMIT2015 - JavaMail API test");
			// 7) Set the content of the message
			currentMessage.setText("You will be assimilated! Resistance is futile.");
			// 8) Get a "Transport" from the session
			currentTransport = currentSesion.getTransport("smtps");
			// 9) Connect the transport to a named host using a username and password
			currentTransport.connect("smtp.gmail.com", "myUsername", "myPassword");
			// 10) Send the message to all recipients over the transport
			currentTransport.sendMessage(currentMessage, currentMessage.getAllRecipients());
			System.out.println("Send mail message was successful");
		} catch (UnsupportedEncodingException | MessagingException e) {
			System.out.println("Send mail meassage was not successful");
			e.printStackTrace();
		} finally {
			// Transport does not implement AutoCloseable
			if (currentTransport != null) {
				try {
					currentTransport.close();
				} catch (MessagingException e) {
					
				}
			}
		}
		
		

	}

}
