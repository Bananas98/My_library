/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nasirov.library.commands.authorization;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.models.Reader;
import com.nasirov.library.services.AdminService;
import com.nasirov.library.services.SearchService;
import com.nasirov.library.managers.Config;
import com.nasirov.library.managers.Message;
import com.nasirov.library.services.AuthorizationService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private SearchService searchService =  SearchService.getInstance();
    private AuthorizationService authorizationService = AuthorizationService.getInstance();
    private AdminService adminService = AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Reader reader;
        reader= authorizationService.login(login,password);
        if (reader!=null) {
            request.getSession().setAttribute("user",reader);
            if(reader.getHost()){
                request.setAttribute("users", adminService.getUsersForHost());
                return Config.getInstance().getProperty(Config.ADMIN);
            }
            request.getSession().setAttribute("books", searchService.getAllBooks());
            request.getServletContext().setAttribute("genres", searchService.getAllGenres());
            return Config.getInstance().getProperty(Config.MAIN);
        }
            String locale=(String) request.getSession().getAttribute(LOCALE);
            request.setAttribute("error", Message.getInstance(locale).getString(Message.LOGIN_ERROR));
            return Config.getInstance().getProperty(Config.LOGIN);

    }
}
