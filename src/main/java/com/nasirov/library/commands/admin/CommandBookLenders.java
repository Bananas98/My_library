package com.nasirov.library.commands.admin;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.models.Book;
import com.nasirov.library.models.ReaderBook;
import com.nasirov.library.services.SearchService;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


public class CommandBookLenders implements ICommand {


    private SearchService searchService =  SearchService.getInstance();
    private AdminService adminService=AdminService.getInstance();
    private final static String READER_ID="id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter(READER_ID));
        Book book= searchService.getBooksById(id);
        request.getSession().setAttribute("book",book);
        List<ReaderBook>readerBooks= adminService.getReadersForBook(id,1);
        request.setAttribute("readerBooks",readerBooks);
        request.getSession().setAttribute("pageCount",adminService.pageCount(id));
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("currentDate", timestamp);
        return Config.getInstance().getProperty(Config.BOOK_LENDERS);
    }
}
