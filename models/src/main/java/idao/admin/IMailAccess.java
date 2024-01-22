package idao.admin;

import org.springframework.stereotype.Component;

import javax.mail.*;
import java.io.IOException;
import java.util.List;
@Component
public interface IMailAccess {
    void sendEmail(List<String> to, String subject, String body) throws MessagingException, IOException;
}
