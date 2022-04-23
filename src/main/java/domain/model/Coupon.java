package domain.model;

public class Coupon {
    private final String code;
    private final double discount;

    public Coupon(String code, double discount) {
        this.code = code;
        this.discount = discount;
    }

    public double discount() {
        return discount;
    }
}
