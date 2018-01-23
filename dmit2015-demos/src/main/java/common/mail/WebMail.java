package common.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.pop3.POP3SSLStore;

public class WebMail {
	
	public static void sendmail() {
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
	
	public static void receivemail() {
		// Steps to read a remote mailbox (some steps are optional)
				// 1) Set up the properties you'll use for the connection
				Properties props = new Properties();
				props.setProperty("mail.pop3.host", "pop.gmail.com");
				props.setProperty("mail.pop3.port", "995");
				props.setProperty("mail.pop3.ssl.trust", "pop.gmail.com");
				props.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.setProperty("mail.pop3.socketFactory.fallback", "false");
				props.setProperty("mail.pop3.socketFactory.port", "995");
				// 2) Construct the "Authenticator" you'll use for authentication
				// 3) Get a "Session" object with "Session.getInstance()"
				try {
					URLName url = new URLName("pop3", "pop.gmail.com", 995, "", "myUserName", "myPassword");
					Session currentSession = Session.getInstance(props, null);
					// 4) Use the session's "getStore()" method to return a "Store"
					Store currentStore = new POP3SSLStore(currentSession, url);
					// 5) Connect to the store
					currentStore.connect();
					// 6) Get the INBOX folder from the store with the "getFolder()" method
					Folder inbox = currentStore.getFolder("INBOX");
					// 7) Open the INBOX folder
					inbox.open(Folder.READ_ONLY);
					// 8) Open the folder you want inside the INBOX folder
					// 9) Get the messages from the folder as an array of "Message" object
					Message[] messages = inbox.getMessages();
					// 10) Iterate through the array of messages
					for( int index = 0; index < 5 && index < messages.length; index++ ) {
						System.out.println("--- Message " + (index + 1) + " ---");
						Message singleMessage = messages[index];
						singleMessage.writeTo(System.out);
					}
					// 11) Close the connection but DON'T remove the messages from the server
					inbox.close(false);
					// 12) Close the store
					currentStore.close();			
				} catch(MessagingException | IOException e) {
					e.printStackTrace();
					System.out.println("Receive mail was not successful");
				}
	}

	public static void main(String[] args) {
//		sendmail();
//		receivemail();
	}

}
