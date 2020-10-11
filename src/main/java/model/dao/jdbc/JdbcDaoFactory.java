package model.dao.jdbc;

import exception.DatabaseException;
import model.dao.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/library_db");

        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public UserDao createUserDao() {
        try {
            return new JdbcUserDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    @Override
    public UserDao createUserDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcUserDao(sqlConnection);
    }

    public BookDao createBookDao() {
        try {
            return new JdbcBookDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public BookDao createBookDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcBookDao(sqlConnection);
    }

    public AuthorDao createAuthorDao() {
        try {
            return new JdbcAuthorDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public AuthorDao createAuthorDao(DaoConnection connection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcAuthorDao(sqlConnection);
    }


}
