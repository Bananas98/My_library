package com.nasirov.library.dao.mysqldao;

import com.nasirov.library.dao.factory.Connector;
import com.nasirov.library.dao.interfaces.AuthorsBookDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AuthorsBookMySQLDAO implements AuthorsBookDAO {

    private final static Logger LOGGER = Logger.getLogger(AuthorsBookMySQLDAO.class);

    private final String ADD_AUTHORS_FOR_BOOK = "INSERT INTO books_authors (author_id,book_id) VALUES (?,?)";
    private final String DELETE_AUTHORS_FOR_BOOK="DELETE FROM books_authors WHERE book_id=?";


    private static volatile AuthorsBookMySQLDAO authorsBookMySQLDAO;

    public static AuthorsBookMySQLDAO getInstance() {
        AuthorsBookMySQLDAO localInstance = authorsBookMySQLDAO;
        if (localInstance == null) {
            synchronized (AuthorsBookMySQLDAO.class) {
                localInstance = authorsBookMySQLDAO;
                if (localInstance == null) {
                    authorsBookMySQLDAO = localInstance = new AuthorsBookMySQLDAO();
                }
            }
        }
        return localInstance;
    }

    private AuthorsBookMySQLDAO(){}


    @Override
    public void create(List<Integer> authorsId,Integer bookId){
        try (Connection connection= Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_AUTHORS_FOR_BOOK)) {
            for(Integer authorId:authorsId){
                preparedStatement.setInt(1,authorId);
                preparedStatement.setInt(2,bookId);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Integer bookId){
        try (Connection connection=Connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHORS_FOR_BOOK)) {
            preparedStatement.setInt(1,bookId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
