package model.dao.jdbc;

import exception.DatabaseException;
import model.dao.UserDao;
import model.entity.Role;
import model.entity.User;

import java.sql.*;
import java.util.Optional;

public class JdbcUserDao implements UserDao {

    private Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void create(User user) {
        String createQuery = "INSERT INTO USERS (name, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement query = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            query.setString(3, user.getEmail());
            query.setString(4, user.getRole().name().toLowerCase());
            query.executeUpdate();

            ResultSet keys = query.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void update(User user) {
        String updateUserQuery = "UPDATE users SET name = ?, password = ?, email = ?, "
                +  "role = ?, bill = ? WHERE id = ?";
        try (PreparedStatement query = connection.prepareStatement(updateUserQuery)) {
            query.setString(1, user.getName());
            query.setString(2, user.getPassword());
            query.setString(3, user.getEmail());
            query.setString(4, user.getRole().name().toLowerCase());
            query.setDouble(5, user.getBill());
            query.setInt(6, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void delete(User user) {
        String DELETE_USER = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement query = connection.prepareStatement(DELETE_USER)) {
            query.setLong(1, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Optional<User> getById(Long id){
        String getByLogin  = "SELECT * FROM users WHERE id = ?";
        Optional<User> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(getByLogin)) {
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            user = Optional.of(parseResultSet(resultSet));
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return user;
    }

    @Override
    public Optional<User> getByLogin(String email) {
        String getByPhoneNumber  = "SELECT * FROM users WHERE email = ?";
        Optional<User> user = Optional.empty();
        try (PreparedStatement query = connection.prepareStatement(getByPhoneNumber)) {
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return user;
    }

    static User parseResultSet(ResultSet resultSet) throws SQLException {
        return new User.Builder().setId(resultSet.getInt("users.id"))
                .setName(resultSet.getString("name"))
                .setPassword(resultSet.getString("password"))
                .setEmail(resultSet.getString("email"))
                .setRole(Role.valueOf(resultSet.getString("role").toUpperCase()))
                .setBill(resultSet.getDouble("bill"))
                .build();
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
