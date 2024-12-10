package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.infra.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class SocialNetworkTest {
    @Test
    void post_creates_a_user() {
        var userRepository = new InMemoryUserRepository();
        var alice = new User("Alice");
        var socialNetwork = new SocialNetwork(userRepository, null);

        socialNetwork.post("Alice", "any message");

        then(userRepository.findByName("Alice")).get()
                .usingRecursiveComparison()
                .isEqualTo(alice);
    }
}