package com.nasirov.library.dao.factory;

import com.nasirov.library.dao.interfaces.*;

public abstract class DAOFactory {


    /**
     * @return a DAO depending on its type
     */
    public static DAOFactory getDAOFactory() {
        return new MySQLDAO();
    }


    public abstract AuthorDAO getAuthorDAO();

    public abstract BookDAO getBookDAO();

    public abstract GenreDAO getGenreDAO();

    public abstract ReaderBookDAO getReaderBookDAO();

    public abstract ReaderDAO getReaderDAO();

    public abstract RoleDAO getRoleDAO();

    public abstract AuthorsBookDAO getAuthorsBookDAO();

    public abstract ReaderRoleDAO getReaderRoleDAO();

}
