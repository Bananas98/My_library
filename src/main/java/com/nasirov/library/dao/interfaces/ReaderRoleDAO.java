package com.nasirov.library.dao.interfaces;

public interface ReaderRoleDAO {

    /**
     * give admin rights
     * @param userId
     */
    void makeAdmin(Integer userId);

    /**
     * picks administrator rights
     * @param userId
     */
    void unmakeAdmin(Integer userId);
}
