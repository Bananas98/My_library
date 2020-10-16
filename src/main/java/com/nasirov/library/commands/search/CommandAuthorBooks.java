package com.nasirov.library.commands.search;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.models.Book;
import com.nasirov.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandAuthorBooks implements ICommand {


    private SearchService searchService =  SearchService.getInstance();
    private static final String ID = "id";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter(ID));
        List<Book>books = searchService.getAllBooksOfAuthor(id);
        request.setAttribute("books",books);
        String page= Config.getInstance().getProperty(Config.MAIN);
        return page;
    }
}
