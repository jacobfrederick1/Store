import java.util.*;
import java.io.*;

public class Store {
    Map<String, Product> products;
    double profit = 0;
    double cost = 0;

    public Store() {
        products = new HashMap<>();
    }

    public String process(String dataFile) {
        String statements = "";
        try {

            File file = new File(dataFile);
            Scanner infile = new Scanner(file);

            while (infile.hasNext()) {
                String process = infile.next();
                if(!process.equals("*")){
                    if(!process.equals("report")){
                        String item = infile.next();
                        if (process.equals("new")) {
                            if (!products.containsKey(item)) {
                                products.put(item, new Product(Double.parseDouble(infile.next()), Double.parseDouble(infile.next()), 0));
                                statements += item + " added to inventory\n" + "\n";
                            } 
                            else {
                                infile.next();
                                infile.next();
                                statements += "ERROR: " + item + " already in inventory\n"+ "\n";
                            }
                        }
                        else if (process.equals("delete")) {
                            statements += delete(item)+ "\n";
                        }
                        else if (process.equals("buy")) {
                            statements += buy(item, Integer.parseInt(infile.next())) + "\n";
                        }
                        else if (process.equals("sell")) {
                            statements += sell(item,Integer.parseInt(infile.next()))+ "\n";
                        }
                        else if (process.equals("item")) {
                            statements += item(item)+ "\n";
                        }
                    }
                    else{
                        statements += report()+ "\n";
                    }
                }
                else{
                    break;
                }
            }
            infile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error file not found");
        }
        return statements;
    }

    public String buy(String item, int quantity) {
        if (products.containsKey(item)) {
            cost += products.get(item).getPrice() * quantity;
            products.get(item).setQuantity(products.get(item).quantity + quantity);
            return String.format(
                    products.get(item).getQuantity() + " units of " + item
                            + " added to inventory at a total cost of $%1.2f\n",
                    products.get(item).quantity * products.get(item).getPrice());
        } else {
            return "ERROR: " + item + " not in inventory \n";

        }
    }

    public String sell(String item, int sold) {
        double currentSale = 0;
        if (products.containsKey(item)) {
            if (sold <= products.get(item).quantity) {
                currentSale = products.get(item).getSellingPrice() * sold - (products.get(item).price * sold);
                profit += currentSale;
                cost -= products.get(item).price * sold;
                products.get(item).setQuantity(products.get(item).getQuantity() - sold);
                return sold + " units of " + item
                        + String.format(" sold at a total price of $%.2f", products.get(item).getSellingPrice() * sold)
                        + " for a profit of $" + String.format("%.2f", currentSale) + "\n";
            } else {
                return "ERROR: " + sold + " exceeds units of " + item + " in inventory\n";
            }
        } else {
            return "ERROR: " + item + " not in inventory \n";
        }
    }

    public String delete(String item) {
        if (products.containsKey(item)) {
            String statement = String.format(item + " removed from inventory for a total loss of $%.2f \n",
                    products.get(item).quantity * products.get(item).getPrice());

            cost -= products.get(item).quantity * products.get(item).price;
            profit -= products.get(item).quantity * products.get(item).price;

            products.remove(item);
            return statement;
        } else {
            return "ERROR: " + item + " not in inventory\n";
        }
    }

    public String item(String item) {
        if (products.containsKey(item)) {
            return "ID: " + item + "," + products.get(item).toString() + "\n";
        } else {
            return "ERROR: " + item + " not in inventory\n";

        }
    }

    public String report() {
        return String.format("Total cost: $%.2f, Total profit: $%.2f", cost, profit);

    }

}