/**
 * The Product class serves to be used by the Store class to create different products with different values for each
 */
public class Product {
    private double price;
    private double sellingPrice;
    private int quantity;

    /**
     * No argument constructor Product initializes price,sellingPrice and quantity to default values
     */
    public Product(){
        price = 5.99;
        sellingPrice = 25;
        quantity = 0;
    }
    
    /**
     * Argument constructor Product takes two doubles and an integer and initializes the values price,sellingPrice and quantity
     * @param price The double value that will be associated with the products buying price
     * @param sellingPrice The double value that will be associated with the products cost to the buyer
     * @param quantity The integer value associated with the number of avaliable products for purchase
     */
    public Product(double price, double sellingPrice,int quantity){
       
        this.price = price;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }
    
    /**
     * The method setPrice sets the price it cost the store to purchase an item
     * @param price The amount it would cost for a store to purchase a product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * The method sellingPrice sets the price of a product for purchase
     * @param sellingPrice The price it would cost a guest to buy an item
     */
    public void setsellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * The method setQuantity sets the number of products avaliable for purchase
     * @param quantity The number of products to be added to the store
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * The method getPrice returns the amount it cost the store to purchase a product
     * @return The price it cost the store to purchase a product
     */
    public double getPrice() {
        return price;
    }

    /**
     * The method getSellingPrice returns the cost for a guest to purchase the product
     * @return The selling price of the product
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     *  The method getQuality returns the number of products for sale
     * @return The number of products for sale
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return A string formatted to display the stores cost to buy the product, the number of units avaliable for sale  and the total cost for the store to purchase the items
     */
    public String toString(){
        return String.format(" Unit cost: $%.2f",price) + ", Units: " + quantity + ", Total cost: $" + String.format("%.2f", (price*quantity));
        
    }
}
