package com.hyeonho.board.repository;

import com.hyeonho.board.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {

}
