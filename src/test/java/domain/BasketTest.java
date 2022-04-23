package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    void testAddItemWithPrice() {
        MenuItem item = new MenuItem("Sea Food salad", 12.0);
        assertEquals(item.id(), basket.add(item));
    }

    @Test
    void testAddThreeChocolateIcecream() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        assertEquals(item.id(), basket.addWithQuantity(item, 3));
        assertEquals(12.0, basket.totalPrice());
    }

    @Test
    void testDuplicateBasket() {
        MenuItem item = new MenuItem("Chocolate ice cream", 4.0);
        basket.addWithQuantity(item, 3);
        Basket newBasket = basket.repeat();
        assertNotEquals(basket.id(), newBasket.id());
    }
}