package socialnetwork;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class AcceptanceTest {

    @Test
    void given_Alice_posts_a_message_then_she_can_see_it_on_her_timeline() {
        var socialNetwork = new SocialNetwork(new UserRepository());
        var message = "I love the weather today";

        socialNetwork.post("Alice", message);

        then(socialNetwork.timelineFor("Alice"))
                .contains(message);
    }
}
