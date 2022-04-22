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
        return this.items.stream()
                .map(item->item.getQuantity())
                .reduce(0, Integer::sum);
    }

}
