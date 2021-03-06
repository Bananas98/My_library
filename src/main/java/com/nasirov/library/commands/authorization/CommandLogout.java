package com.nasirov.library.commands.authorization;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandLogout implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
