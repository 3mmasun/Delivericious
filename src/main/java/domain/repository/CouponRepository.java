package domain.repository;

import java.util.List;
import domain.model.Coupon;
import domain.model.CouponCode;

public interface CouponRepository {
    void addCoupon(Coupon coupon);

    List<Coupon> getAllCoupon();

    List<Coupon> getCouponsByCode(CouponCode code);
}
