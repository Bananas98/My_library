package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.models.Reader;
import com.nasirov.library.models.ReaderBook;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.AdminService;
import com.nasirov.library.services.AuthorizationService;
import com.nasirov.library.services.IssuanceBookService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class CommandReaderInfo implements ICommand {

    private AdminService adminService = AdminService.getInstance();
    private IssuanceBookService issuanceBookService = IssuanceBookService.getInstance();
    private AuthorizationService authorizationService=AuthorizationService.getInstance();

    private final static String READER_ID="id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader currentUser = (Reader) request.getSession().getAttribute(CURRENT_USER);
        List<ReaderBook> readerBooks;
        if (authorizationService.isAdmin(currentUser.getRoles())) {
            int readerId = Integer.parseInt(request.getParameter(READER_ID));
            request.getSession().setAttribute("reader", adminService.getReaderById(readerId));
            readerBooks = adminService.getBooksOfReaderForAdmin(readerId);
        } else {
            readerBooks = issuanceBookService.getBooksOfReaderForReader(currentUser.getId());
        }
        request.setAttribute("readerBooks", readerBooks);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("currentDate", timestamp);
        return Config.getInstance().getProperty(Config.READER_INFO);
    }
}
