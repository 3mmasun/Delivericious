package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import domain.model.Basket;
import domain.model.BasketItem;
import domain.model.MenuItemCategory;
import domain.model.Coupon;

public class CouponService {
    private final List<Coupon> coupons;
    private static final int SOUP_COUPON_MIN_QUANTITY = 5;

    public CouponService(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public List<Coupon> suggestCoupon(Basket basket) {
        int soupItemQuantity = basket.basketItems().stream()
                .filter(basketItem -> basketItem.itemCategory().equals(MenuItemCategory.SOUP))
                .map(BasketItem::itemQuantity)
                .reduce(0, Integer::sum);
        if (soupItemQuantity >= SOUP_COUPON_MIN_QUANTITY) {
            return getCouponByDiscount(10.0);
        }
        return Collections.emptyList();
    }

    private List<Coupon> getCouponByDiscount(double discount) {
        return this.coupons.stream().filter(coupon -> coupon.discount() == discount)
                .collect(Collectors.toList());
    }

}
