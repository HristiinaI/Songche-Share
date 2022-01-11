package com.songcheShare.project.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.songcheShare.project.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUserTest() {
        User user = new User();
        user.setEmail("p.petrov@gmail.com");
        user.setPassword("petrov");
        user.setFirstName("Petar");
        user.setLastName("Petrov");
        user.setUsername("p.petrov");

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void findByEmailTest() {
        String email = "p.petrov@gmail.com";

        User user = userRepository.findByEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void findByEmailTest_When_User_Does_Not_Exist() {
        String email = "p1.petrov@gmail.com";

        User user = userRepository.findByEmail(email);

        assertThat(user).isEqualTo(null);
    }
}
