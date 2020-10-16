package com.nasirov.library.commands.readers;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandMainMenu implements ICommand {


    private SearchService searchService =  SearchService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("books", searchService.getAllBooks());
        return Config.getInstance().getProperty(Config.MAIN);
    }
}
