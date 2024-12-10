package com.hyeonho.board.service;

import com.hyeonho.board.domain.Users;
import com.hyeonho.board.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Users register(Users users) {
        try {
            if(isEmailDuplicate(users.getEmail())) {
                throw new IllegalArgumentException("사용중인 이메일");
            }

            String encodePassword = passwordEncoder.encode(users.getPassword());

            Users newUser = new Users();
            newUser.setEmail(users.getEmail());
            newUser.setName(users.getName());
            newUser.setPassword(encodePassword);

            return usersRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Users login(String email, String password) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 email 입니다."));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw  new IllegalArgumentException("잘못된 password 입니다.");
        }

        return user;
    }

    public boolean isEmailDuplicate(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    public boolean isTrueUser(Long id) {
        return usersRepository.findById(id).isPresent();
    }
}
