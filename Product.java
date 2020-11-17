import jdk.javadoc.internal.doclets.formats.html.markup.HtmlAttr;
import jdk.javadoc.internal.tool.resources.javadoc;

public class Product {
    String itemId;
    double price;
    int quantity;

    public Product(){
        itemId = "Paper Towels";
        price = 5.99;
        quantity = 25;
    }
    
    public Product(String item,int price,int quantity){
        itemId = item;
        this.price = price;
        this.quantity = quantity;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
}
