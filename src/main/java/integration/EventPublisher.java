package integration;

import domain.event.Event;
import domain.event.Publisher;

public class EventPublisher implements Publisher {
    private static EventPublisher instance;

    public static EventPublisher getInstance() {
        if(instance == null)
            instance = new EventPublisher();
        return instance;
    }

    @Override
    public void publish(Event event) {
        System.out.println(event.toMessage());
    }
}
