package org.henry.service.implementation;

import org.henry.dao.UserDao;
import org.henry.service.UserService;
import org.henry.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final UserTransformer userTransformer;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserTransformer userTransformer) {
        this.userDao = userDao;
        this.userTransformer = userTransformer;
    }

    @Override
    public UserDao getDao() {
        return userDao;
    }

    @Override
    public UserTransformer getTransformer() {
        return userTransformer;
    }
}
