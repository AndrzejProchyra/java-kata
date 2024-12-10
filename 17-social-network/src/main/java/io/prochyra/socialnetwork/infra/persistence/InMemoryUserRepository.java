package io.prochyra.socialnetwork.infra.persistence;

import io.prochyra.socialnetwork.application.model.User;
import io.prochyra.socialnetwork.application.model.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findByName(String userName) {
        return users.stream()
                .filter(user -> user.name().equals(userName))
                .findFirst();
    }
}
