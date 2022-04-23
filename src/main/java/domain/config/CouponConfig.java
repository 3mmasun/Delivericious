package domain.config;

import domain.model.CouponCode;
import domain.model.MenuItemCategory;

public class CouponConfig {
    private final MenuItemCategory category;
    private final int minApplicableQuantity;
    private final CouponCode couponCode;

    public CouponConfig(MenuItemCategory category, int minApplicableQuantity, CouponCode couponCode) {
        this.category = category;
        this.minApplicableQuantity = minApplicableQuantity;
        this.couponCode = couponCode;
    }

    public MenuItemCategory getCategory() {
        return category;
    }

    public int getMinApplicableQuantity() {
        return minApplicableQuantity;
    }

    public CouponCode getCouponCode() {
        return couponCode;
    }
}
