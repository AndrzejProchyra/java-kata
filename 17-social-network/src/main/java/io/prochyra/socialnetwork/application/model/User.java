package io.prochyra.socialnetwork.application.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

// Comment: this aggregate could contain more of the logic
public class User {

    private final String name;
    private final List<Post> timeline;
    // ID value object for the UserId
    private final List<String> following;

    public User(String name) {
        this.name = name;
        timeline = new ArrayList<>();
        following = new ArrayList<>();
    }

    public List<Post> timeLine() {
        return unmodifiableList(timeline);
    }

    public String name() {
        return name;
    }

    public void publish(String message, Instant timestamp) {
        timeline.add(new Post(message, timestamp));
    }

    public List<String> followedUsers() {
        return List.copyOf(following);
    }

    public void follow(User otherUser) {
        following.add(otherUser.name());
    }
}
