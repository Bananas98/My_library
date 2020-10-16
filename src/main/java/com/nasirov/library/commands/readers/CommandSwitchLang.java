package com.nasirov.library.commands.readers;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandSwitchLang implements ICommand {

    private final static String REQUEST_PAGE="page";
    private final static String LOGIN_PAGE="login";
    private final static String MAIN_PAGE="main";
    private final static String NEW_LOCALE="lang";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("locale", request.getParameter(NEW_LOCALE));
        String page=request.getParameter(REQUEST_PAGE);
        if(page==null||page.equals(LOGIN_PAGE)){
            return Config.getInstance().getProperty(Config.LOGIN);
        }
        if(page.equals(MAIN_PAGE)){
            if(request.getSession().getAttribute(CURRENT_USER)==null){
                return Config.getInstance().getProperty(Config.LOGIN);
            }
            return Config.getInstance().getProperty(Config.MAIN);
        }
        return Config.getInstance().getProperty(Config.REGISTRATION);
    }
}
