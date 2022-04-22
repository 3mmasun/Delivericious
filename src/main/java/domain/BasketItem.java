package domain;

public class BasketItem {
    private MenuItem menuItem;
    private int quantity;

    public BasketItem(MenuItem menuItem) {
        this(menuItem, 1);
    }

    public BasketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
