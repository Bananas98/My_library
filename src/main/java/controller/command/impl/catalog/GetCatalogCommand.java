package controller.command.impl.catalog;

import controller.command.Command;
import controller.constants.Page;
import model.entity.Book;
import model.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetCatalogCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = Integer.parseInt(request.getParameter("page"));
        List<Book> books = BookService.getInstance().getBook(pageNumber);
        request.setAttribute("books", books);
        return "/WEB-INF/pages/catalog.jsp";
    }
}
