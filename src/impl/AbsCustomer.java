package impl;

import interfaces.Category;
import interfaces.Customer;
import interfaces.ShoppingCart;

public abstract class AbsCustomer implements Customer {
    private Category shouldBuy;
    private String custId;
    private double moneySpent;
    private int guangLeft = 0;
    private int guangCount = 0;

    public static final int DEFAULT_GUANG_COUNT = 5;

    public AbsCustomer(String custId, Category shouldBuy, int guangCount) {
        this.custId = custId;
        this.shouldBuy = shouldBuy;
        this.guangCount = guangCount;
    }

    public int getGuangCount() {
        return guangCount;
    }

    public void setGuangLeft(int guangLeft) {
        this.guangLeft = guangLeft;
    }

    public AbsCustomer(String custId, Category shouldBuy) {
        this(custId, shouldBuy, DEFAULT_GUANG_COUNT);
    }

    @Override
    public String getCustId() {
        return custId;
    }

    public void startShopping() {
        guangLeft = guangCount;
    }

    public boolean wantToCheckout() {
        guangLeft--;
        return guangLeft <= 0;
    }

    public double payFor(ShoppingCart shoppingCart, double totalCost) {
        moneySpent += totalCost;
        return totalCost;
    }

    public Category getShouldBuy() {
        return shouldBuy;
    }

    /**
     * 先看必须买的，没有必须买的就随便看看
     *
     * @return 想要购买的商品种类
     */
    public Category chooseCategory() {
        //有一次机会看需要购买的东西
        if (guangLeft + 1 >= guangCount) {
            return shouldBuy;
        } else {
            return getRandomCategory();
        }
    }

    protected abstract Category getRandomCategory();

    public double getMoneySpent() {
        return moneySpent;
    }
}
