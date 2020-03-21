import impl.SimpleShopman;
import interfaces.Customer;
import interfaces.Shopman;
import interfaces.SuperMarket;

import static util.ShoppingUtil.*;

public class ShoppingAppMain {
    public static void main(String[] args){
        SuperMarket superMarket = createSuperMarket();
        Shopman shopman = new SimpleShopman(superMarket);

        boolean open = true;
        while(open){
            new ShoppingTask(shopman).executeTask();
            output("是否继续营业？（yes)");
            open = !input().next().trim().equalsIgnoreCase("no");
        }
        superMarket.dailyReport();
    }
}

class ShoppingTask{
    private  Shopman shopman;
    public ShoppingTask(Shopman  shopman){
        this.shopman = shopman;
    }

    public void executeTask(){
        Customer customer = createCustomer(true);
        shopman.seerveCustomer(customer);
    }
}