package domain.model;

import java.util.UUID;
import domain.exception.MenuItemQuantityReduceException;

public class BasketItem {
    private final MenuItem menuItem;
    private int quantity = 0;

    public BasketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        increaseQuantity(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public UUID menuItemId() {
        return menuItem.id();
    }

    public int increaseQuantity(int quantity) {
        this.quantity += quantity;
        return this.quantity;
    }

    public int reduceQuantity(int quantity) {
        if (this.quantity < quantity)
            throw new MenuItemQuantityReduceException();
        this.quantity -= quantity;
        return this.quantity;
    }
}
