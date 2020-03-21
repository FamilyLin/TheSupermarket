package impl;

import interfaces.Card;
import interfaces.Customer;
import interfaces.ShoppingCart;

public class CashCard implements Card {
    //1:1抵扣现金的点数
    private double point;
    public CashCard(double point){
        this.point = point;
    }

    public double processCardDiscount(double totalCost, double totalCostAfterDiscount,
                                      Customer customer, ShoppingCart shoppingCart){
        if (totalCostAfterDiscount < point){
            point -= totalCostAfterDiscount;
            return totalCostAfterDiscount;
        }else{
            point = 0;
            return point;
        }
    }
}
