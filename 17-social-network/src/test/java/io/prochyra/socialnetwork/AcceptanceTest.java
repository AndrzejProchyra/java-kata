package io.prochyra.socialnetwork;

import io.prochyra.socialnetwork.application.SocialNetwork;
import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.infra.persistence.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.BDDAssertions.then;

class AcceptanceTest {

    @Test
    void given_Alice_posts_a_message_then_she_can_see_it_on_her_timeline() {
        var postTime = Instant.now();
        var instantSource = new AdjustableInstantSource(postTime);
        var socialNetwork = new SocialNetwork(new InMemoryUserRepository(), instantSource);
        var message = "I love the weather today";

        socialNetwork.post("Alice", message);

        var expectedPost = new Post(message, postTime);
        then(socialNetwork.timelineFor("Alice"))
                .contains(expectedPost);
    }

    @Test
    void given_Bob_posts_two_messages_he_can_see_them_on_his_timeline_in_reverse_chronological_order() {
        var now = Instant.now();
        var instantSource = new AdjustableInstantSource(now);
        var socialNetwork = new SocialNetwork(new InMemoryUserRepository(), instantSource);
        var firstMessage = "Damn! We lost!";
        var secondMessage = "Good game though.";

        socialNetwork.post("Bob", firstMessage);
        instantSource.advanceBy(Duration.ofMinutes(1));
        socialNetwork.post("Bob", secondMessage);

        instantSource.advanceBy(Duration.ofMinutes(1));
        var expectedFirstPost = new Post(firstMessage, now);
        var expectedSecondPost = new Post(secondMessage, now.plus(Duration.ofMinutes(1)));
        then(socialNetwork.timelineFor("Bob"))
                .containsExactly(expectedFirstPost, expectedSecondPost);
    }
}
