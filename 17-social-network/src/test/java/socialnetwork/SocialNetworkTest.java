package socialnetwork;

import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

class SocialNetworkTest {
    @Test
    void post_creates_a_user() {
        var userRepository = mock(UserRepository.class);
        var alice = new User("Alice");
        var socialNetwork = new SocialNetwork(userRepository);

        socialNetwork.post("Alice", "any message");

        then(userRepository).should().save(alice);
    }
}