package com.nasirov.library.commands.admin;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.services.SearchService;
import com.nasirov.library.managers.Config;
import com.nasirov.library.managers.Message;
import com.nasirov.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteBook implements ICommand {
    private AdminService adminService=AdminService.getInstance();
    private SearchService searchService=SearchService.getInstance();
    private static final String BOOK_ID="bookId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId=Integer.valueOf(request.getParameter(BOOK_ID));
        String locale=(String) request.getSession().getAttribute(LOCALE);
        if(adminService.isBookOrdered(bookId)){
            request.setAttribute("message",Message.getInstance(locale).getString(Message.CANT_DELETE_BOOK));
        }else {
            adminService.deleteBook(bookId);
            request.getSession().setAttribute("books", searchService.getAllBooks());
            request.setAttribute("message", Message.getInstance(locale).getString(Message.BOOK_DELETED));
        }
        return Config.getInstance().getProperty(Config.MAIN);
    }
}