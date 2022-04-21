package org.henry.service;

import org.henry.dao.UserDao;
import org.henry.dto.UserDto;
import org.henry.entity.User;
import org.henry.transformer.UserTransformer;

public interface UserService extends BaseService<User, UserDto, UserDao, UserTransformer> {
}
