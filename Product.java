public class Product {
    double price;
    double sellingPrice;
    double quantity;

    public Product(){
        price = 5.99;
        sellingPrice = 25;
        quantity = 0;
    }
    
    public Product(double price, double sellingPrice,double quantity){
       
        this.price = price;
        this.sellingPrice = sellingPrice;
        this.quantity = quantity;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public void setsellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public void setQuantity(double quantity) {
        this.quantity += quantity;
    }
    public double getPrice() {
        return price;
    }
    public double getsellingPrice() {
        return sellingPrice;
    }
    public double getQuantity() {
        return quantity;
    }
}
