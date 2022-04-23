package domain.integration;

import domain.model.Basket;

public class BasketCheckoutEvent extends Event {
    private final Basket basket;

    public BasketCheckoutEvent(Basket basket) {
        this.uuid = basket.id();
        this.basket = basket;
    }

    @Override
    String toMessage() {
        return String.format("Checkout completed for basket %s", basket.id());
    }
}
