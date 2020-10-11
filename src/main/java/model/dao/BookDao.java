package model.dao;

import model.entity.Book;

import java.util.List;

public interface BookDao extends GenericDAO<Book>{

    List<Book> getBook(int limit, int offset);

    List<Book> findByCategories(int categoriesId);

    List<Book> getByName(String name);

    void setBookAmount(int bookId, int amount);

    List<Book> findAllBooksOfAuthorByName(String  authorName);

    List<Book> findAllBooks();



}
