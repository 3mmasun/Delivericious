package domain.model;

public class Coupon {
    private final CouponCode code;
    private final double discount;

    public Coupon(CouponCode code, double discount) {
        this.code = code;
        this.discount = discount;
    }

    public double discount() {
        return this.discount;
    }

    public CouponCode code() {
        return code;
    }
}
