package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {

    private Integer id;
    private String name;
    private Categories categories;
    private Integer amount;
    private Author author;

    public Book() {

    }

    public Book(String name, Categories categories, Integer id, Integer amount) {
        this.name = name;
        this.categories = categories;
        this.id = id;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getGenre() {
        return categories;
    }

    public void setGenre(Categories categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthors(Author  author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(name, book.name)) return false;
        if (!Objects.equals(categories, book.categories)) return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
