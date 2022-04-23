package domain.model;

import domain.exception.MenuItemQuantityReduceException;

public class BasketItem {
    private final MenuItem menuItem;
    private int quantity = 0;

    public BasketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        increaseQuantity(quantity);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int reduceQuantity(short quantity) {
        if (this.quantity < quantity)
            throw new MenuItemQuantityReduceException();
        this.quantity -= quantity;
        return this.quantity;
    }

    public MenuItem item() {
        return menuItem;
    }

    public int itemQuantity() {
        return quantity;
    }

    public double itemTotalPrice(){
        return this.menuItem.price() * this.quantity;
    }

    public Category itemCategory(){
        return menuItem.category();
    }
}
