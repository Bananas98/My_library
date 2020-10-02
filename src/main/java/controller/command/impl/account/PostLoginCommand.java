package controller.command.impl.account;

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

        if (request.getAttribute(Parameters.USER) != null) {
            return Page.HOME;
        }

        LoginDto loginDto = getUserInput(request);

        if (!validateUserInput(loginDto)) {
            request.setAttribute(Parameters.ERROR, LocaleMessage.INVALID_LOGIN_DATA);
            return Page.LOGIN_PAGE;
        }

        User user = userService.signInUser(loginDto);
        if (user == null) {
            return Page.LOGIN_PAGE;
        }
        request.getSession().setAttribute(Parameters.USER, user);
        return Page.HOME;
    }

    private LoginDto getUserInput(HttpServletRequest request) {
        return new LoginDto(request.getParameter(Parameters.PHONE_NUMBER), request.getParameter(Parameters.PASSWORD));
    }

    private boolean validateUserInput(LoginDto loginDto) {
        return (Validator.getInstance().validateEmail(loginDto.getEmail()) &&
                loginDto.getPassword() != null && !loginDto.getPassword().isEmpty());

    }
}
