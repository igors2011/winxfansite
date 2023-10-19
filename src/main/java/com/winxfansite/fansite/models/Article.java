package com.winxfansite.fansite.models;

public class Article
{
    private int id;
    private String header;
    private String shortDescr;
    private String longDescr;
    private String articleType;
    private String author;

    public Article(int id, String header, String shortDescr, String longDescr, String articleType, String author) {
        this.id = id;
        this.header = header;
        this.shortDescr = shortDescr;
        this.longDescr = longDescr;
        this.articleType = articleType;
        this.author = author;
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

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
