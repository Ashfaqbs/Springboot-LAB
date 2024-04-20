import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
//emailService.sendEmail("your_email@example.com", "recipient@example.com", "Subject", "Message body");

//  HTML content
  
  //String emailContent = "<h2 style='color:red;'>Alert Triggered!</h2>"
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
}



