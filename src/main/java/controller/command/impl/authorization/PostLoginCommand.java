package controller.command.impl.authorization;

import controller.command.Command;
import controller.constants.Page;
import controller.constants.Parameters;
import model.entity.User;
import model.services.UserService;
import utils.LoginDto;
import utils.Validator;
import utils.locale.LocaleMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostLoginCommand implements Command {

    private static UserService userService = UserService.getInstance();

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getAttribute("user") != null) {
            return "/index.jsp";
        }

        LoginDto loginDto = getUserInput(request);

        if (!validateUserInput(loginDto)) {
            request.setAttribute("error", LocaleMessage.INVALID_LOGIN_DATA);
            return "/WEB-INF/pages/login.jsp";
        }

        User user = userService.signInUser(loginDto);
        if (user == null) {
            return "/WEB-INF/pages/login.jsp";
        }
        request.getSession().setAttribute("user", user);
        return "/index.jsp";
    }

    private LoginDto getUserInput(HttpServletRequest request) {
        return new LoginDto( request.getParameter("password"),request.getParameter("email"));
    }

    private boolean validateUserInput(LoginDto loginDto) {
        return (Validator.getInstance().validateEmail(loginDto.getEmail()) &&
                loginDto.getPassword() != null && !loginDto.getPassword().isEmpty());

    }
}
