package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BasketTest {
    @Test
    void testAddTomatoSoup(){
        BasketItem item = new BasketItem("Tomato Soup");
        Basket basket = new Basket();
        basket.add(item);
        assertEquals(1, basket.totalItem());
    }

    @Test
    void testAddItemWithPrice() {
        Basket basket = new Basket();
        BasketItem item = new BasketItem("Sea Food salad", 12.0);
        basket.add(item);
        assertEquals(1, basket.totalItem());
        assertEquals(12.0, basket.totalPrice());
    }

    @Test
    void testAddThreeChocolateIcecream() {
        Basket basket = new Basket();
        BasketItem item = new BasketItem("Chocolate ice cream", 4.0);
        basket.addItemWithQuantity(item, 3);
        assertEquals(3, basket.totalItem());
        assertEquals(12.0, basket.totalPrice());
    }
}