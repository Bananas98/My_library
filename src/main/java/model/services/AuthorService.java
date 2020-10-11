package model.services;

import model.dao.AuthorDao;
import model.dao.DaoFactory;
import model.entity.Author;

import java.util.List;

public class AuthorService {
    private static AuthorService instance;

    private DaoFactory daoFactory;

    public static AuthorService getInstance() {
        if (instance == null) {
            instance = new AuthorService(DaoFactory.getDaoFactory());
        }
        return instance;
    }

    private AuthorService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void createAuthor(Author author) {
        try (AuthorDao authorDao = daoFactory.createAuthorDao()) {
            authorDao.create(author);
        }
    }

    public List<Author> getAuthorBySurnameBeginning(String surnameBeginning) {
        List<Author> authors;
        try (AuthorDao authorDao = daoFactory.createAuthorDao()) {
            authors = authorDao.getBySurnameBeginning(surnameBeginning);
        }
        return authors;
    }

    public List<Author> getAuthorsByBookDescriptionId(Long bookId) {
        List<Author> authors;
        try (AuthorDao authorDao = daoFactory.createAuthorDao()){
            authors = authorDao.getByBookDescriptionId(bookId);
        }
        return authors;
    }

    public List<Author> getByBookDescription(BookDescription bookDescription) {
        return getAuthorsByBookDescriptionId(bookDescription.getId());
    }
}
