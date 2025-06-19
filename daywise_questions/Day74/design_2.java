/*You are building an E-Commerce Product Insights Engine for a marketplace like 
Amazon or Flipkart. The platform stores information about various products, 
their pricing history, and user ratings.

Your job is to:

    1. Accept data for multiple products.
    
    2. Each product has:
        🎯 Multiple price entries (date + price)
        🎯 Multiple ratings (user + stars out of 5)
        
    3. Calculate:
        🎯  Average price of the product
        🎯 Price volatility score: Standard deviation of prices
        🎯 Average rating

    4. Classify products into Insight Tiers:
        🟢 Stable & Loved: Volatility < 100 and Rating ≥ 4.0
        🟡 Unstable but Popular: Volatility ≥ 100 and Rating ≥ 4.0
        🔴 Unstable & Poorly Rated: Volatility ≥ 100 and Rating < 4.0
        ⚪ Stable but Low-Rated: Volatility < 100 and Rating < 4.0
        
Sample Input:
-------------
2               
EchoDot
3
2024-06-01 3499
2024-06-10 3299
2024-06-15 3599
2 
Alice 5
Bob 4
OldTV         
4          
2024-05-01 9999
2024-05-10 10999
2024-05-15 11999
2024-05-20 8999
3
Charlie 2
Diana 3
Eve 1

Sample Output:
--------------
Product: EchoDot, AvgPrice: 3465.7, Volatility: 124.7, AvgRating: 4.5, Tier: Unstable but Popular
Product: OldTV, AvgPrice: 10499.0, Volatility: 1118.0, AvgRating: 2.0, Tier: Unstable & Poorly Rated

 */
import java.util.*;
public class design_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            int m = Integer.parseInt(sc.nextLine());

            List<PriceEntry> prices = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                String[] parts = sc.nextLine().split(" ");
                prices.add(new PriceEntry(parts[0], Double.parseDouble(parts[1])));
            }

            int k = Integer.parseInt(sc.nextLine());
            List<Rating> ratings = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                String[] parts = sc.nextLine().split(" ");
                ratings.add(new Rating(parts[0], Integer.parseInt(parts[1])));
            }

            products.add(new Product(name, prices, ratings));
        }

        InsightEngine engine = new InsightEngineImpl();
        // System.out.println("=== Product Insights Summary ===");
        for (Product p : products) {
            ProductInsight insight = engine.analyze(p);
            System.out.println(insight);
        }
        sc.close();
    }
}

class PriceEntry {
    // String date; double amount
    private String date;
    private double amount;
    PriceEntry(String date, double amount){
        setDate(date);
        setAmount(amount);
    }
    private void setDate(String date){
        this.date = date;
    }
    private void setAmount(double amount){
        this.amount = amount;
    }
    public String getDate(){
        return this.date;
    }
    public double getAmount(){
        return this.amount;
    }
    public String toString(){
        return "date: "+this.date+", amount: "+this.amount;
    }
    
}

class Rating {
    // String userName; int stars
    private String userName;
    private int stars;
    Rating(String userName,int stars){
        setUserName(userName);
        setStars(stars);
    }
    private void setStars(int stars){
        this.stars = stars;
    }
    private void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public int getStars(){
        return this.stars;
    }
    public String toString(){
        return this.userName+" - "+this.stars;
    }
}

class Product {
    // String name; List<PriceEntry>; List<Rating>
    private String name;
    private List<PriceEntry> prices;
    private List<Rating> ratings;
    Product(String name,List<PriceEntry> prices,List<Rating> ratings){
        setName(name);
        setPrices(prices);
        setRatings(ratings);
    }
    private void setName(String name){
        this.name = name;
    }
    private void setPrices(List<PriceEntry> prices){
        this.prices = prices;
    }
    private void setRatings(List<Rating> ratings){
        this.ratings = ratings;
    }
    
    public String getName(){
        return this.name;
    }
    public List<PriceEntry> getPrices(){
        return this.prices;
    }
    public List<Rating> getRatings(){
        return this.ratings;
    }
}

class ProductInsight {
    // Product; double avgPrice; double volatility; double avgRating; String insightTier
    private Product product;
    private double avgPrice, volatility, avgRatings;
    private String insightTier;
    ProductInsight(Product product, double avgPrice, double volatility, double avgRatings,String insightTier){
        setProduct(product);
        setAvgPrice(avgPrice);
        setVolatility(volatility);
        setAvgRatings(avgRatings);
        setInsightTier(insightTier);
    }
    public void setProduct(Product product){
        this.product = product;
    }
    public void setAvgPrice(double avgPrice){
        this.avgPrice = avgPrice;
    }
    public void setVolatility(double volatility){
        this.volatility = volatility;
    }
    public void setAvgRatings(double avgRatings){
        this.avgRatings = avgRatings;
    }
    public void setInsightTier(String insightTier){
        this.insightTier = insightTier;
    }
    // Override toString() for output
    public String toString(){
        return "Product: "+product.getName()+", AvgPrice: "+String.format("%.1f",this.avgPrice)+", Volatility: "+String.format("%.1f",this.volatility)+", AvgRating: "+String.format("%.1f",this.avgRatings)+", Tier: "+this.insightTier;
    }
}

interface InsightEngine {
    ProductInsight analyze(Product p);
}



class InsightEngineImpl implements InsightEngine {
    public ProductInsight analyze(Product p) {
        // Logic:
        double avgPrice = 0.0;
        double avgRating = 0.0;
        double volatility = 0.0;
        for(PriceEntry price:p.getPrices()){
            avgPrice += price.getAmount();
        }
        avgPrice = avgPrice/p.getPrices().size();
        
        for(Rating rating:p.getRatings()){
            avgRating += rating.getStars();
        }
        avgRating = avgRating/p.getRatings().size();
        
        double sum = 0.0;
        for(PriceEntry price:p.getPrices()){
            sum += Math.pow((price.getAmount()-avgPrice),2);
        }
        volatility = Math.sqrt(sum/p.getPrices().size());
        
        String insightTier = "";
        if(volatility < 100 && avgRating >= 4.0){
            insightTier = "Stable & Loved";
        }else if(volatility >= 100 && avgRating >= 4.0){
            insightTier = "Unstable but Popular";
        }else if(volatility >= 100 && avgRating < 4.0){
            insightTier = "Unstable & Poorly Rated";
        }else if(volatility < 100 && avgRating < 4.0){
            insightTier = "Stable but Low-Rated";
        }
        return new ProductInsight(p,avgPrice,volatility,avgRating,insightTier);
    }
}
