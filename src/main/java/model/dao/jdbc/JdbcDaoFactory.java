package model.dao.jdbc;

import exception.DatabaseException;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/library_5");

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


}
