package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandGetBook implements ICommand {
    private LibrarianService librarianService = LibrarianService.getInstance();
    private final static String BOOK_ID="id";
    private final static String DAYS="days";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id= Integer.valueOf(request.getParameter(BOOK_ID));
        Integer days=Integer.valueOf(request.getParameter(DAYS));
        librarianService.getBookToReader(id,days);
        request.setAttribute("readerBooks", librarianService.getBookOrders());
        return Config.getInstance().getProperty(Config.ORDERS);
    }
}
