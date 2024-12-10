package io.prochyra.socialnetwork.application.model;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByName(String username);
}
