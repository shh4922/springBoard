package com.hyeonho.board;

import com.hyeonho.board.domain.User;
import com.hyeonho.board.repository.UserRepository;
import com.hyeonho.board.service.UserService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void 회원가입() {

        for(int i=0; i<5; i++) {
            User user = new User();
            user.setName("t"+i);
            user.setUsername("t"+i);
            user.setPassword("t"+i);

            User joinUser = userService.save(user);

            User findUser = userService.findById(joinUser.getId()).get();

            System.out.println(joinUser);
            System.out.println(findUser);
            Assertions.assertThat(user.getId()).isEqualTo(findUser.getId());
        }

    }
}
