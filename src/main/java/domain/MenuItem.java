package domain;

import java.util.UUID;

public class MenuItem {
    private String name;
    private double price;
    private final UUID id;

    public MenuItem() {
        this.id = UUID.randomUUID();
    }

    public MenuItem(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public MenuItem(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public UUID id() {
        return id;
    }
}
