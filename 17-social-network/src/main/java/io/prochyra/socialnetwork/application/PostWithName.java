package io.prochyra.socialnetwork.application;

import java.time.Instant;

public record PostWithName(String userName, String message, Instant timestamp) {
}
