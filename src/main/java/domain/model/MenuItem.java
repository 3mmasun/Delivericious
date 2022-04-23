package domain.model;

import java.util.UUID;

public class MenuItem {
    private String name;
    private final UUID id;
    private Money money;
    private Category category;

    public MenuItem() {
        this.id = UUID.randomUUID();
    }

    public MenuItem(String name) {
        this();
        this.name = name;
    }

    public MenuItem(String name, double price) {
        this(name);
        this.money = new Money(Currency.SGD, price);
        this.category = Category.NOT_SPECIFIED;
    }

    public MenuItem(String name, double price, Category category) {
        this(name, price);
        this.category = category;
    }

    public double price() {
        return this.money.getAmount();
    }

    public UUID id() {
        return id;
    }

    public Category category() {
        return category;
    }
}
