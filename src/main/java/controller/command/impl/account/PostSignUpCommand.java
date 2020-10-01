package controller.command.impl.account;



import controller.command.Command;
import controller.constants.Page;
import controller.constants.Parameters;
import model.entity.User;
import model.services.UserService;
import utils.Validator;
import utils.locale.LocaleMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostSignUpCommand implements Command {

    private static UserService userService = UserService.getInstance();

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = parseRequest(request);
        if (validateUserInput(user)) {
            createUser(user, request);
        } else request.setAttribute(Parameters.ERROR, LocaleMessage.INVALID_INPUT);
        return Page.SIGN_UP;
    }

    private User parseRequest(HttpServletRequest request) {
        return new User.Builder().setName(request.getParameter(Parameters.NAME))
                                 .setPassword(request.getParameter(Parameters.PASSWORD))
                                 .setEmail(request.getParameter(Parameters.EMAIL)).build();
    }

    private boolean validateUserInput(User user){
        Validator v = Validator.getInstance();
        return v.validateEmail(user.getEmail()) && v.validatePassword(user.getPassword()) &&
                v.validateName(user.getName());
    }

    private void createUser(User user, HttpServletRequest request) {
        UserService userService = UserService.getInstance();
        userService.createUser(user);
        if (user.getId() != null) {
            request.setAttribute(Parameters.USER_ID, user.getId());
        } else {
            request.setAttribute(Parameters.ERROR, LocaleMessage.SIGN_UP_ERROR);
        }
    }
}
