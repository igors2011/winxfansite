package idao.admin;

import models.Log;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
@Component
public interface ILogAccess {
    List<Log> getAllLogs();
    List<Log> getLogsByType(String type, Timestamp dateFrom, Timestamp dateTo);
    void logInfo(String message);
    void logError(String message);
}
