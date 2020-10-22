package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.services.SearchService;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandSetBookAmount implements ICommand {
    private AdminService adminService = AdminService.getInstance();
    private SearchService searchService = SearchService.getInstance();

    private final static String BOOK_ID="bookId";
    private final static String NEW_BOOK_AMOUNT="amount";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.valueOf(request.getParameter(BOOK_ID));
        Integer amount=Integer.valueOf(request.getParameter(NEW_BOOK_AMOUNT));
        adminService.setBookAmount(bookId,amount);
        request.getSession().setAttribute("books", searchService.getAllBooks());
        return Config.getInstance().getProperty(Config.MAIN);
    }
}
