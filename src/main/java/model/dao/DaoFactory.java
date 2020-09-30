package model.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(DaoFactory.class);
    private static final String DB_FILE = "/config.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";
    private static DaoFactory daoFactory;
    public static int LIMIT;

    public abstract DaoConnection getConnection();

    public abstract UserDao createUserDao();

    public abstract UserDao createUserDao(DaoConnection connection);

//    public abstract BookDao createBookDescriptionDao();
//
//    public abstract BookDao createBookDescriptionDao(DaoConnection connection);
//
//    public abstract BookInstanceDao createBookInstanceDao();
//
//    public abstract BookInstanceDao createBookInstanceDao(DaoConnection connection);
//
//    public abstract AuthorDao createAuthorDao();
//
//    public abstract AuthorDao createAuthorDao(DaoConnection connection);
//
//    public abstract OrderDao createBookOrderDao();
//
//    public abstract OrderDao createBookOrderDao(DaoConnection connection);

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                LIMIT = Integer.parseInt(dbProps.getProperty("limit"));
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();

            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                LOGGER.error("Can't load inputStream db config file to properties object", e);
                //throw new ServerException(e);
            }
        }
        return daoFactory;
    }

}