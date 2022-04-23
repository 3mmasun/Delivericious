package persistance;

import java.util.HashMap;
import java.util.UUID;
import domain.model.Basket;
import domain.repository.BasketRepository;

public class BasketHashMapRepository implements BasketRepository {
    private final HashMap<UUID, Basket> baskets;

    public BasketHashMapRepository() {
        this.baskets = new HashMap<>();
    }

    @Override
    public void save(Basket basket) {
        this.baskets.put(basket.id(), basket);
    }

    @Override
    public Basket getBasket(UUID uuid) {
        return this.baskets.get(uuid);
    }
}
