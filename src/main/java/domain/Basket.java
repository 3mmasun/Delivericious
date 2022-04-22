package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

}
