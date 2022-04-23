package persistance;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import domain.model.Coupon;
import domain.model.CouponCode;
import domain.repository.CouponRepository;

public class SimpleCouponRepository implements CouponRepository {
    private final List<Coupon> coupons;

    public SimpleCouponRepository() {
        coupons = new ArrayList<>();
    }

    @Override
    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return this.coupons;
    }

    @Override
    public List<Coupon> getCouponsByCode(CouponCode code) {
        return this.getAllCoupon()
                .stream()
                .filter(coupon -> coupon.code().equals(code))
                .collect(Collectors.toList());
    }
}
