package org.henry.dao;

import org.henry.dto.UserPaginationFiltrationDto;
import org.henry.dto.request.FilterPaginationRequest;
import org.henry.entity.User;
import org.henry.repository.UserRepository;
import org.springframework.data.domain.Page;

public interface UserDao extends BaseDao<User, UserRepository> {
    Page<User> findAllUsersPaginatedFiltered(FilterPaginationRequest<UserPaginationFiltrationDto> userPaginationFiltrationDto);
}
