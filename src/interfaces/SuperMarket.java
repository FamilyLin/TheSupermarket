package interfaces;

public interface SuperMarket {
    Merchandise[] getAllMerchandises();
    Merchandise[] getRandomMerchandiseOfCategory(Category category);
    void addEarnedMoney(double earnedMoney);
    void dailyReport();
}
