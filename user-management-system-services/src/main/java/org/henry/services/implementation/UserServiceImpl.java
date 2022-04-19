package org.henry.services.implementation;

import lombok.RequiredArgsConstructor;
import org.henry.dao.UserDAO;
import org.henry.repository.UserRepository;
import org.henry.services.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDAO getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDAO createUser(UserDAO userDAO) {
        return userRepository.save(userDAO);
    }

    @Override
    public UserDAO updateUser(UserDAO userDAO) {
        return userRepository.saveAndFlush(userDAO);
    }

    @Override
    public UserDAO deleteUserById(int id) {
        return userRepository.deleteById(id);
    }

}
