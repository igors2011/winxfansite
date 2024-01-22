package idao.admin;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface IMailAccess {
    void sendEmail(List<String> to, String subject, String body) throws MessagingException, IOException;
}
