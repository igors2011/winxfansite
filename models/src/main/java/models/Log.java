package models;

import java.sql.Timestamp;

public class Log {
    private int id;

    public Log(Timestamp date, String type, String message) {
        this.date = date;
        this.type = type;
        this.message = message;
    }

    private Timestamp date;
    private String type;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getDateDescr() {
        return "" + getDate();
    }
}
