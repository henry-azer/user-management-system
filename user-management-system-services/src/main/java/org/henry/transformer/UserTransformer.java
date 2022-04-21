package org.henry.transformer;

import org.henry.dto.UserDto;
import org.henry.entity.User;
import org.henry.transformer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer implements BaseTransformer<User, UserDto, UserMapper> {

    private final UserMapper userMapper;

    @Autowired
    public UserTransformer(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserMapper getMapper() {
        return userMapper;
    }
}
