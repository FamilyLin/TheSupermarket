package interfaces;


/**
 * VIP卡，打折卡，赠品卡等
 */
public interface Card {
    /**
     * 执行完毕超市自身的打折策略之后，确定顾客需要付多少钱
     * 然后再根据顾客是否有VIP卡，判断是否继续打折，看顾客是否有现金卡，抵扣现金
     *
     *
     *
     */
    double processCardDiscount(double totalCost, double totalCostAfterDiscount,
                               Customer customer, ShoppingCart shoppingCart);
}
