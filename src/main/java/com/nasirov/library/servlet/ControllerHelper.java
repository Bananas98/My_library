/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nasirov.library.servlet;


import com.nasirov.library.commands.ICommand;
import com.nasirov.library.commands.librarian.*;
import com.nasirov.library.commands.readers.*;
import com.nasirov.library.commands.authorization.CommandLogin;
import com.nasirov.library.commands.authorization.CommandLogout;
import com.nasirov.library.commands.authorization.CommandRegistration;
import com.nasirov.library.commands.authorization.CommandRegistrationPage;
import com.nasirov.library.commands.adding.CommandAddAuthor;
import com.nasirov.library.commands.adding.CommandAddBook;
import com.nasirov.library.commands.adding.CommandAddGenre;
import com.nasirov.library.commands.admin.CommandSetRole;
import com.nasirov.library.commands.search.CommandAuthorBooks;
import com.nasirov.library.commands.search.CommandAuthorsSearch;
import com.nasirov.library.commands.search.CommandBookSearch;
import com.nasirov.library.commands.search.CommandReadersSearch;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class ControllerHelper {

    private static ControllerHelper instance = null;
    private Map<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("searchBooks",new CommandBookSearch());
        commands.put("readers",new CommandReaders());
        commands.put("searchReaders",new CommandReadersSearch());
        commands.put("registration",new CommandRegistration());
        commands.put("addBook",new CommandAddBook());
        commands.put("bookLenders",new CommandBookLenders());
        commands.put("allOfAuthor",new CommandAuthorBooks());
        commands.put("readerInfo",new CommandReaderInfo());
        commands.put("searchAuthor",new CommandAuthorsSearch());
        commands.put("addAuthor",new CommandAddAuthor());
        commands.put("returnBook",new CommandReturnBook());
        commands.put("logout",new CommandLogout());
        commands.put("addGenre",new CommandAddGenre());
        commands.put("mainMenu",new CommandMainMenu());
        commands.put("orderBook",new CommandOrderBook());
        commands.put("orders",new CommandOrders());
        commands.put("getBook",new CommandGetBook());
        commands.put("readingRoom",new CommandReadingRoom());
        commands.put("registrationPage",new CommandRegistrationPage());
        commands.put("deleteBook",new CommandDeleteBook());
        commands.put("setBookAmount",new CommandSetBookAmount());
        commands.put("switchLang",new CommandSwitchLang());
        commands.put("setRole",new CommandSetRole());
        commands.put("setPage",new CommandSetPage());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }
}
