import java.util.*;
import java.io.*;

public class Store {
    Map<String, Product> products;
    double moneyIn = 0;
    double moneyOut = 0;

    public Store() {
        products = new HashMap<>();
    }

    public String process(String dataFile) {
        try {

            File file = new File(dataFile);
            Scanner infile = new Scanner(file);

            while (infile.hasNext()) {
                String process = infile.next();
                String item = infile.next();
                if (process.equals("new")) {
                    System.out.print(newItem(item, Double.parseDouble(infile.next()), Double.parseDouble(infile.next())));
                } else if (process.equals("delete")) {
                    System.out.print(delete(item));
                } else if (process.equals("buy")) {
                    System.out.print(buy(item, Integer.parseInt(infile.next())));

                } else if (process.equals("sell")) {
                    System.out.print(sell(item,Integer.parseInt(infile.next())));
                } else if (process.equals("item")) {
                    System.out.print(item(item));
                } else if (process.equals("report")) {
                    System.out.print(report());
                } else {
                    break;
                }
            }
            infile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found");
        }
        return "";
    }

    public String buy(String item, int quantity) {
        if (products.containsKey(item)) {
            moneyOut += products.get(item).getPrice() * quantity;
            products.get(item).setQuantity(quantity);
            return products.get(item).getQuantity() + " units of " + item + " added to inventory with a total cost of $"
                    + products.get(item).quantity * products.get(item).getPrice() + "\n";
        } else {
            return "Error: " + item + " not in inventory \n";

        }
    }

    public String sell(String item,int sold) {
        double currentSale = 0;
        if (products.containsKey(item) && products.get(item).getQuantity() > sold) {
            currentSale = products.get(item).sellingPrice * sold;
            moneyIn += currentSale;
            products.get(item).setQuantity(products.get(item).getQuantity() - sold);
            return sold + " units of " + item + " sold at a total price of $"
                    + products.get(item).getsellingPrice() + " for a profit of $" + currentSale + "\n";
        } else if (products.containsKey(item) && products.get(item).getQuantity() < sold) {
            return "Error: " + sold + " exceeds units of " + item + " in inventory\n";
        } else {
            return "Error: " + item + " not in inventory \n";
        }
    }

    public String delete(String item) {
        if (products.containsKey(item)) {
            String statement = item + " removed from inventory with a total loss of $"
            + products.get(item).quantity * products.get(item).getPrice() + "\n";

            products.remove(item);
            return statement;
        }
        else {
            return "ERROR: " + item + " not in inventory\n";
        }
    }

    public String item(String item) {
        if (products.containsKey(item)) {
            return "ID: " + item + products.get(item).toString() + "\n";
        } else {
            return "Error: " + item + " not in inventory \n";
        }
    }

    public String newItem(String item, Double buyPrice, Double sellPrice) {
        if (!products.containsKey(item)) {
            products.put(item, new Product(buyPrice, sellPrice, 0));
            return item + " added to inventory\n";
        } else {
            return "ERROR: " + item + " already in inventory\n";
        }
    }

    public String report() {
       return "Total cost: $" + moneyOut + ", Total profit: $" + Math.round(moneyIn - moneyOut);        
    }

}