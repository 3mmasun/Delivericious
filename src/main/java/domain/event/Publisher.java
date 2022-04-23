package domain.event;

public interface Publisher {
    void publish(Event event);
}
