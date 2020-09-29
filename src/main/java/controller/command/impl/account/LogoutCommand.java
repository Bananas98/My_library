package controller.command.impl.account;



import controller.command.Command;
import controller.constants.Page;
import controller.constants.Parameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute(Parameters.USER);
        return Page.HOME;
    }

}
