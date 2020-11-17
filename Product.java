public class Product {
    double price;
    int quantity;

    public Product(){
        price = 5.99;
        quantity = 25;
    }
    
    public Product(int price,int quantity){
       
        this.price = price;
        this.quantity = quantity;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
}
