package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.application.model.UserRepository;

import java.time.InstantSource;
import java.util.Comparator;
import java.util.List;

public class SocialNetwork {
    private final UserRepository userRepository;
    private final InstantSource instantSource;

    public SocialNetwork(UserRepository userRepository, InstantSource instantSource) {
        this.userRepository = userRepository;
        this.instantSource = instantSource;
    }

    public List<Post> timelineFor(String userName) {
        return userRepository.findByName(userName).get()
                .timeLine()
                .stream()
                .sorted(Comparator.comparing(Post::timestamp).reversed())
                .toList();
    }

    public void post(String userName, String message) {
        var user = userRepository.findByName(userName)
                .orElseGet(() -> new User(userName));
        user.publish(message, instantSource.instant());
        userRepository.save(user);
    }

    public void follow(String sourceUser, String targetUser) {

    }

    public List<Post> wallFor(String userName) {
        return null;
    }
}
