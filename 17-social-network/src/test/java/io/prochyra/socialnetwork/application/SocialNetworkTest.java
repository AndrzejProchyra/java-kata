package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.AdjustableInstantSource;
import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.infra.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

class SocialNetworkTest {

    static final Instant NOW = Instant.now();
    SocialNetwork socialNetwork;
    InMemoryUserRepository userRepository;
    AdjustableInstantSource instantSource;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        instantSource = new AdjustableInstantSource(NOW);
        socialNetwork = new SocialNetwork(userRepository, instantSource);
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
                .contains(new Post(firstMessage, NOW), new Post(secondMessage, NOW));
    }

    @Test
    void should_return_timeline_in_reverse_chronological_order() {
        socialNetwork.post("Steve", "first message");
        advanceTimeByMinutes(1);
        socialNetwork.post("Steve", "second message");
        advanceTimeByMinutes(1);
        socialNetwork.post("Steve", "third message");

        then(socialNetwork.timelineFor("Steve"))
                .containsExactly(
                        new PostWithName("Steve", "third message", NOW.plus(Duration.ofMinutes(2))),
                        new PostWithName("Steve", "second message", NOW.plus(Duration.ofMinutes(1))),
                        new PostWithName("Steve", "first message", NOW));
    }

    @Test
    void should_follow_a_user() {
        var charlie = new User("Charlie");
        userRepository.save(charlie);
        var bob = new User("Bob");
        userRepository.save(bob);

        socialNetwork.follow("Bob", "Charlie");

        Optional<User> retrievedBob = userRepository.findByName("Bob");
        then(retrievedBob).isPresent();
        then(retrievedBob.get().followedUsers())
                .containsExactly("Charlie");
    }

    private void advanceTimeByMinutes(int minutes) {
        instantSource.advanceBy(Duration.ofMinutes(minutes));
    }
}