package domain.repository;

import java.util.UUID;
import domain.model.Basket;

public interface BasketRepository {
    void save(Basket basket);
    Basket getBasket(UUID uuid);
}
