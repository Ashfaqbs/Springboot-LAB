package com.test.ashfaq.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.BodyPart;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.servlet.http.Part;

@Service
public class EmailService {

	private final JavaMailSender emailSender;

	public EmailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
//emailService.sendEmail("your_email@example.com", "recipient@example.com", "Subject", "Message body");

//  HTML content

	// String emailContent = "<h2 style='color:red;'>Alert Triggered!</h2>"
//         + "<p>An alert has been triggered due to an exception. Please take necessary action.</p>";
// emailService.sendEmail("your_email@example.com", "recipient@example.com", "Alert Notification", emailContent);

	public void sendEmail(String from, String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from); // Set the "From" address
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);
	}

	@Value("${spring.mail.username}")
	private String from;

	public void receiveMail() {
		// Setup your email server properties
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", "imap");
		properties.setProperty("mail.imap.host", "your_imap_host");
		properties.setProperty("mail.imap.port", "your_imap_port");

		// Connect to the email server
		Session session = Session.getDefaultInstance(properties);
		try {
			Store store = session.getStore();
			store.connect("your_email", "your_password");

			// Open the inbox folder
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			// Fetch messages from the inbox
			Message[] messages = inbox.getMessages();
			for (Message message : messages) {
				// Process each message
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Body: " + message.getContent().toString());

				// You can implement your logic here to handle the email
			}

			// Close the inbox
			inbox.close(true);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//need to set up an IMAP or POP3 client in your Spring Boot application in order to get mails
// in application.prop
// spring.mail.host=imap-mail.outlook.com
// spring.mail.port=993
// spring.mail.username=your-email@outlook.com
// spring.mail.password=your-password
// spring.mail.protocol=imap
// spring.mail.properties.mail.imap.ssl.enable=true

//    --uppdated 

	public void receiveEmails() {
		try {
			Store store = javaMailSender.getSession().getStore("imap");
			store.connect("imap-mail.outlook.com", "your-email@outlook.com", "your-password");
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			Message[] messages = inbox.getMessages();
			for (Message message : messages) {
				System.out.println("Message: " + message.getSubject());
				if (message.isMimeType("multipart/*")) {
					Multipart multipart = (Multipart) message.getContent();
					for (int i = 0; i < multipart.getCount(); i++) {
						BodyPart bodyPart = multipart.getBodyPart(i);
						if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
							String fileName = bodyPart.getFileName();
							InputStream inputStream = bodyPart.getInputStream();
							FileOutputStream outputStream = new FileOutputStream(new File(fileName));
							byte[] buffer = new byte[4096];
							int bytesRead;
							while ((bytesRead = inputStream.read(buffer)) != -1) {
								outputStream.write(buffer, 0, bytesRead);
							}
							outputStream.close();
							inputStream.close();
						}
					}
				}
			}

			inbox.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
