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
        String statements = "";
        try{

            File file = new File(dataFile);
            Scanner infile = new Scanner(file);

           while(infile.hasNext()){
               String process = infile.next();
               String item = infile.next();
               if(process.equals("new")){
                   if(!products.containsKey(item)){
                    products.put(item, new Product(Double.parseDouble(infile.next()),Double.parseDouble(infile.next()),0));
                    statements +=  item + " added to inventory\n";
                   }
                   else{
                       statements += "Error: " + item + " already in inventory\n";
                   }
                }
               else if(process.equals("delete")){
                    if(products.containsKey(item)){
                        products.remove(item);
                        statements += item + " removed from inventory with a total loss of $" + Math.round(products.get(item).quantity * products.get(item).getPrice())+ "\n";
                    }
                    else{
                        statements += "Error: " + item + " not in inventory\n";
                    }
                }
               else if(process.equals("buy")){
                    if(products.containsKey(item)){
                        double quantity = Double.parseDouble(infile.next());
                        moneyOut += products.get(item).getPrice() * quantity;
                        products.get(item).setQuantity(quantity);
                        statements +=  products.get(item).getQuantity() + " added to inventory with a total cost of $" + Math.round(products.get(item).quantity*products.get(item).getPrice()) + "\n" ;
                    }
                    else{
                        statements += "Error: " + item + " not in inventory \n";
                        infile.next();
                    }
                }
               else if(process.equals("sell")){
                   double sold = Double.parseDouble(infile.next());
                   if(products.containsKey(item) && products.get(item).getQuantity() > sold ){
                        moneyIn += Math.round(products.get(item).sellingPrice*sold);
                        products.get(item).setQuantity(products.get(item).getQuantity()-sold);
                        statements += sold + " units of " + item + " sold at a total price of $" + products.get(item).getsellingPrice() +" for a profit of $" + Math.round(products.get(item).getsellingPrice() * sold) + "\n";
                    }
                    else if(products.containsKey(item) && products.get(item).getQuantity() < sold ){
                        statements += "Error: " + sold + " exceeds units of " + item + " in inventory\n";
                    }
                    else{
                        statements += "Error: " + item + " not in inventory \n";
                    }
                }
                else if(process.equals("item")){
                    if(products.containsKey(item)){
                        statements += "ID: " + item + products.get(item).toString() + "\n";
                    }
                    else{
                        statements += "Error: " + item + " not in inventory \n";
                    }
                }
                else if(process.equals("report")){
                    statements += "Total cost: $" + moneyOut + ", Total profit: $" + Math.round(moneyIn - moneyOut);
                }
                else{
                    break;
                }
            }
        infile.close();
    } 
        catch (Exception e) {
            System.out.println("Error file not found");
        }
        return statements;
    }
}