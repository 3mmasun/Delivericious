package domain;

public class BasketItem {
    private String name;
    private double price;

    public BasketItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public BasketItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
