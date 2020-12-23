import javax.mail.MessagingException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws MessagingException {

        MailSender mailSender = new MailSender();
        List<String> list = SQLConnector.getCheckedList();

        if (list.size() > 0) {
            mailSender.sendMail("help@mk-net.ru", getFormattedMessage(list));
        } else {
            mailSender.sendMail("dk@mk-net.ru", "Скрипт отработал. Договоров с бессрочным лимитом не обнаружено");
        }
    }

    private static String getFormattedMessage(List<String> list) {
        StringBuilder sb = new StringBuilder("Внимание! Обнаружены договора с бессрочным лимитом: \n\n");

        for (String s : list) {
            sb.append(s).append(" \n");
        }

        return sb.toString();
    }
}