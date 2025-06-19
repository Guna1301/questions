/*Given a list of CustomerPurchase objects, each representing a purchase 
with customerId, customerName, purchaseAmount, and category. 
Compute the tier of customers based on total purchases 
(only counting purchases ≥ 500.0).

Tiers:
- Platinum ≥ 5000
- Gold ≥ 2000 and < 5000
- Silver < 2000

Note: Display the customers in descending order of their purchases of same tier.

Example 1
---------

Input:
4
C101 Alice 1200 Electronics
C102 Bob 499 Books
C101 Alice 4500 Travel
C103 Charlie 800 Furniture

Output:
C101 Alice : Platinum
C103 Charlie : Silver


Example 2
----------
Input:
8
C801 Mia 1000 Electronics
C801 Mia 1200 Furniture
C801 MIA 3000 Lighting
C802 Olivia 1001 Apparel
C803 Emma 1999 Jewelry
C803 Emma 1 Books
C804 Ava 2000 Appliances
C805 Mia 1000 Garden

Output:
C801 Mia : Platinum
C804 Ava : Gold
C803 Emma : Silver
C802 Olivia : Silver
C805 Mia : Silver
 */
// TODO: do it

import java.util.*;
class Customer{
    private String customerId, customerName,category;
    private double purchaseAmount;
    private String tire;
    Customer(String customerId, String customerName, String category,double purchaseAmount){
        setCustomerId(customerId);
        setCustomerName(customerName);
        setCategory(category);
        setPurchaseAmount(purchaseAmount);
    }
    private void setCustomerId(String customerId){
        this.customerId = customerId;
    }
    private void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    private void setCategory(String category){
        this.category = category;
    }
    private void setPurchaseAmount(double purchaseAmount){
        this.purchaseAmount = purchaseAmount;
    }
    public void setTire(String tire){
        this.tire = tire;
    }
    public double getPurchaseAmount(){
        return this.purchaseAmount;
    }
    
    public String toString(){
        return this.customerId+" "+this.customerName+" : "+this.tire;
    }
}

public class stream{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<Customer> customers = new ArrayList<>();
        for(int i=0;i<n;i++){
            String details[] = sc.nextLine().split(" ");
            customers.add(new Customer(details[0],details[1],details[3],Integer.parseInt(details[2])));
        }
        find(customers);
        sc.close();
    }

    public static void find(List<Customer> customers){
        customers.stream().filter(customer -> customer.getPurchaseAmount()>500)
        .forEach(c->{
            double amount = c.getPurchaseAmount();
            if(amount>=5000){
                c.setTire("Platinum");
            }else if(amount>=2000 && amount<5000){
                c.setTire("Gold");
            }else{
                c.setTire("Silver");
            };
            System.out.println(c.toString());
        });
    }
}