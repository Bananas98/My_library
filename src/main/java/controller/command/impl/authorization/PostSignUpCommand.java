package controller.command.impl.authorization;


import controller.command.Command;
import model.entity.Role;
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
        } else request.setAttribute("error", LocaleMessage.INVALID_INPUT);
        return "/WEB-INF/pages/signUp.jsp";
    }

    private User parseRequest(HttpServletRequest request) {
        return new User.Builder().setName(request.getParameter("name"))
                .setPassword(request.getParameter("password"))
                .setEmail(request.getParameter("email"))
                .setRole(Role.valueOf(request.getParameter("role"))).build();
    }

    private boolean validateUserInput(User user) {
//        Validator v = Validator.getInstance();
//        return v.validateEmail(user.getEmail()) && v.validatePassword(user.getPassword()) &&
//                v.validateName(user.getName());
        return true;
    }

    private void createUser(User user, HttpServletRequest request) {
        UserService userService = UserService.getInstance();
        userService.createUser(user);
        if (user.getId() != null) {
            request.setAttribute("users_id", user.getId());
        } else {
            request.setAttribute("error", LocaleMessage.SIGN_UP_ERROR);
        }
    }
}
