package domain.integration;

import java.util.UUID;

public abstract class Event {
    protected UUID uuid;

    abstract String toMessage();
}
