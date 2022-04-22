package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BasketTest {
    @Test
    void testAddTomatoSoup(){
        Basket basket = new Basket();
        MenuItem item = new MenuItem("Tomato Soup");
        BasketItem basketItem = new BasketItem(item);
        basket.add(basketItem);
        assertEquals(1, basket.totalItem());
    }

    @Test
    void testAddItemWithPrice() {
        Basket basket = new Basket();
        MenuItem item = new MenuItem("Sea Food salad", 12.0);
        BasketItem basketItem = new BasketItem(item);
        basket.add(basketItem);
        assertEquals(1, basket.totalItem());
    }

    @Test
    void testAddThreeChocolateIcecream() {
        Basket basket = new Basket();
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        BasketItem basketItem = new BasketItem(item, 3);
        basket.add(basketItem);
        assertEquals(3, basket.totalItem());
    }
}