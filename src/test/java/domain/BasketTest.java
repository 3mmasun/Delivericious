package domain;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;
import domain.model.CouponConfig;
import domain.exception.BasketExceedMaxQuantityException;
import domain.repository.CouponRepository;
import integration.EventPublisher;
import domain.event.Publisher;
import domain.model.*;
import persistance.SimpleBasketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistance.SimpleCouponRepository;

class BasketTest {
    private Basket basket;

    @BeforeEach
    void setup(){
        basket = new Basket();
    }

    @Test
    void testAddTomatoSoup(){
        MenuItem item = new MenuItem("Tomato Soup");
        assertEquals(item.uuid(), basket.add(item));
    }

    @Test
    void testAddItemWithPriceAndCategory() {
        MenuItem item = seaFoodSalad();
        assertEquals(item.uuid(), basket.add(item));
    }

    @Test
    void testAddThreeChocolateIcecream() {
        MenuItem item = chocolateIceCream();
        assertEquals(item.uuid(), basket.addWithQuantity(item, 3));
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
        assertNotEquals(basket.uuid(), newBasket.uuid());
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
        SimpleBasketRepository simpleBasketRepository = new SimpleBasketRepository();
        simpleBasketRepository.save(basket);
        assertEquals(basket.uuid(), simpleBasketRepository.getBasket(basket.uuid()).uuid());
    }

    @Test
    void testSuggestCouponForSoupCategory(){
        basket.addWithQuantity(seaFoodSalad(), 5);
        CouponRepository couponRepository = new SimpleCouponRepository();
        CouponConfig soupCouponConfig = new CouponConfig(MenuItemCategory.SOUP, 5, CouponCode.DELIVERICIOUS_10);
        CouponService couponService = new CouponService(couponRepository, List.of(soupCouponConfig));

        Coupon coupon1 = new Coupon(CouponCode.DELIVERICIOUS_10, 10.0);
        couponRepository.addCoupon(coupon1);

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