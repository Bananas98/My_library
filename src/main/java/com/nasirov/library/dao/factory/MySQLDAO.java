package com.nasirov.library.dao.factory;

import com.nasirov.library.dao.interfaces.ReaderRoleDAO;
import com.nasirov.library.dao.mysqldao.*;


public class MySQLDAO extends DAOFactory {

    private static volatile MySQLDAO mySQLDAO;

    public static MySQLDAO getInstance() {
        MySQLDAO localInstance = mySQLDAO;
        if (localInstance == null) {
            synchronized (MySQLDAO.class) {
                localInstance = mySQLDAO;
                if (localInstance == null) {
                    mySQLDAO = localInstance = new MySQLDAO();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthorMySQLDAO getAuthorDAO() {
        return AuthorMySQLDAO.getInstance();
    }

    @Override
    public BookMySQLDAO getBookDAO() {
        return BookMySQLDAO.getInstance();
    }

    @Override
    public GenreMySQLDAO getGenreDAO() {
        return GenreMySQLDAO.getInstance();
    }

    @Override
    public ReaderBookMySQLDAO getReaderBookDAO() {
        return ReaderBookMySQLDAO.getInstance();
    }

    @Override
    public ReaderMySQLDAO getReaderDAO() {
        return ReaderMySQLDAO.getInstance();
    }

    @Override
    public RoleMySQLDAO getRoleDAO() {
        return RoleMySQLDAO.getInstance();
    }

    @Override
    public AuthorsBookMySQLDAO getAuthorsBookDAO() {
        return AuthorsBookMySQLDAO.getInstance();
    }

    @Override
    public ReaderRoleDAO getReaderRoleDAO() {
        return ReaderRoleMYSQLDAO.getInstance();
    }
}
