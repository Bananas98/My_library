package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandGetBook implements ICommand {
    private AdminService adminService=AdminService.getInstance();
    private final static String BOOK_ID="id";
    private final static String DAYS="days";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id= Integer.valueOf(request.getParameter(BOOK_ID));
        Integer days=Integer.valueOf(request.getParameter(DAYS));
        adminService.getBookToReader(id,days);
        request.setAttribute("readerBooks",adminService.getBookOrders());
        return Config.getInstance().getProperty(Config.ORDERS);
    }
}
