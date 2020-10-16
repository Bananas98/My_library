package com.nasirov.library.dao.interfaces;

import java.util.List;

public interface AuthorsBookDAO {

    /**
     * add authors for book
     * @param authorsId
     * @param bookId
     */
    void create(List<Integer> authorsId, Integer bookId);

    /**
     * delete author for book
     * @param bookId
     */
    void delete(Integer bookId);
}
