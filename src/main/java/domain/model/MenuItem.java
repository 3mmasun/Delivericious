package domain.model;

import java.util.UUID;

public class MenuItem {
    private String name;
    private final UUID id;
    private Money money;

    public MenuItem() {
        this.id = UUID.randomUUID();
    }

    public MenuItem(String name, double price) {
        this();
        this.name = name;
        this.money = new Money(Currency.SGD, price);
    }

    public MenuItem(String name) {
        this();
        this.name = name;
    }

    public double getPrice() {
        return this.money.getAmount();
    }

    public UUID id() {
        return id;
    }
}
