package domain.model;

public class CouponConfig {
    private final MenuItemCategory category;
    private final int minEligibleQuantity;
    private final CouponCode couponCode;

    public CouponConfig(MenuItemCategory category, int minEligibleQuantity, CouponCode couponCode) {
        this.category = category;
        this.minEligibleQuantity = minEligibleQuantity;
        this.couponCode = couponCode;
    }

    public MenuItemCategory category() {
        return category;
    }

    public int minEligibleQuantity() {
        return minEligibleQuantity;
    }

    public CouponCode couponCode() {
        return couponCode;
    }
}
