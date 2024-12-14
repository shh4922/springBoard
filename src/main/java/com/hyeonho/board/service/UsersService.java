package com.hyeonho.board.service;

import com.hyeonho.board.auth.JwtTokenProvider;
import com.hyeonho.board.domain.Users;
import com.hyeonho.board.dto.user.LoginResponseDTO;
import com.hyeonho.board.repository.UsersRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
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

    public LoginResponseDTO login(String email, String password) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 email 입니다."));

        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new IllegalArgumentException("잘못된 password 입니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(email);
//        RefreshToken.removeUserRefreshToken(user.getUser_id());
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        LoginResponseDTO res = new LoginResponseDTO();
        res.setAcessToken(accessToken);
        res.setRefreshToken(refreshToken);

        return res;
    }


    public boolean isEmailDuplicate(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    public boolean isTrueUser(Long id) {
        return usersRepository.findById(id).isPresent();
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("그런 이메일 하는애 없음ㅋㅋ"));
    }

    public UserDetails loadUserByEmail(String email) {
        return (UserDetails) usersRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("그런 이메일 하는애 없음ㅋㅋ"));
    }
}
