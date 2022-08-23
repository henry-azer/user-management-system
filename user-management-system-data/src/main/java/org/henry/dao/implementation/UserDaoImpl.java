package org.henry.dao.implementation;

import org.henry.dao.UserDao;
import org.henry.dto.UserPaginationFiltrationDto;
import org.henry.dto.request.FilterPaginationRequest;
import org.henry.entity.User;
import org.henry.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAllUsersPaginatedFiltered(FilterPaginationRequest<UserPaginationFiltrationDto> userPaginationFiltrationDto) {
        PageRequest pageRequest = PageRequest.
                of(userPaginationFiltrationDto.getPageNumber() - 1, userPaginationFiltrationDto.getPageSize(), buildSort(userPaginationFiltrationDto, User.class));
        UserPaginationFiltrationDto criteria = userPaginationFiltrationDto.getCriteria();

        return !Objects.equals(criteria.getName(), "")
                ? getRepository().findAllByNameContainsIgnoreCase(criteria.getName(), pageRequest)
                : getRepository().findAll(pageRequest);
    }

    @Override
    public UserRepository getRepository() {
        return userRepository;
    }

}