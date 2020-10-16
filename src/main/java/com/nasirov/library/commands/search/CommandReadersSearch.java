package com.nasirov.library.commands.search;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.AdminService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandReadersSearch implements ICommand {

    private static final String SEARCH_TEXT = "search";
    private static final String SEARCH_CRITERIA = "selected";
    private static final String SEARCH_BY_READER_NAME = "Reader name";
    private AdminService adminService=AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String text = request.getParameter(SEARCH_TEXT);
        String criteria = request.getParameter(SEARCH_CRITERIA);
        request.setAttribute("searchText",text);
        if(criteria.equals(SEARCH_BY_READER_NAME))
            request.setAttribute("readers", adminService.getReadersByName(text));
        else
            request.setAttribute("readers", adminService.getReadersByEmail(text));

        return Config.getInstance().getProperty(Config.READERS);
    }
}
