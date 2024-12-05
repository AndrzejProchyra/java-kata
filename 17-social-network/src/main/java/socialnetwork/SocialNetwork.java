package socialnetwork;

import java.util.List;

public class SocialNetwork {
    private final UserRepository userRepository;

    public SocialNetwork(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> timelineFor(String alice) {
        return null;
    }

    public void post(String userName, String message) {
        userRepository.save(new User(userName));
    }
}
