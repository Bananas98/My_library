/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nasirov.library.managers;

import java.util.ResourceBundle;

public class Config {


    public static final String ADMIN = "ADMIN";
    private static Config instance;

    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "config";
    public static final String MAIN = "MAIN";
    public static final String LOGIN = "LOGIN";
    public static final String READERS ="READERS";
    public static final String REGISTRATION="REGISTRATION";
    public static final String ADD_BOOK ="ADD_BOOK";
    public static final String BOOK_LENDERS = "BOOK_LENDERS";
    public static final String READER_INFO ="READER_INFO";
    public static final String ADD_AUTHOR ="ADD_AUTHOR";
    public static final String ORDERS = "ORDERS";
    public static final String READING_ROOM="READING_ROOM";

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
