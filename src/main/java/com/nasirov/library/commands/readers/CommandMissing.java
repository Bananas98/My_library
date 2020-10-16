/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nasirov.library.commands.readers;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.managers.Config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandMissing implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false)==null||request.getSession().getAttribute(CURRENT_USER)==null) {
            return Config.getInstance().getProperty(Config.LOGIN);
        }else return Config.getInstance().getProperty(Config.MAIN);
    }
}
