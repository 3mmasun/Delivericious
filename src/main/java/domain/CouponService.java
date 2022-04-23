package domain;

import java.util.ArrayList;
import java.util.List;
import domain.config.CouponConfig;
import domain.model.*;
import domain.repository.CouponRepository;

public class CouponService {
    private final CouponRepository couponRepository;
    private final List<CouponConfig> couponConfigs;

    public CouponService(CouponRepository couponRepository,
                         List<CouponConfig> couponConfigs) {
        this.couponRepository = couponRepository;
        this.couponConfigs = couponConfigs;
    }

    public List<Coupon> suggestCoupon(Basket basket) {
        List<Coupon> result = new ArrayList<>();
        couponConfigs.forEach(config -> {
            int categoryQuantity = basket.totalQuantityByCategory(config.getCategory());
            if(categoryQuantity >= config.getMinApplicableQuantity()){
                result.addAll(couponRepository.getCouponsByCode(config.getCouponCode()));
            }
        });
        return result;
    }

}
