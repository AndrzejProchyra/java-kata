package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.AdjustableInstantSource;
import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.infra.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.InstantSource;

import static org.assertj.core.api.BDDAssertions.then;

class SocialNetworkTest {
    @Test
    void post_creates_a_user() {
        var userRepository = new InMemoryUserRepository();
        var socialNetwork = new SocialNetwork(userRepository, InstantSource.system());

        socialNetwork.post("Alice", "any message");

        then(userRepository.findByName("Alice"))
                .get()
                .extracting(User::name)
                .isEqualTo("Alice");
    }

    @Test
    void post_publishes_a_message_to_a_user_timeline() {
        var userRepository = new InMemoryUserRepository();
        var now = Instant.now();
        var socialNetwork = new SocialNetwork(userRepository, new AdjustableInstantSource(now));
        String message = "The rain in Spain falls mainly on the plain.";

        socialNetwork.post("Alice", message);

        var alice = userRepository.findByName("Alice");
        then(alice).isPresent();
        then(alice.get().timeLine())
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(new Post(message, now));
    }
}