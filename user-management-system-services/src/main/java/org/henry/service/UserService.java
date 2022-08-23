package org.henry.service;

import org.henry.dao.UserDao;
import org.henry.dto.UserDto;
import org.henry.dto.UserPaginationFiltrationDto;
import org.henry.dto.request.FilterPaginationRequest;
import org.henry.dto.response.PaginationResponse;
import org.henry.entity.User;
import org.henry.transformer.UserTransformer;

public interface UserService extends BaseService<User, UserDto, UserDao, UserTransformer> {
    PaginationResponse<UserDto> findAllUsersPaginatedFiltered(FilterPaginationRequest<UserPaginationFiltrationDto> userPaginationFiltrationDto);
}