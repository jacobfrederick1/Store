public class Product {
    double price;
    double sellingPrice;

    public Product(){
        price = 5.99;
        sellingPrice = 25;
    }
    
    public Product(double price, double d){
       
        this.price = price;
        this.sellingPrice = d;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public void setsellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public double getPrice() {
        return price;
    }
    public double getsellingPrice() {
        return sellingPrice;
    }
}
