package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.application.model.UserRepository;

import java.time.InstantSource;
import java.util.List;

public class SocialNetwork {
    private final UserRepository userRepository;

    public SocialNetwork(UserRepository userRepository, InstantSource instantSource) {
        this.userRepository = userRepository;
    }

    public List<Post> timelineFor(String alice) {
        return null;
    }

    public void post(String userName, String message) {
        userRepository.save(new User(userName));
    }
}
