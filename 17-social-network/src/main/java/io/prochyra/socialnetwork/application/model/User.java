package io.prochyra.socialnetwork.application.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public final class User {

    private final String name;
    private final List<Post> timeline;

    public User(String name) {
        this.name = name;
        timeline = new ArrayList<>();
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
}
