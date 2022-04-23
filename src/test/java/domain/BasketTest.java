package domain;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;
import domain.exception.BasketExceedMaxQuantityException;
import domain.integration.EventPublisher;
import domain.integration.Publisher;
import domain.model.*;
import persistance.BasketHashMapRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasketTest {
    private Basket basket;

    @BeforeEach
    void setup(){
        basket = new Basket();
    }

    @Test
    void testAddTomatoSoup(){
        MenuItem item = new MenuItem("Tomato Soup");
        assertEquals(item.id(), basket.add(item));
    }

    @Test
    void testAddItemWithPriceAndCategory() {
        MenuItem item = seaFoodSalad();
        assertEquals(item.id(), basket.add(item));
    }

    @Test
    void testAddThreeChocolateIcecream() {
        MenuItem item = chocolateIceCream();
        assertEquals(item.id(), basket.addWithQuantity(item, 3));
        assertEquals(12.0, basket.totalPrice());
    }

    @Test
    void testRemove() {
        MenuItem item = chocolateIceCream();
        basket.addWithQuantity(item, 3);
        basket.remove(item);
        basket.remove(item);
        basket.remove(item);
        assertEquals(0, basket.totalBasketItem());
    }

    @Test
    void testDuplicateBasket() {
        basket.addWithQuantity(chocolateIceCream(), 3);
        Basket newBasket = basket.repeat();
        assertNotEquals(basket.id(), newBasket.id());
        assertEquals(basket.totalPrice(), newBasket.totalPrice());
    }

    @Test
    void testMaxBasketItemQuatity100() {
        MenuItem item = chocolateIceCream();
        basket.addWithQuantity(item, 100);
        assertThrows(BasketExceedMaxQuantityException.class, ()->basket.addWithQuantity(item, 1));
    }

    @Test
    void testSaveBasket() {
        basket.addWithQuantity(chocolateIceCream(), 100);
        BasketHashMapRepository basketHashMapRepository = new BasketHashMapRepository();
        basketHashMapRepository.save(basket);
        assertEquals(basket.id(), basketHashMapRepository.getBasket(basket.id()).id());
    }

    @Test
    void testSuggestCouponForSoupCategory(){
        basket.addWithQuantity(seaFoodSalad(), 5);
        Coupon coupon1 = new Coupon("DELIVERICIOUS_10", 10.0);
        CouponService couponService = new CouponService(List.of(coupon1));
        assertTrue(couponService.suggestCoupon(basket).contains(coupon1));
    }

    @Test
    void testCheckoutBasket() {
        basket.addWithQuantity(seaFoodSalad(), 5);
        Publisher publisher = new EventPublisher();
        CheckoutService checkoutService = new CheckoutService(publisher, new PaymentService());
        checkoutService.checkout(basket);
        assertTrue(basket.checkOutCompleted());
    }

    private MenuItem chocolateIceCream() {
        return new MenuItem("Chocolate ice cream", BigDecimal.valueOf(4.0));
    }

    private MenuItem seaFoodSalad() {
        return new MenuItem("Sea Food salad", BigDecimal.valueOf(12.0), MenuItemCategory.SOUP);
    }
}