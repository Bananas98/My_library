package controller.command.impl.catalog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetSearchBookCommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/WEB-INF/pages/searchBook.jsp";
    }
}
