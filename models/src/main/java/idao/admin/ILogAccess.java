package idao.admin;

import models.Log;

import java.sql.Timestamp;
import java.util.List;

public interface ILogAccess {
    List<Log> getAllLogs();
    List<Log> getLogsByType(String type, Timestamp dateFrom, Timestamp dateTo);

}
