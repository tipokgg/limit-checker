import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {


    public void sendMail(String recepient) throws MessagingException {
        System.out.println("Preparing to send!");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccMail = "antiv18@gmail.com";
        String password = "kG_400wDArambler12";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccMail, password);
            }
        });

        Message message = prepareMessage(session, myAccMail, recepient);

        Transport.send(message);

        System.out.println("Message sent successfully!");


    }

    public Message prepareMessage(Session session, String myAccMail, String recepient) {

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(myAccMail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Just first e-mail from Java");
            message.setText("Hello gui");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }

}
