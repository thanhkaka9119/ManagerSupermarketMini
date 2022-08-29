package com.vnpt.User;

import com.vnpt.data_access.IUserRepository;
import com.vnpt.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.assertj.core.api.Assertions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private IUserRepository userRepository;

    @Test
    public void getUserByUserNameTest(){
        User user = userRepository.findByUsername("patrick");
        Assertions.assertThat(user.getUsername()).isEqualTo("patrick");
    }
}
