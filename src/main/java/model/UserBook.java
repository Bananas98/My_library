package model;
import java.util.Date;
import java.util.Objects;

public class UserBook {

    private int id;
    private User user;
    private Book book;
    private Date lendDate;
    private Date returnDate;

    public UserBook(int id,User user, Book book, Date lendDate, Date returnDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public UserBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book= book;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBook that = (UserBook) o;

        if (!Objects.equals(user, that.user)) return false;
        if (!Objects.equals(book, that.book)) return false;
        if (!Objects.equals(lendDate, that.lendDate)) return false;
        return Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (lendDate != null ? lendDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

}
