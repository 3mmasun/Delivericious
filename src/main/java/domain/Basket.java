package domain;

import java.util.*;

public class Basket {
    private List<BasketItem> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(BasketItem item) {
        this.items.add(item);
    }

    public int totalItem(){
        return this.items.size();
    }

    public double totalPrice() {
        return items.stream()
                .map(BasketItem::getPrice)
                .reduce(0.0, Double::sum);
    }

    public void addItems(List<BasketItem> items) {
        this.items.addAll(items);
    }

    public void addItemWithQuantity(BasketItem item, int quantity) {
        for (int i = 0; i < quantity; i++){
            addItems(Collections.singletonList(item));
        }
    }
}
