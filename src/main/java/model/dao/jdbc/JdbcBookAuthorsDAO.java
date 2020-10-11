package model.dao.jdbc;

import model.dao.BookAuthorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcBookAuthorsDAO implements BookAuthorDAO {

    private Connection connection;

    public JdbcBookAuthorsDAO(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(List<Integer> authorsId, Integer bookId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book_author (author_id,book_id) VALUES (?,?)")){
            for(Integer authorId:authorsId){
                preparedStatement.setInt(1,authorId);
                preparedStatement.setInt(2,bookId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer bookId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book_author WHERE book_id=?")){
            preparedStatement.setInt(1,bookId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
