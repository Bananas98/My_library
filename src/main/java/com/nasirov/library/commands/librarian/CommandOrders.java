package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandOrders implements ICommand {
    private LibrarianService librarianService = LibrarianService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("readerBooks", librarianService.getBookOrders());
        return Config.getInstance().getProperty(Config.ORDERS);
    }
}
