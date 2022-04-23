package domain;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import domain.exception.BasketExceedMaxQuantityException;
import domain.exception.MenuItemNotInBasketException;

public class Basket {
    private Map<UUID, BasketItem> basketItemList;
    private final UUID id;
    private static final int DEFAULT_QUANTITY = 1;
    private static final int MAX_QUANTITY = 100;

    public UUID id() {
        return id;
    }

    public Basket() {
        this.id = UUID.randomUUID();
        this.basketItemList = new HashMap<>();
    }

    public void setBasketItemList(Map<UUID, BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
    }

    public UUID add(MenuItem menuItem) {
        return addWithQuantity(menuItem, DEFAULT_QUANTITY);
    }

    public UUID addWithQuantity(MenuItem menuItem, int quantity) {
        if (totalQuantity() == MAX_QUANTITY)
            throw new BasketExceedMaxQuantityException();
        if (basketContains(menuItem))
            this.basketItemList.get(menuItem.id()).increaseQuantity(quantity);
        else {
            BasketItem basketItem = new BasketItem(menuItem, quantity);
            this.basketItemList.put(menuItem.id(), basketItem);
        }
        return menuItem.id();
    }

    private Integer totalQuantity() {
        return this.basketItemList.values().stream().map(BasketItem::getQuantity).reduce(0, Integer::sum);
    }

    private boolean basketContains(MenuItem menuItem) {
        return this.basketItemList.containsKey(menuItem.id());
    }

    public Basket repeat() {
        Basket newBasket = new Basket();
        List<BasketItem> newBasketItems = this.basketItemList.values().stream()
                .map(i -> new BasketItem(i.getMenuItem(), i.getQuantity()))
                .collect(Collectors.toList());
        Map<UUID, BasketItem> newList = new HashMap<>();
        newBasketItems.forEach(i -> newList.put(i.getMenuItem().id(), i));
        newBasket.setBasketItemList(newList);
        return newBasket;
    }

    public double totalPrice() {
        return basketItemList.values().stream()
                .map(basketItem -> basketItem.getMenuItem().getPrice() * basketItem.getQuantity())
                .reduce(0.0, Double::sum);
    }

    public int totalBasketItem() {
        return this.basketItemList.size();
    }

    public void remove(MenuItem menuItem) {
        if (basketContains(menuItem)) {
            int itemQuantity = this.basketItemList.get(menuItem.id()).reduceQuantity(DEFAULT_QUANTITY);
            if (itemQuantity == 0) {
                this.basketItemList.remove(menuItem.id());
            }
        }
        else
            throw new MenuItemNotInBasketException();
    }
}
