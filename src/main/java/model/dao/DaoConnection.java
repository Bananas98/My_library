package model.dao;

public interface DaoConnection {
    void begin();

    void commit();

    void rollback();

    void close();

}
