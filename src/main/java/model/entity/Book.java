package model.entity;

import java.util.Date;

public class Book {

    private int id;
    private String name;
    private double delay_penalty;
    private Date date_of_release;
    private String categories;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDelay_penalty() {
        return delay_penalty;
    }

    public void setDelay_penalty(double delay_penalty) {
        this.delay_penalty = delay_penalty;
    }

    public Date getDate_of_release() {
        return date_of_release;
    }

    public void setDate_of_release(Date date_of_release) {
        this.date_of_release = date_of_release;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
