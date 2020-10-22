package com.nasirov.library.commands.librarian;

import com.nasirov.library.commands.ICommand;
import com.nasirov.library.models.ReaderBook;
import com.nasirov.library.managers.Config;
import com.nasirov.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandReadingRoom implements ICommand {

    private AdminService adminService=AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReaderBook> readerBooks = adminService.getBooksFromReadingRoom();
        request.setAttribute("readerBooks", readerBooks);
        return Config.getInstance().getProperty(Config.READING_ROOM);
    }
}
