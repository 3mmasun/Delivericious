package domain.event;

import domain.model.Basket;

public class BasketCheckoutEvent extends Event {
    private final Basket basket;

    public BasketCheckoutEvent(Basket basket) {
        super(basket);
        this.basket = basket;
    }

    @Override
    public String toMessage() {
        return String.format("Checkout completed for basket %s", basket.uuid());
    }
}
