package com.nasirov.library.services;

import com.nasirov.library.dao.factory.DAOFactory;
import com.nasirov.library.models.Author;
import com.nasirov.library.models.Book;
import com.nasirov.library.models.Genre;

import java.util.List;


public class SearchService {
    private DAOFactory mySQLDAO = DAOFactory.getDAOFactory();
    ;
    private static volatile SearchService searchService;


    private SearchService() {

    }

    public static SearchService getInstance() {
        SearchService localInstance = searchService;
        if (localInstance == null) {
            synchronized (SearchService.class) {
                localInstance = searchService;
                if (localInstance == null) {
                    searchService = localInstance = new SearchService();
                }
            }
        }
        return localInstance;
    }

    /**
     * @return all books
     */
    public List<Book> getAllBooks() {
        return mySQLDAO.getBookDAO().findAll();
    }

    /**
     *
     * @param name
     * @return books which names start with parameter
     */
    public List<Book> getBooksByName(String name) {
        return mySQLDAO.getBookDAO().findByName(name);
    }

    /**
     *
     * @param genreId
     * @return book by genre
     */
    public List<Book> getBooksByGenre(Integer genreId) {
        return mySQLDAO.getBookDAO().findByGenre(genreId);
    }

    /**
     *
     * @param authorName
     * @return all books where author name start with param
     */
    public List<Book> getAllBookOfAuthor(String authorName) {
        return mySQLDAO.getBookDAO().findAllBooksOfAuthorByName(authorName);
    }

    /**
     *
     * @return all genres
     */
    public List<Genre> getAllGenres() {
        return mySQLDAO.getGenreDAO().findAll();
    }

    /**
     *
     * @return all authors
     */
    public List<Author> getAllAuthors() {
        return mySQLDAO.getAuthorDAO().findAll();
    }

    /**
     *
     * @param authorId
     * @return all books of author
     */
    public List<Book> getAllBooksOfAuthor(Integer authorId) {
        return mySQLDAO.getBookDAO().findAllBooksOfAuthorById(authorId);
    }

    /**
     *
     * @param name
     * @return authors which name start with parameter
     */
    public List<Author> getAuthorsByName(String name) {
        return mySQLDAO.getAuthorDAO().findByName(name);
    }

    /**
     *
     * @param bookId
     * @return book by id
     */
    public Book getBooksById(Integer bookId) {
        return mySQLDAO.getBookDAO().findById(bookId);
    }
}


