package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> basketItems;
    private double totalPrice;
    private final UUID id;

    public UUID id() {
        return id;
    }

    public Basket() {
        this.basketItems = new ArrayList<>();
        this.id = UUID.randomUUID();
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
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

    public Basket repeat() {
        Basket newBasket = new Basket();
        List<BasketItem> newBasketItems = this.basketItems.stream()
                .map(i->new BasketItem(i.getMenuItem(), i.getQuantity()))
                .collect(Collectors.toList());
        newBasket.setBasketItems(newBasketItems);
        return newBasket;
    }

    public double totalPrice() {
        return this.totalPrice;
    }

}
