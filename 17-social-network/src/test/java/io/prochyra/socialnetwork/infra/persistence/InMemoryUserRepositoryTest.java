package io.prochyra.socialnetwork.infra.persistence;

import io.prochyra.socialnetwork.application.model.UserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryUserRepositoryTest extends UserRepositoryTest {
    @Override
    UserRepository createUserRepository() {
        return new InMemoryUserRepository();
    }

    @Test
    void ensure_setup() {
        assertThat(userRepository).isNotNull();
    }
}
