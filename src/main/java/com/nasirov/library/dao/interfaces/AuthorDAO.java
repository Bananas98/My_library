package com.nasirov.library.dao.interfaces;

import com.nasirov.library.models.Author;
import com.nasirov.library.models.Book;

import java.util.List;

public interface AuthorDAO{
    /**
     *
     * @param name
     * @return authors which name start with parameter
     */
    List<Author> findByName(String name);

    /**
     *
     * @param id
     * @return author by id
     */
    Author findById(int id);

    /**
     *
     * @return all authors
     */
    List<Author> findAll();

    /**
     *
     * @param authorName
     * @return is author created
     */
    Integer create(String authorName);

    /**
     *
     * @param book
     * @return all authors for book
     */
    List<Author> findBookAuthors(Book book);

}
