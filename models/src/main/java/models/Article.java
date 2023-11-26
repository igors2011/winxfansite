package models;

public class Article
{
    private int id;
    private String header;
    private String shortDescr;
    private String longDescr;
    private String type;
    private String author;
    private String URL;

    public Article(int id, String header, String shortDescr, String longDescr, String type, String author) {
        this.id = id;
        this.header = header;
        this.shortDescr = shortDescr;
        this.longDescr = longDescr;
        this.type = type;
        this.author = author;
        this.URL = type + "/" + header;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public void setShortDescr(String shortDescr) {
        this.shortDescr = shortDescr;
    }

    public String getLongDescr() {
        return longDescr;
    }

    public void setLongDescr(String longDescr) {
        this.longDescr = longDescr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getURL() {
        return URL;
    }
}
