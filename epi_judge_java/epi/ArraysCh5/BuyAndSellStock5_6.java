package epi.ArraysCh5;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock5_6 {
  // Use Math.min max, avoids if/else logic to get min/max
  // Use only required intermediate variables

  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    double maxProfit = 0;
    if(prices.size() > 0){
      double min = prices.get(0);
      for(Double price: prices){
        if(min > price){
          min = price;
        }else if(maxProfit < price-min){
          maxProfit = price - min;
        }
      }
    }
    return maxProfit;
  }






















  public static double computeMaxProfit1

          (List<Double> prices) {
    double maxProfit =0, minPrice =prices.get(0);

    for(double price : prices){
      maxProfit = Math.max(maxProfit, price - minPrice);
      minPrice = Math.min(price, minPrice);
    }
    return maxProfit;
  }

  public static double computeMaxProfitOld(List<Double> prices) {
    double buyPrice=0, sellPrice =0, profit =0, currMinPrice =0;
    currMinPrice = prices.get(0);
    for(double price: prices){
      if(price < currMinPrice){
        currMinPrice = price;
      }else {
        if(price - currMinPrice > profit){
          buyPrice = currMinPrice;
          sellPrice = price;
          profit = price -currMinPrice;
        }
      }
    }
//    System.out.println("buy:"+buyPrice+" sell:"+sellPrice+" profit:"+profit);
    return profit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
