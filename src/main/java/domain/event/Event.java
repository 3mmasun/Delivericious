package domain.event;

import java.util.UUID;
import domain.model.DelivericiousEntity;

public abstract class Event {
    protected UUID uuid;

    protected Event(DelivericiousEntity entity) {
        this.uuid = entity.uuid();
    }

    public abstract String toMessage();
}
