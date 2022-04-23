package domain;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import domain.exception.BasketExceedMaxQuantityException;
import domain.model.Basket;
import domain.model.Category;
import domain.model.Coupon;
import domain.model.MenuItem;
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
        MenuItem item = new MenuItem("Sea Food salad", 12.0, Category.SOUP);
        assertEquals(item.id(), basket.add(item));
    }

    @Test
    void testAddThreeChocolateIcecream() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        assertEquals(item.id(), basket.addWithQuantity(item, 3));
        assertEquals(12.0, basket.totalPrice());
    }

    @Test
    void testRemove() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        basket.addWithQuantity(item, 3);
        basket.remove(item);
        basket.remove(item);
        basket.remove(item);
        assertEquals(0, basket.totalBasketItem());
    }

    @Test
    void testDuplicateBasket() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        basket.addWithQuantity(item, 3);
        Basket newBasket = basket.repeat();
        assertNotEquals(basket.id(), newBasket.id());
        assertEquals(basket.totalPrice(), newBasket.totalPrice());
    }

    @Test
    void testMaxBasketItemQuatity100() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        basket.addWithQuantity(item, 100);
        assertThrows(BasketExceedMaxQuantityException.class, ()->basket.addWithQuantity(item, 1));
    }

    @Test
    void testSaveBasket() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        basket.addWithQuantity(item, 100);
        BasketHashMapRepository basketHashMapRepository = new BasketHashMapRepository();
        basketHashMapRepository.save(basket);
        assertEquals(basket.id(), basketHashMapRepository.getBasket(basket.id()).id());
    }

    @Test
    void testSuggestCouponForSoupCategory(){
        MenuItem item = new MenuItem("Sea Food salad", 12.0, Category.SOUP);
        basket.addWithQuantity(item, 5);
        Coupon coupon1 = new Coupon("DELIVERICIOUS_10", 10.0);
        CouponService couponService = new CouponService(List.of(coupon1));
        assertTrue(couponService.suggestCoupon(basket).contains(coupon1));
    }

    @Test
    void testCheckoutBasket() {
        basket.checkout();
        assertTrue(basket.checkOutCompleted());
    }
}