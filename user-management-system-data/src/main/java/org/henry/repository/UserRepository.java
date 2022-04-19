package org.henry.repository;

import org.henry.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO, Integer> {

    UserDAO findById(int id);

    UserDAO deleteById(int id);
}