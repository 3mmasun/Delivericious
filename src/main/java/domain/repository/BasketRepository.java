package domain.repository;

import java.util.HashMap;
import java.util.UUID;
import domain.Basket;

public class BasketRepository {
    private final HashMap<UUID, Basket> baskets;

    public BasketRepository() {
        this.baskets = new HashMap<>();
    }

    public void save(Basket basket) {
        this.baskets.put(basket.id(), basket);
    }

    public Basket retrieve(UUID id) {
        return this.baskets.get(id);
    }
}
