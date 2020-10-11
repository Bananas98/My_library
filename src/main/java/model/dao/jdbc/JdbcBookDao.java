package model.dao.jdbc;

import exception.DatabaseException;
import model.dao.BookDao;

import model.entity.Author;
import model.entity.Book;
import model.entity.Categories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookDao implements BookDao {

    private final String SELECT_BOOKS = "SELECT c.name,c.id,b.name,b.id,b.delay_penalty,b.amount,date_of_release, a.name,a.id FROM books b JOIN categories c ON b.id_categories = c.id JOIN book_author ba ON ba.book_id=b.id JOIN authors a ON a.id=ba.author_id ";
    private final String SELECT_BOOK_OF_AUTHOR_BY_NAME = SELECT_BOOKS+"WHERE b.id in (SELECT book_id FROM book_author WHERE author_id in (SELECT id FROM authors WHERE name like ?)) ";
    //private final String SELECT_BOOK_OF_AUTHOR_BY_ID = SELECT_BOOKS+"WHERE b.id in (SELECT book_id FROM books_authors WHERE author_id=?) ";

    private Connection connection;

    public JdbcBookDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Book book) {
        String createQuery = "INSERT INTO books(name, delay_penalty, amount, id_categories, id_author,date_of_release) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement query = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, book.getName());
            query.setDouble(2, book.getDelayPenalty());
            query.setInt(3, book.getAmount());
            query.setInt(4, book.getCategories().getId());
            query.setInt(5, book.getAuthor().getId());
            query.setDate(6,book.getDateOfRelease());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                book.setId(keys.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Book book) {
        String updateQuery = "UPDATE books SET name = ?, delay_penalty = ?, amount = ?, id_categories = ?, "
                + "id_author = ?, date_of_release = ? WHERE  id = ?";
        try (PreparedStatement query = connection.prepareStatement(updateQuery)) {
            query.setString(1, book.getName());
            query.setDouble(2, book.getDelayPenalty());
            query.setInt(3, book.getAmount());
            query.setInt(4, book.getCategories().getId());
            query.setInt(5, book.getAuthor().getId());
            query.setDate(6,book.getDateOfRelease());
            query.setInt(7, book.getId());
            query.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(Book book) {
        String deleteQuery = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement query = connection.prepareStatement(deleteQuery)) {
            query.setLong(1, book.getId());
            query.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Book> getById(int bookId) {
        String getByIdQuery = "SELECT * FROM books WHERE id = ?";
        Optional<Book> book = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(getByIdQuery)){
            query.setLong(1, bookId);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                book = Optional.of(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
        return book;
    }

    @Override
    public List<Book> getBook(int limit, int offset) {
        //SQL_CALC_FOUND_ROWS
        String getBooksPaginationQuery = "SELECT * FROM books LIMIT ? OFFSET ?";
        List<Book> bookDescriptions = new ArrayList<>(limit);
        try (PreparedStatement query = connection.prepareStatement(getBooksPaginationQuery)) {
            query.setInt(1, limit);
            query.setInt(2, offset);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                bookDescriptions.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
        return bookDescriptions;
    }


    @Override
    public List<Book> getByName(String name) {
        String getByTitleQuery = "SELECT * FROM books WHERE name like ?";
        List<Book> books = new ArrayList<>();
        name = "%" + name + "%";
        try (PreparedStatement query = connection.prepareStatement(getByTitleQuery)) {
            query.setString(1, name);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                books.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
        return books;
    }

    @Override
    public List<Book> findAllBooks() {
        String getAllBooks = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(getAllBooks)) {
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                books.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
        return books;
    }


    @Override
    public List<Book> findByCategories(int genreId) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKS + "WHERE id_categories=?")) {
            preparedStatement.setInt(1, genreId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    books.add(parseResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void setBookAmount(int bookId, int amount) {
        try(PreparedStatement preparedStatement=connection.prepareStatement("UPDATE books SET amount=? WHERE id=?")){
            preparedStatement.setInt(1,amount);
            preparedStatement.setInt(2,bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> findAllBooksOfAuthorByName(String authorName) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_OF_AUTHOR_BY_NAME)){
            preparedStatement.setObject(1,authorName);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    books.add(parseResultSet(resultSet));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    protected static Book parseResultSet(ResultSet resultSet) throws SQLException {
        return new Book.Builder().setId(resultSet.getInt("books.id"))
                .setName(resultSet.getString("name"))
                .setDelayPenalty(resultSet.getDouble("delay_penalty"))
                .setAmount(resultSet.getInt("amount"))
                .setCategories(new Categories.Builder()
                        .setId(resultSet.getInt("categories.id"))
                        .setName(resultSet.getString("categories.name"))
                        .build())
                .setAuthor(new Author.Builder()
                        .setId(resultSet.getInt("authors.id"))
                        .setName(resultSet.getString("authors.name"))
                        .build())
                .setDateOfRelease(resultSet.getDate("date_of_release"))
                .build();
    }
        


    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
