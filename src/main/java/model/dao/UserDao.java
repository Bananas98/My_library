package model.dao;

import model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDAO<User> {

    Optional<User> getByLogin(String login);

}
