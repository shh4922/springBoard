package com.hyeonho.board.repository;

import com.hyeonho.board.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {


    Optional<Users> findByEmail(String email);

    Optional<Users> findById(Long id);
}
