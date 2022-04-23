package domain;

import domain.exception.BasketCheckoutException;
import domain.event.BasketCheckoutEvent;
import domain.event.Event;
import domain.event.Publisher;
import domain.model.Basket;

public class CheckoutService {
    private final Publisher publisher;
    private final PaymentService paymentService;

    public CheckoutService(Publisher publisher,
                           PaymentService paymentService) {
        this.publisher = publisher;
        this.paymentService = paymentService;
    }

    public void checkout(Basket basket) {
        if (paymentService.pay(basket.totalPrice())) {
            basket.checkout();
            Event event = new BasketCheckoutEvent(basket);
            publisher.publish(event);
        } else
            throw new BasketCheckoutException();
    }
}
