package com.nasirov.library.dao.interfaces;

import com.nasirov.library.models.Reader;
import com.nasirov.library.models.Role;

import java.util.List;

public interface RoleDAO{

    /**
     * @param reader
     * @return all roles of reader
     */
    List<Role> findRolesForReader(Reader reader);
}
