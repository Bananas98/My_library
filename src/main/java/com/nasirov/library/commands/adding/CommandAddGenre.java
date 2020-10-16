package com.nasirov.library.commands.adding;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.services.SearchService;
import com.nasirov.library.managers.Config;
import com.nasirov.library.managers.Message;
import com.nasirov.library.services.AddingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandAddGenre implements ICommand {
    private static final String NAME = "name";

    private SearchService searchService = SearchService.getInstance();
    private AddingService addingService = AddingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale=(String) request.getSession().getAttribute(LOCALE);
        String genreName = request.getParameter(NAME);
        if (addingService.addNewGenre(genreName) == 0) {
            request.setAttribute("resultForGenre", Message.getInstance(locale).getString(Message.GENRE_ALREADY_EXIST_ERROR));
        } else {
            request.setAttribute("resultForGenre", Message.getInstance(locale).getString(Message.GENRE_ADDED));
        }
        request.getServletContext().setAttribute("genres", searchService.getAllGenres());
        return Config.getInstance().getProperty(Config.ADD_AUTHOR);
    }
}
