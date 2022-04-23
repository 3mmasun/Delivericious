package domain;

public class BasketItem {
    private final MenuItem menuItem;
    private int quantity = 0;

    public BasketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        increaseBy(quantity);
    }

    public int getQuantity() {
        return quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    private void increaseBy(int quantity) {
        this.quantity += quantity;
    }
}
