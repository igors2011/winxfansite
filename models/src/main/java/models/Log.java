package models;

import java.sql.Date;

public class Log {
    private int id;

    public Log(Date date, String type, String message) {
        this.date = date;
        this.type = type;
        this.message = message;
    }

    private Date date;
    private String type;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
