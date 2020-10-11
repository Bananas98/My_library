package model.services;

import model.dao.DaoConnection;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.Role;
import model.entity.User;
import utils.LoginDto;
import utils.PasswordHasher;

import java.util.Optional;

public class UserService {

    private static UserService instance = new UserService(DaoFactory.getDaoFactory());

    public static UserService getInstance(){
        return instance;
    }

    private DaoFactory daoFactory;

    private UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void createUser(User user){
        user.setPassword(PasswordHasher.getInstance().hashPassword(user.getPassword()));
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.create(user);
        }
    }

    public Optional<User> getUserById(int readerId) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getById(readerId);
        }
    }

    public Optional<User> getByLogin(String login) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.getByLogin(login);
        }
    }

    public User signInUser(LoginDto loginDto){
        Optional<User> userOptional;
        try (UserDao userDao = daoFactory.createUserDao()) {
            userOptional = userDao.getByLogin(loginDto.getEmail());
        }
        boolean verified = PasswordHasher.getInstance().
                verifyPassword(userOptional.get().getPassword(), loginDto.getPassword());
        if (verified) {
            return userOptional.get();
        }
        return null;
    }
}
