package controller.command.impl;

import controller.command.Command;
import controller.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        return "/index.jsp";
    }

}
