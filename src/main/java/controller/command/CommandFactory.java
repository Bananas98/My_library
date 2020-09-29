package controller.command;

import controller.command.impl.HomeCommand;
import controller.command.impl.PageNotFoundCommand;
import controller.command.impl.account.GetLoginCommand;
import controller.command.impl.account.GetSignUpCommand;
import controller.command.impl.account.LogoutCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static String INDEX_PATH = ".*/main/";
    private static String REPLACEMENT = "";
    private static String DELIMITER = ":";

    private static Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("GET:", new HomeCommand());
        commands.put("GET:pageNotFound", new PageNotFoundCommand());
        commands.put("GET:login", new GetLoginCommand());
        commands.put("GET:logout", new LogoutCommand());
        commands.put("GET:signUp", new GetSignUpCommand());
    }

    public static Command getCommand(HttpServletRequest request) {
        String key = getKeyFromRequest(request);
        Command command = commands.get(key);
        return (command != null) ? command : commands.get("GET:pageNotFound");
    }

    public static String getKeyFromRequest(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(INDEX_PATH, REPLACEMENT);
        String key = method + DELIMITER + path;
        System.out.println(key);
        return key;
    }

}
