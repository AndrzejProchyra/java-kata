package io.prochyra.socialnetwork.application;

import java.time.Instant;

public record Post(String message, Instant timestamp) {
}
