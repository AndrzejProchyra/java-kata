package io.prochyra.socialnetwork.infra.persistence;

import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.application.model.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

abstract class UserRepositoryTest {

    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = createUserRepository();
    }

    abstract UserRepository createUserRepository();

    @Test
    void should_save_and_retrieve_a_user() {
        var user = new User("username");

        userRepository.save(user);
        var retrievedUser = userRepository.findByName("username");

        then(retrievedUser).get()
                .usingRecursiveComparison()
                .isEqualTo(user);
    }
}