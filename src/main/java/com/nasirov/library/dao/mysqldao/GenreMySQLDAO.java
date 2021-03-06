package com.nasirov.library.dao.mysqldao;

import com.nasirov.library.dao.factory.Connector;
import com.nasirov.library.dao.interfaces.GenreDAO;
import com.nasirov.library.models.Genre;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreMySQLDAO implements GenreDAO {

    private final static Logger LOGGER = Logger.getLogger(GenreMySQLDAO.class);


    private static volatile GenreMySQLDAO genreMySQLDAO;

    private final String SELECT_GENRE="SELECT * FROM genres ";
    private final String CREATE_GENRE="INSERT INTO genres (name) VALUES (?)";
    private final String SELECT_GENRE_BY_ID=SELECT_GENRE+"WHERE id=?";



    private GenreMySQLDAO(){

    }

    public static GenreMySQLDAO getInstance() {
        GenreMySQLDAO localInstance = genreMySQLDAO;
        if (localInstance == null) {
            synchronized (GenreMySQLDAO.class) {
                localInstance = genreMySQLDAO;
                if (localInstance == null) {
                    genreMySQLDAO = localInstance = new GenreMySQLDAO();
                }
            }
        }
        return localInstance;
    }



    @Override
    public Genre findById(int id) {
        Genre genre=new Genre();
        try(Connection connection= Connector.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SELECT_GENRE_BY_ID)) {
            preparedStatement.setInt(1,id);
            try( ResultSet resultSet=preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    genre.setId(resultSet.getInt("id"));
                    genre.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        List<Genre>genres=new ArrayList<>();
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_GENRE);
            ResultSet resultSet=preparedStatement.executeQuery()){
            while (resultSet.next()){
                genres.add(new Genre(resultSet.getInt("id"),resultSet.getString("name")));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return genres;
    }

    @Override
    public Integer create(String genreName) {
        Integer i=0;
        try(Connection connection=Connector.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(CREATE_GENRE)) {
            preparedStatement.setString(1,genreName);
            i= preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return i;
    }

}
