package io.prochyra.socialnetwork.application;

import io.prochyra.socialnetwork.application.model.Post;
import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.application.model.UserRepository;

import java.time.InstantSource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class SocialNetwork {
    private final UserRepository userRepository;
    private final InstantSource instantSource;

    public SocialNetwork(UserRepository userRepository, InstantSource instantSource) {
        this.userRepository = userRepository;
        this.instantSource = instantSource;
    }

    public List<PostWithName> timelineFor(String userName) {
        return userRepository.findByName(userName).get()
                .timeLine()
                .stream()
                .sorted(Comparator.comparing(Post::timestamp).reversed())
                .map(post -> new PostWithName(userName, post.message(), post.timestamp()))
                .toList();
    }

    public void post(String userName, String message) {
        var user = userRepository.findByName(userName)
                .orElseGet(() -> new User(userName));
        user.publish(message, instantSource.instant());
        userRepository.save(user);
    }

    public void follow(String sourceUserName, String targetUserName) {
        var source = userRepository.findByName(sourceUserName);
        var target = userRepository.findByName(targetUserName);
        source.ifPresent(user -> user.follow(target.get()));
    }

    public List<PostWithName> wallFor(String userName) {
        var user = userRepository.findByName(userName).get();
        return Stream.concat(
                        timelineFor(userName).stream(),
                        user.followedUsers()
                                .stream()
                                .flatMap(name -> timelineFor(name).stream()))
                .sorted(Comparator.comparing(PostWithName::timestamp).reversed())
                .toList();
    }
}
