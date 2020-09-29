package model.entity;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private Long id;
    private Book book;
    private User user;
    private Date returnDate;
    private Date lendDate;

    public Order(Long id, Book book, User user, Date returnDate, Date lendDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.returnDate = returnDate;
        this.lendDate = lendDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) &&
                Objects.equals(getBook(), order.getBook()) &&
                Objects.equals(getUser(), order.getUser()) &&
                Objects.equals(getReturnDate(), order.getReturnDate()) &&
                Objects.equals(getLendDate(), order.getLendDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBook(), getUser(), getReturnDate(), getLendDate());
    }
}
