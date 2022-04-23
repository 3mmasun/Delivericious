package domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class MenuItem {
    private String name;
    private final UUID id;
    private Money money;
    private MenuItemCategory category;

    public MenuItem() {
        this.id = UUID.randomUUID();
    }

    public MenuItem(String name) {
        this();
        this.name = name;
    }

    public MenuItem(String name, BigDecimal price) {
        this(name);
        this.money = new Money(Currency.SGD, price);
        this.category = MenuItemCategory.NOT_SPECIFIED;
    }

    public MenuItem(String name, BigDecimal price, MenuItemCategory category) {
        this(name, price);
        this.category = category;
    }

    public BigDecimal price() {
        return this.money.getAmount();
    }

    public UUID id() {
        return id;
    }

    public MenuItemCategory category() {
        return category;
    }
}
