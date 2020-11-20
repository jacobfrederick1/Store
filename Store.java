import java.util.*;
import java.io.*;

public class Store {
    Map<String, Product> products;

    public Store() {
        products = new HashMap<>();
    }

    public String process(String dataFile){
        double moneyIn = 0;
        double moneyOut = 0;
        String statements = " ";
        try{

            File file = new File(dataFile);
            Scanner infile = new Scanner(file);

           while(infile.hasNext()){
               String process = infile.next();
               String item = infile.next();

               if(process.equals("new")){
                   if(!products.containsKey(item)){
                    products.put(item, new Product(Double.parseDouble(infile.next()),Double.parseDouble(infile.next()),0));
                   }
                   else{
                       statements += "Error Product already in Store\n";
                   }
                }
               else if(process.equals("delete")){
                    if(products.containsKey(item)){
                        products.remove(item);
                    }
                    else{
                        statements += "Error product could not be removed because it does not exist\n";
                    }
                }
               else if(process.equals("buy")){
                    if(products.containsKey(item)){
                        double quantity = Double.parseDouble(infile.next());
                        moneyOut += products.get(item).getPrice() * quantity;
                        products.get(item).setQuantity(quantity);
                    }
                    statements += "item not in inventory";
                }
               else if(process.equals("sell")){
                   double sold = Double.parseDouble(infile.next());
                   if(products.containsKey(item) && products.get(item).getQuantity() > sold ){
                        moneyIn += products.get(item).sellingPrice*sold;
                        products.get(item).setQuantity(products.get(item).getQuantity()-sold);
                    }
                    else if(products.containsKey(item) && products.get(item).getQuantity() < sold ){
                        System.out.print("The amount sold is more than the store carries");
                    }
                    else{
                        System.out.print("The product is not avaliable at the store");
                    }
                }
                else if(process.equals("item")){
                    if(products.containsKey(item)){
                        products.get(item).toString();
                    }
                    else{
                        System.out.print("Product not avaliable in store");
                    }
                }
                else if(process.equals("report")){
                    System.out.print("Total Cost: $" + moneyOut + ", Total Profit: $" + (moneyIn-moneyOut));
                }
                break;
            }
        infile.close();
    } 
        catch (Exception e) {
            System.out.println("Error file not found");
        }
        return null;
    }
}