package model.dao.jdbc;

import model.dao.DaoConnection;

import java.sql.Connection;

public class JdbcDaoConnection implements DaoConnection {
    public JdbcDaoConnection(Connection connection) {

    }

    @Override
    public void begin() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }
}
