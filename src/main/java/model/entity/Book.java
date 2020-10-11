package model.entity;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Book {

    private int id;
    private String name;
    private double delayPenalty;
    private int amount;
    private Categories categories;
    private Author author;
    private Date dateOfRelease;

    public Book(int id, String name, double delayPenalty, int amount, Categories categories, List<Author> authors, Date dateOfRelease) {
    }

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

    public double getDelayPenalty() {
        return delayPenalty;
    }

    public void setDelayPenalty(double delayPenalty) {
        this.delayPenalty = delayPenalty;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author authors) {
        this.author = authors;
    }

    public static class Builder implements IBuilder<Book> {
        private int id;
        private String name;
        private double delayPenalty;
        private int amount;
        private Categories categories;
        private Author author;
        private List<Author> authors;
        private Date dateOfRelease;


        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDelayPenalty(double delayPenalty) {
            this.delayPenalty = delayPenalty;
            return this;
        }


        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }


        public Builder setCategories(Categories categories){
            this.categories = categories;
            return this;
        }

        public Builder setAuthor(Author author) {
            this.author = author;
            return this;
        }

        public Builder setAuthors(List<Author> authors) {
            this.authors = authors;
            return this;
        }

        public Builder setDateOfRelease(Date dateOfRelease) {
            this.dateOfRelease = dateOfRelease;
            return this;
        }


        public Book build() {
            if (authors == null) {
                authors = new LinkedList<>();
            }
            return new Book (id, name, delayPenalty,amount, categories ,authors, dateOfRelease);
        }
    }
}
