package model.dao.jdbc;

import exception.DatabaseException;
import model.dao.DaoConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoConnection implements DaoConnection {

    private Connection connection;
    private boolean inTransaction = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
