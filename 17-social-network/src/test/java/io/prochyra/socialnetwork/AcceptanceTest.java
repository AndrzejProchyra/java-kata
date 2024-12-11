package io.prochyra.socialnetwork;

import io.prochyra.socialnetwork.application.SocialNetwork;
import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.infra.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.BDDAssertions.then;

class AcceptanceTest {

    SocialNetwork socialNetwork;
    static final Instant NOW = Instant.now();
    AdjustableInstantSource instantSource;

    @BeforeEach
    void setUp() {
        instantSource = new AdjustableInstantSource(NOW);
        socialNetwork = new SocialNetwork(new InMemoryUserRepository(), instantSource);
    }

    @Test
    void given_Alice_posts_a_message_then_she_can_see_it_on_her_timeline() {
        var message = "I love the weather today";

        socialNetwork.post("Alice", message);

        var expectedPost = new Post(message, NOW);
        then(socialNetwork.timelineFor("Alice"))
                .contains(expectedPost);
    }

    @Test
    void given_Bob_posts_two_messages_he_can_see_them_on_his_timeline_in_reverse_chronological_order() {
        var firstMessage = "Damn! We lost!";
        var secondMessage = "Good game though.";

        socialNetwork.post("Bob", firstMessage);
        advanceTimeByMinutes(1);
        socialNetwork.post("Bob", secondMessage);

        var expectedFirstPost = new Post(firstMessage, NOW);
        var expectedSecondPost = new Post(secondMessage, NOW.plus(Duration.ofMinutes(1)));
        then(socialNetwork.timelineFor("Bob"))
                .containsExactly(expectedSecondPost, expectedFirstPost);
    }

    private void advanceTimeByMinutes(int minutes) {
        instantSource.advanceBy(Duration.ofMinutes(minutes));
    }
}
