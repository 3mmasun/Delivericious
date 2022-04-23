package domain.event;

import java.util.UUID;

public abstract class Event {
    protected UUID uuid;

    public abstract String toMessage();
}
