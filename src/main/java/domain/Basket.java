package domain;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {
    private List<BasketItem> basketItems;
    private Map<UUID, BasketItem> basketItemList;
    private final UUID id;
    private static final short DEFAULT_QUANTITY = 1;

    public UUID id() {
        return id;
    }

    public Basket() {
        this.id = UUID.randomUUID();
        this.basketItems = new ArrayList<>();
        this.basketItemList = new HashMap<>();
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }

    public UUID add(MenuItem menuItem) {
        return addWithQuantity(menuItem, DEFAULT_QUANTITY);
    }

    public UUID addWithQuantity(MenuItem menuItem, int quantity) {
        BasketItem basketItem = new BasketItem(menuItem, quantity);
        this.basketItems.add(basketItem);
        return menuItem.id();
    }

    public Basket repeat() {
        Basket newBasket = new Basket();
        List<BasketItem> newBasketItems = this.basketItems.stream()
                .map(i -> new BasketItem(i.getMenuItem(), i.getQuantity()))
                .collect(Collectors.toList());
        newBasket.setBasketItems(newBasketItems);
        return newBasket;
    }

    public double totalPrice() {
        return basketItems.stream()
                .map(basketItem -> basketItem.getMenuItem().getPrice() * basketItem.getQuantity())
                .reduce(0.0, Double::sum);
    }
}
