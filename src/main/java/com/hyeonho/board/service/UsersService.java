package com.hyeonho.board.service;

import com.hyeonho.board.domain.Users;
import com.hyeonho.board.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Users register(Users users) {
        try {
            if(isEmailDuplicate(users.getEmail())) {
                throw new IllegalArgumentException("사용중인 이메일");
            }
            return usersRepository.save(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isEmailDuplicate(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }
}
