package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.AdjustableInstantSource;
import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.infra.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.BDDAssertions.then;

class SocialNetworkTest {

    public static final Instant NOW = Instant.now();
    private SocialNetwork socialNetwork;
    private InMemoryUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        socialNetwork = new SocialNetwork(userRepository, new AdjustableInstantSource(NOW));
    }

    @Test
    void post_creates_a_user_if_it_does_not_exist() {
        socialNetwork.post("Alice", "any message");

        then(userRepository.findByName("Alice"))
                .get()
                .extracting(User::name)
                .isEqualTo("Alice");
    }

    @Test
    void post_publishes_a_message_to_a_user_timeline() {
        var message = "The rain in Spain falls mainly on the plain.";

        socialNetwork.post("Alice", message);

        var alice = userRepository.findByName("Alice");
        then(alice).isPresent();
        then(alice.get().timeLine())
                .containsExactly(new Post(message, NOW));
    }

    @Test
    void post_publishes_a_message_to_an_existing_user_timeline() {
        var firstMessage = "This is the first message";
        var secondMessage = "This is the second message";

        socialNetwork.post("Alice", firstMessage);
        socialNetwork.post("Alice", secondMessage);

        var retrievedAlice = userRepository.findByName("Alice");
        then(retrievedAlice).isPresent();
        then(retrievedAlice.get().timeLine())
                .containsExactly(new Post(firstMessage, NOW), new Post(secondMessage, NOW));
    }
}