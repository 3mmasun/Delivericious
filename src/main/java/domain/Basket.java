package domain;

import java.util.*;

public class Basket {
    private List<BasketItem> basketItems;
    private double totalPrice;

    public Basket() {
        this.basketItems = new ArrayList<>();
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public List<BasketItem> basketItems() {
        return basketItems;
    }

    public void add(BasketItem item) {
        this.basketItems.add(item);
        this.totalPrice = basketItems.stream()
                .map(basketItem -> basketItem.getMenuItem().getPrice() * basketItem.getQuantity())
                .reduce(0.0, Double::sum);
    }

    public int totalItem(){
        return this.basketItems.stream()
                .map(item->item.getQuantity())
                .reduce(0, Integer::sum);
    }

    public Basket duplicate() {
        Basket newBasket = new Basket();
        List<BasketItem> items = new ArrayList<>();
        for (BasketItem item: basketItems){
            BasketItem newItem = new BasketItem(item.getMenuItem(), item.getQuantity());
            items.add(newItem);
        }
        newBasket.setBasketItems(items);
        return newBasket;
    }

    public double totalPrice() {
        return this.totalPrice;
    }
}
