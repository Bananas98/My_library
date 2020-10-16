package com.nasirov.library.dao.interfaces;


import com.nasirov.library.models.Genre;

import java.util.List;

public interface GenreDAO  {
    /**
     *
     * @param id
     * @return genre where id eq param
     */
    Genre findById(int id);

    /**
     *
     * @return all genres
     */
    List<Genre> findAll();

    /**
     *
     * @param genreName name for new genre
     * @return is genre created
     */
    Integer create(String genreName);
}
