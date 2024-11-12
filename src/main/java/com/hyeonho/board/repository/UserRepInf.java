package com.hyeonho.board.repository;

import com.hyeonho.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepInf {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAllUser();
}
