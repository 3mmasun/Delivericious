package domain.model;

import java.math.BigDecimal;

public class MenuItem extends DelivericiousEntity {
    private final String name;
    private Money money;
    private MenuItemCategory category;

    public MenuItem(String name) {
        super();
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

    public MenuItemCategory category() {
        return category;
    }
}
