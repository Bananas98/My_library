package com.nasirov.library.commands.admin;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.models.Reader;
import com.nasirov.library.services.AdminService;
import com.nasirov.library.managers.Config;
import com.nasirov.library.managers.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandSetRole implements ICommand {
    private AdminService adminService = AdminService.getInstance();

    private final static String USER_ID="userId";
    private final static String IS_USER_ADMIN="admin";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader=(Reader)request.getSession().getAttribute(CURRENT_USER);

        if(!reader.getHost()){
            return Config.getInstance().getProperty(Config.MAIN);
        }
        Integer userId= Integer.valueOf(request.getParameter(USER_ID));
        Boolean librarian= Boolean.valueOf(request.getParameter(IS_USER_ADMIN));
        String locale= (String) request.getSession().getAttribute(LOCALE);
        if(!librarian){
            if(!adminService.isReaderHasDebt(userId)) {
                adminService.makeAdmin(userId);
            }else {
                request.setAttribute("message", Message.getInstance(locale).getString(Message.USER_NEED_RETURN_BOOKS));
            }
        }else{
            adminService.unmakeAdmin(userId);
        }
        request.setAttribute("users", adminService.getUsersForHost());
        return Config.getInstance().getProperty(Config.HOST);
    }
}
