package com.nasirov.library.commands.search;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.services.SearchService;
import com.nasirov.library.managers.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandBookSearch implements ICommand {

    private static final String SEARCH_TEXT = "search";
    private static final String SEARCH_CRITERIA = "selected";
    private static final String SEARCH_BY_BOOK_NAME = "bookName";

    private SearchService searchService =  SearchService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String criteria = request.getParameter(SEARCH_CRITERIA);
        if(criteria!=null) {
            String text = request.getParameter(SEARCH_TEXT);
            request.setAttribute("searchText",text);
            if (criteria.equals(SEARCH_BY_BOOK_NAME))
                request.getSession().setAttribute("books", searchService.getBooksByName(text));
            else
                request.getSession().setAttribute("books", searchService.getAllBookOfAuthor(text));
        }else {
            int genreId= Integer.parseInt(request.getParameter("id"));
            request.getSession().setAttribute("books", searchService.getBooksByGenre(genreId));
        }

        return Config.getInstance().getProperty(Config.MAIN);
    }
}
