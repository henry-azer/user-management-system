package org.henry.services;

import org.henry.dao.UserDAO;

public interface UserService {

    UserDAO getUserById(int id);

    UserDAO createUser(UserDAO userDAO);

    UserDAO updateUser(UserDAO userDAO);

    UserDAO deleteUserById(int id);
}
