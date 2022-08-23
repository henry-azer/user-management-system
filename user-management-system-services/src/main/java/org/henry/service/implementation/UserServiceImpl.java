package org.henry.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.henry.dao.UserDao;
import org.henry.dto.UserDto;
import org.henry.dto.UserPaginationFiltrationDto;
import org.henry.dto.request.FilterPaginationRequest;
import org.henry.dto.response.PaginationResponse;
import org.henry.entity.User;
import org.henry.service.UserService;
import org.henry.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
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
    public PaginationResponse<UserDto> findAllUsersPaginatedFiltered(FilterPaginationRequest<UserPaginationFiltrationDto> userPaginationFiltrationDto) {
        log.info("findAllUsersPaginatedFiltered was called");
        Page<User> users = getDao().findAllUsersPaginatedFiltered(userPaginationFiltrationDto);

        return buildPaginationResponse(users);
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
