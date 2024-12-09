package io.prochyra.socialnetwork;

import java.time.Instant;
import java.time.InstantSource;
import java.time.Duration;

public class AdjustableInstantSource implements InstantSource {
    private Instant currentInstant;

    public AdjustableInstantSource(Instant initialInstant) {
        this.currentInstant = initialInstant;
    }

    @Override
    public Instant instant() {
        return currentInstant;
    }

    public void advanceBy(Duration duration) {
        currentInstant = currentInstant.plus(duration);
    }
}