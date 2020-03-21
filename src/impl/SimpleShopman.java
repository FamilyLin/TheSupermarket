package impl;

import interfaces.*;

import static util.ShoppingUtil.output;

public class SimpleShopman implements Shopman {
    private SuperMarket superMarket;
    public SimpleShopman(SuperMarket superMarket){
        this.superMarket = superMarket;
    }

    private static final int MAX_BUY_DEFAULT = 9;

    public void serveCustomer(Customer customer){
        int maxTypeToBuy = MAX_BUY_DEFAULT;
        if(customer instanceof AbsCustomer){
            maxTypeToBuy = ((AbsCustomer)customer).getGuangCount();
        }
        ShoppingCart shoppingCart = new ShoppingCart(maxTypeToBuy);
        customer.startShopping();

        while((!customer.wantToCheckout())&&shoppingCart.canHold()){
            Category category = customer.chooseCategory();
            //简单的导购员，顾客说不想买就算了，不做推荐
            if(category == null){
                continue;
            }
            Merchandise[] toChoose = superMarket.getRandomMerchandiseOfCategory(category);
            for (Merchandise m:toChoose){
                if (m == null){
                    continue;
                }
                int buyCount = customer.buyMerchandise(m);
                if (buyCount>0){
                    //一个类别只买一个商品
                    shoppingCart.add(m, buyCount);
                    break;
                }
            }
        }

        double originCost = shoppingCart.calculateOriginCost();
        double finalCost = originCost;

        double saveMoney = 0;
        if (customer instanceof HasCard){
            Card card = ((HasCard)customer).getCard();
            saveMoney = card.processCardDiscount(originCost, finalCost,customer,shoppingCart);
            finalCost -= saveMoney;
        }

        double moneyEarned = customer.payFor(shoppingCart,finalCost);

        superMarket.addEarnedMoney(moneyEarned);

        output("顾客"+customer.getCustId()+"购物清单如下：");
        output(shoppingCart.toString());
        output("优惠金额为："+ saveMoney);
        output("实付金额为："+ moneyEarned);
    }

    @Override
    public void seerveCustomer(Customer customer) {

    }
}
