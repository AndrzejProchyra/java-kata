package io.prochyra.socialnetwork.application.model;

import java.time.Instant;

public record Post(String message, Instant timestamp) {
}
