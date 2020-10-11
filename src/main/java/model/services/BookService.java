package model.services;

import model.dao.BookDao;
import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.entity.Book;

import java.util.List;

public class BookService {

    private static Book instance;

    private DaoFactory daoFactory;

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService(DaoFactory.getDaoFactory());
        }
        return instance;
    }

    private BookService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addBook(Book bookDescription) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            BookDao bookDescriptionDao = daoFactory.createBookDao(connection);
            bookDescriptionDao.create(bookDescription);
            connection.commit();
        }
    }

    public List<Book> getBook(int pageNumber){
        int limit = DaoFactory.LIMIT;
        int offset = (pageNumber - 1) * limit;
        List<Book> books;

        try (BookDao bookDescriptionDao = daoFactory.createBookDao()) {
            books = bookDescriptionDao.getBook(limit, offset);
        }

        AuthorService as = AuthorService.getInstance();
        for (Book book: books) {
            book.setAuthors(as.getByBook(book));
        }
        return books;
    }

    public List<Book> getBookByTitle(String title) {
        List<Book> books;

        try (BookDao bookDescriptionDao = daoFactory.createBookDao()) {
            books = bookDescriptionDao.getByTitle(title);
        }

        AuthorService authorService = AuthorService.getInstance();
        for (Book book: books) {
            authorService.getAuthorsByBookId(book.getId());
        }
        return books;
    }
}
