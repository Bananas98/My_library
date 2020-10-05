package controller.command.impl;

import controller.command.Command;
import controller.constants.Page;
import controller.constants.Parameters;
import utils.locale.LocaleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ChangeLocaleCommand implements Command {

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocale(request);
        return "/index.jsp";
    }

    private void setLocale(HttpServletRequest request) {
        String selectedLanguage = request.getParameter("lang");
        Locale chosenLocale = Locale.US;
        request.getSession().setAttribute("locale", chosenLocale);
        LocaleManager.setResourceBundleLocale(chosenLocale);
    }
}
