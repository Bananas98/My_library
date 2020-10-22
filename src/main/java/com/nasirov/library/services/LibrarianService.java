package com.nasirov.library.services;

import com.nasirov.library.dao.factory.DAOFactory;
import com.nasirov.library.models.Reader;
import com.nasirov.library.models.ReaderBook;


import java.util.List;


public class LibrarianService {
    private DAOFactory mySQLDAO= DAOFactory.getDAOFactory();
    private static volatile LibrarianService librarianService;


    private LibrarianService(){

    }

    public static LibrarianService getInstance() {
        LibrarianService localInstance = librarianService;
        if (localInstance == null) {
            synchronized (LibrarianService.class) {
                localInstance = librarianService;
                if (localInstance == null) {
                    librarianService = localInstance = new LibrarianService();
                }
            }
        }
        return localInstance;
    }

    /**
     * find all readers
     * @return all readers
     */
    public List<Reader>getInfoAboutAllReaders(){
        return mySQLDAO.getReaderDAO().findAll();
    }

    /**
     *
     * @param name
     * @return reader where name start with param
     */
    public List<Reader>getReadersByName(String name){
        return mySQLDAO.getReaderDAO().findByName(name);
    }

    /**
     *
     * @param email
     * @return reader where name start with param
     */
    public List<Reader>getReadersByEmail(String email){
        return mySQLDAO.getReaderDAO().findByEmail(email);
    }

    /**
     * @param readerId
     * @return all books which reader take
     */

    public List<ReaderBook>getBooksOfReaderForAdmin(Integer readerId){return mySQLDAO.getReaderBookDAO().findReaderBooksForAdmins(readerId);}

    /**
     * @param readerBookId
     */
    public void readerReturnBook(Integer readerBookId) {
        mySQLDAO.getReaderBookDAO().readerReturnBook(readerBookId);
    }

    /**
     *
     * @param readerId
     * @return reader by id
     */
    public Reader getReaderById(Integer readerId){
        return mySQLDAO.getReaderDAO().findById(readerId);
    }


    /**
     *
     * @param bookId
     * @return all readers who take book
     */
    public List<ReaderBook>getReadersForBook(Integer bookId,Integer fromBook){
        return mySQLDAO.getReaderBookDAO().findReadersForBook(bookId,fromBook);
    }

    /**
     *
     * @return all orders on books
     */
    public List<ReaderBook> getBookOrders(){
        return mySQLDAO.getReaderBookDAO().findBookOrders();
    }


    /**
     *get book to reader with readerId on n days
     * @param readerId
     * @param days
     */
    public void getBookToReader(Integer readerId, Integer days){
        mySQLDAO.getReaderBookDAO().getBookToReader(readerId,days);
    }

    /**
     *
     * @return all books in reading room
     */
    public List<ReaderBook> getBooksFromReadingRoom() {
        return mySQLDAO.getReaderBookDAO().findBooksForReadingRoom();
    }

    /**
     * delete all orders on book, all book authors, and book
     * @param bookId
     */
    public void deleteBook(Integer bookId){
        mySQLDAO.getReaderBookDAO().deleteForBook(bookId);
        mySQLDAO.getAuthorsBookDAO().delete(bookId);
        mySQLDAO.getBookDAO().delete(bookId);
    }

    public void deleteUser(Integer userId){
        mySQLDAO.getReaderBookDAO().deleteForBook(userId);
        mySQLDAO.getAuthorsBookDAO().delete(userId);
        mySQLDAO.getBookDAO().delete(userId);
    }

    /**
     *
     * @param bookId
     * @return is book ordered
     */
    public Boolean isBookOrdered(Integer bookId){
        return mySQLDAO.getReaderBookDAO().isBookOrdered(bookId);
    }

    /**
     *
     * @param bookId
     * @param amount new book amount
     */
    public void setBookAmount(Integer bookId, Integer amount) {
        mySQLDAO.getBookDAO().setBookAmount(bookId,amount);
    }

    /**
     *
     * @param bookId
     * @return count of pages
     */
    public Integer pageCount(Integer bookId){
        return mySQLDAO.getReaderBookDAO().getPageCount(bookId);
    }

}
