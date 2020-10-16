package com.nasirov.library.commands.adding;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;
import com.nasirov.library.managers.Message;
import com.nasirov.library.services.AddingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandAddAuthor implements ICommand {

    private static final String NAME = "name";
    private AddingService addingService = AddingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=Config.getInstance().getProperty(Config.ADD_AUTHOR);
        String authorName = request.getParameter(NAME);
        String locale=(String) request.getSession().getAttribute(LOCALE);
        if(authorName==null){
            return page;
        }
        if(addingService.addNewAuthor(authorName)==0){
            request.setAttribute("resultForAuthor",Message.getInstance(locale).getString(Message.AUTHOR_ALREADY_EXIST_ERROR));
        }else {
            request.setAttribute("resultForAuthor",Message.getInstance(locale).getString(Message.AUTHOR_ADDED));
        }
        return page;
    }
}
