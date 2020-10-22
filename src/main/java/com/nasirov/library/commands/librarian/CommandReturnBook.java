package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.models.Book;
import com.nasirov.library.models.Reader;
import com.nasirov.library.models.ReaderBook;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandReturnBook implements ICommand {

    private LibrarianService librarianService = LibrarianService.getInstance();

    private final static String READER_ID="id";
    private final static String REQUEST_PAGE="page";
    private final static String READER_INFO_PAGE="readerInfo";
    private final static String BOOK_LENDERS_PAGE="bookLenders";
    private final static String READING_ROOM_PAGE="readingRoom";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        Integer id= Integer.valueOf(request.getParameter(READER_ID));
        librarianService.readerReturnBook(id);
        String queryFrom=request.getParameter(REQUEST_PAGE);
        List<ReaderBook> readerBooks;
        if(queryFrom.equals(READER_INFO_PAGE)) {
            Reader reader = (Reader) request.getSession().getAttribute("reader");
            readerBooks = librarianService.getBooksOfReaderForAdmin(reader.getId());
            page= Config.getInstance().getProperty(Config.READER_INFO);
        }else if(queryFrom.equals(BOOK_LENDERS_PAGE)) {
            Book book=(Book)request.getSession().getAttribute("book");
            readerBooks = librarianService.getReadersForBook(book.getId(),0);
            request.getSession().setAttribute("pageCount", librarianService.pageCount(book.getId()));
            page= Config.getInstance().getProperty(Config.BOOK_LENDERS);
        }else if(queryFrom.equals(READING_ROOM_PAGE)) {
            readerBooks = librarianService.getBooksFromReadingRoom();
            page= Config.getInstance().getProperty(Config.READING_ROOM);
        }else {
            readerBooks = librarianService.getBookOrders();
            page= Config.getInstance().getProperty(Config.ORDERS);
        }
        request.setAttribute("readerBooks", readerBooks);
        return page;
    }
}
