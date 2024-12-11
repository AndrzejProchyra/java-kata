package io.prochyra.socialnetwork;

import io.prochyra.socialnetwork.application.PostWithName;
import io.prochyra.socialnetwork.application.SocialNetwork;
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

        var expectedPost = new PostWithName("Alice", message, NOW);
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

        var expectedFirstPost = new PostWithName("Bob", firstMessage, NOW);
        var expectedSecondPost = new PostWithName("Bob", secondMessage, NOW.plus(Duration.ofMinutes(1)));
        then(socialNetwork.timelineFor("Bob"))
                .containsExactly(expectedSecondPost, expectedFirstPost);
    }

    @Test
    void Charlie_subscribes_to_Alice_and_Bobs_timeline_and_views_an_aggregate_of_all_posts() {
        socialNetwork.post("Alice", "I love the weather today");
        advanceTimeByMinutes(5);
        socialNetwork.post("Charlie", "I'm in New York today! Anyone want to have a coffee?");
        advanceTimeBySeconds(2);

        socialNetwork.follow("Charlie", "Alice");

        var charliesPost = new PostWithName("Charlie",
                "I'm in New York today! Anyone want to have a coffee?",
                NOW.plus(Duration.ofMinutes(5)));
        var alicesPost = new PostWithName("Alice", "I love the weather today", NOW);
        then(socialNetwork.wallFor("Charlie"))
                .containsExactly(charliesPost, alicesPost);
    }

    private void advanceTimeBySeconds(int seconds) {
        instantSource.advanceBy(Duration.ofSeconds(seconds));
    }

    private void advanceTimeByMinutes(int minutes) {
        instantSource.advanceBy(Duration.ofMinutes(minutes));
    }
}
