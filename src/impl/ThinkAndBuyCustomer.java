package impl;

import interfaces.Card;
import interfaces.Category;
import interfaces.HasCard;
import interfaces.Merchandise;

public class ThinkAndBuyCustomer extends AbsCustomer implements HasCard {

    private Card card = VIPCard.Level0;

    public ThinkAndBuyCustomer(String custId, Category shouldBuy){
        super(custId,shouldBuy,DEFAULT_GUANG_COUNT);
    }

    @Override
    protected Category getRandomCategory() {
        return null;
    }

    @Override
    public int buyMerchandise(Merchandise merchandise) {
        Category category = merchandise.getCategory();
        if(category == getShouldBuy()){
            return 1;
        }
        double soldPrice = merchandise.getSoldPrice();

        double avgPrice = (category.getHigherPrice()+category.getLowerPrice())/2;
        if (soldPrice < avgPrice){
            return 1;
        }else {
            return 0;
        }
    }


    public void  setCard(Card card) {
        this.card = card;
    }

    public Card getCard(){
        return card;
    }
}
