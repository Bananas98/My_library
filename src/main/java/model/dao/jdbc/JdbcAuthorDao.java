package model.dao.jdbc;

import exception.DatabaseException;
import model.dao.AuthorDao;
import model.entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class JdbcAuthorDao implements AuthorDao {
    private Connection connection;

    public JdbcAuthorDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void create(Author author) {
        String createQuery = "INSERT INTO authors (name) VALUES (?)";
        try (PreparedStatement query = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, author.getName());
            query.executeUpdate();
            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                author.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
           e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    public void update(Author author) {
        String updateQuery = "UPDATE authors SET name=? WHERE id=?";
        try (PreparedStatement query = connection.prepareStatement(updateQuery)) {
            query.setString(1, author.getName());
            query.setLong(2, author.getId());
            query.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    public void delete(Author author) {
        String deleteAuthorQuery = "DELETE FROM authors WHERE id=?";
        try (PreparedStatement query = connection.prepareStatement(deleteAuthorQuery)) {
            query.setLong(1, author.getId());
            query.executeUpdate();
        } catch (SQLException e) {
           e.printStackTrace();
            throw new DatabaseException(e);
        }
    }

    public Optional<Author> getById(Long id) {
        String getByIdQuery = "SELECT id, name FROM authors WHERE id=?";
        Optional<Author> author = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(getByIdQuery)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                author = Optional.of(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(e);
        }
        return author;
    }

    public List<Author> getByName(String surnamePart) {
        String getBySurnamePartQuery = "SELECT id, name FROM authors "
                + "WHERE name like ? ORDER BY name";
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(getBySurnamePartQuery)) {
            query.setString(1, surnamePart + "%");
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                authors.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return authors;
    }

    public List<Author> getByBookId(int bookId) {
        String getByBookIdQuery = "SELECT authors.id, name FROM authors "
                + "JOIN book_author ON book_author.author_id = authors.id "
                + "WHERE book_author.book_id = ?";

        List<Author> authors = new LinkedList<>();
        try (PreparedStatement query = connection.prepareStatement(getByBookIdQuery)) {
            query.setLong(1, bookId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                authors.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
           e.printStackTrace();
            throw new DatabaseException(e);
        }
        return authors;
    }

    protected static Author parseResultSet(ResultSet resultSet) throws SQLException {
        return new Author.Builder().setId(resultSet.getInt("author.id"))
                .setName(resultSet.getString("name"))
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
