package idao.user;

import org.springframework.stereotype.Component;

@Component
public interface ILogAccess {
    void logInfo(String message);
    void logError(String message);
}
