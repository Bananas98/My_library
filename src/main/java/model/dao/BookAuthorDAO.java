package model.dao;

import java.util.List;

public interface BookAuthorDAO {
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
