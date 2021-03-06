package com.nasirov.library.services;

import com.nasirov.library.dao.factory.DAOFactory;


import java.util.ArrayList;
import java.util.List;


public class AddingService {

    private DAOFactory mySQLDAO = DAOFactory.getDAOFactory();

    private static volatile AddingService addingService;

    private AddingService() {

    }

    public static AddingService getInstance() {
        AddingService localInstance = addingService;
        if (localInstance == null) {
            synchronized (AddingService.class) {
                localInstance = addingService;
                if (localInstance == null) {
                    addingService = localInstance = new AddingService();
                }
            }
        }
        return localInstance;
    }

    /**
     * create new book in db
     * @param name name of book
     * @param amount amount of book
     * @param genreId genre of book
     * @param authorIdsString authors of book
     */
    public void addNewBook(String name, Integer amount, Integer genreId, List<String> authorIdsString) {
        List<Integer> authorIds = new ArrayList<>(authorIdsString.size());
        for (String authorId : authorIdsString) {
            authorIds.add(Integer.valueOf(authorId));
        }
        Integer bookId = mySQLDAO.getBookDAO().create(name, genreId, amount);
        mySQLDAO.getAuthorsBookDAO().create(authorIds, bookId);
    }

    /**
     *
     * @param authorName
     * @return is Author added
     */
    public Integer addNewAuthor(String authorName) {
        return mySQLDAO.getAuthorDAO().create(authorName);
    }

    /**
     *
     * @param genreName
     * @return is genre added
     */
    public Integer addNewGenre(String genreName) {
        return mySQLDAO.getGenreDAO().create(genreName);
    }
}
