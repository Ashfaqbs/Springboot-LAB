import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
}



