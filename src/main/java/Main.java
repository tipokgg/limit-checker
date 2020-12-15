import javax.mail.MessagingException;

public class Main {


    public static void main(String[] args) throws MessagingException {

        MailSender mailSender = new MailSender();
        mailSender.sendMail("dk@mk-net.ru");

    }

}
