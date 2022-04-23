package domain.model;

import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import domain.exception.BasketExceedMaxQuantityException;
import domain.exception.MenuItemNotInBasketException;

public class Basket {
    private Map<UUID, BasketItem> basketItemList;
    private BasketStatus status;
    private final UUID id;
    private static final short DEFAULT_QUANTITY = 1;
    private static final int MAX_QUANTITY = 100;

    public Basket() {
        this.id = UUID.randomUUID();
        this.basketItemList = new HashMap<>();
        this.status = BasketStatus.NEW;
    }

    public Collection<BasketItem> basketItems() {
        return this.basketItemList.values();
    }

    public UUID id() {
        return id;
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

    public Basket repeat() {
        Basket newBasket = new Basket();
        List<BasketItem> newBasketItems = this.basketItemList.values().stream()
                .map(i -> new BasketItem(i.item(), i.itemQuantity()))
                .collect(Collectors.toList());
        Map<UUID, BasketItem> newList = new HashMap<>();
        newBasketItems.forEach(i -> newList.put(i.item().id(), i));
        newBasket.setBasketItemList(newList);
        return newBasket;
    }

    public double totalPrice() {
        return basketItemList.values().stream()
                .map(BasketItem::itemTotalPrice)
                .map(BigDecimal::doubleValue)
                .reduce(0.0, Double::sum);
    }

    public int totalBasketItem() {
        return this.basketItemList.size();
    }

    private Integer totalQuantity() {
        return this.basketItemList.values().stream().map(BasketItem::itemQuantity).reduce(0, Integer::sum);
    }

    private boolean basketContains(MenuItem menuItem) {
        return this.basketItemList.containsKey(menuItem.id());
    }

    public void checkout() {
        this.status = BasketStatus.CHECKED_OUT;
    }

    public boolean checkOutCompleted() {
        return this.status.equals(BasketStatus.CHECKED_OUT);
    }
}
