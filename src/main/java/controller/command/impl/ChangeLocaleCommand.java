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
        return Page.HOME;
    }

    private void setLocale(HttpServletRequest request) {
        String selectedLanguage = request.getParameter(Parameters.LANG);
        Locale chosenLocale = Locale.US;
        request.getSession().setAttribute(Parameters.LOCALE, chosenLocale);
        LocaleManager.setResourceBundleLocale(chosenLocale);
    }
}
