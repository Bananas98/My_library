package com.nasirov.library.commands.readers;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.models.Book;
import com.nasirov.library.services.LibrarianService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandSetPage implements ICommand {
    private LibrarianService librarianService = LibrarianService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer page= Integer.valueOf(request.getParameter("page"));
        Integer fromBook= (page-1)*5;
        Book book= (Book) request.getSession().getAttribute("book");
        request.getSession().setAttribute("readerBooks", librarianService.getReadersForBook(book.getId(),fromBook));
        return Config.getInstance().getProperty(Config.BOOK_LENDERS);
    }
}
