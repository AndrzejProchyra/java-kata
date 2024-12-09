package io.prochyra.socialnetwork;

import io.prochyra.socialnetwork.application.Post;
import io.prochyra.socialnetwork.application.SocialNetwork;
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
        instantSource.advanceBy(Duration.ofMinutes(5));

        var expectedPost = new Post(message, postTime.plus(Duration.ofMinutes(5)));
        then(socialNetwork.timelineFor("Alice"))
                .contains(expectedPost);
    }
}
