package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.application.model.UserRepository;

import java.time.InstantSource;
import java.util.List;

public class SocialNetwork {
    private final UserRepository userRepository;
    private final InstantSource instantSource;

    public SocialNetwork(UserRepository userRepository, InstantSource instantSource) {
        this.userRepository = userRepository;
        this.instantSource = instantSource;
    }

    public List<Post> timelineFor(String userName) {
        return userRepository.findByName(userName).get().timeLine();
    }

    public void post(String userName, String message) {
        var user = userRepository.findByName(userName)
                .orElseGet(() -> new User(userName));
        user.publish(message, instantSource.instant());
        userRepository.save(user);
    }
}
