package model.dao;

import java.util.Optional;

public interface GenericDAO<T> extends AutoCloseable {

    void create(T object);

    void update(T object);

    void delete(T object);

    Optional<T> getById(int key);

    void close();
}
