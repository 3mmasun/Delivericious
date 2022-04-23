package domain.model;

import java.util.UUID;

public class DelivericiousEntity {
    protected UUID uuid;

    protected DelivericiousEntity() {
        this.uuid = UUID.randomUUID();
    }

    public UUID uuid() {
        return uuid;
    }
}
