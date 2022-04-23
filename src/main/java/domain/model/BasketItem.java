package domain.model;

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

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int reduceQuantity(int quantity) {
        if (this.quantity < quantity)
            throw new MenuItemQuantityReduceException();
        this.quantity -= quantity;
        return this.quantity;
    }

    public double itemTotalPrice(){
        return this.menuItem.getPrice() * this.quantity;
    }

    public Category itemCategory(){
        return menuItem.getCategory();
    }
}
