import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {


    public void sendMail(String recepient, String textMessage) throws MessagingException {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String login = SecretsService.getProperty("MAIL_USER");
        String password = SecretsService.getProperty("MAIL_PASS");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        Message message = prepareMessage(session, login, recepient, textMessage);

        Transport.send(message);


    }

    private Message prepareMessage(Session session, String myAccMail, String recepient, String textMessage) {

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(myAccMail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Оповещение о бессрочном лимите");
            message.setText(textMessage);
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
