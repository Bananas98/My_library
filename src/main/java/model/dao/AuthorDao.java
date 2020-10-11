package model.dao;

import model.entity.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getByName(String namePart);

    List<Author> getByBookId(int bookId);
}
