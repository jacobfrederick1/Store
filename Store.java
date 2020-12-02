//imports for file reading and exception handling
import java.util.*;
import java.io.*;

/**
 * Public class Store creates and Initializes a hashmap of key value pairs with the item name being the key and a Product object being the value.
 * The Store class reads in action, items and floating point numbers where it will test the input action to different methods to perform a specific action
 * while also using the item to test if it is already in the store. Lastly the class will return a string of outputs that are a result of the information read 
 * from the file.
 */
public class Store {
    Map<String, Product> products;
    double profit = 0;
    double cost = 0;

    /**
     * Default constructor to initialize the HashMap products
     */
    public Store() {
        products = new HashMap<>();
    }

    /**
     * The method process reads in a file taking in the action needed to be performed, the item name, and some floating point numbers if applicable
     * The method first uses a try catch statement to determine if the file can even be found, if it is found then the program test the action in the file
     * to a number of different possible outcomes and when it finds a match the program will call the associated method for which the input equals
     * @param dataFile The name of the file that will be read in by the store
     * @return A string with all of the outcomes of the information from the file
     */
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
                                statements += item + " added to inventory\n";
                            } 
                            else {
                                infile.next();
                                infile.next();
                                statements += "ERROR: " + item + " already in inventory\n";
                            }
                        }
                        else if (process.equals("delete")) {
                            statements += delete(item);
                        }
                        else if (process.equals("buy")) {
                            statements += buy(item, Integer.parseInt(infile.next()));
                        }
                        else if (process.equals("sell")) {
                            statements += sell(item,Integer.parseInt(infile.next()));
                        }
                        else if (process.equals("item")) {
                            statements += item(item);
                        }
                    }
                    else{
                        statements += report();
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

    /**
     * The buy method takes in a String and an integer, it will compare the string to keys in the Hashmap and if it finds a match then it will access
     * the quntity method in the Product class to increase the quantity by the integer value.
     * @param item The item name used to access the value in the HashMap
     * @param quantity The number of items that the store is trying to purchase
     * @return A string that is determined by the information provided, if item matches an item in the hashmap the store will purchase the quantity else it wont
     */
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

    /**
     * The sell method takes a string and an integer, it will test the string against keys in the Hashmap and if it finds a match it will test the number integer
     * to the number of items in the store and depending on the result of each it will return a string detailing the outcome.
     * @param item The item that is to be looked for in the HashMap
     * @param sold The number of items a customer is wishing to purchase
     * @return A string outlining the purchase with the number of units sold with the sales price and the profit, or an error message depending on if there is enought items in the store or if the product was even in the store
     */
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
                        + " for a profit of $" + String.format("%.2f", currentSale)+"\n";
            } else {
                return "ERROR: " + sold + " exceeds units of " + item + " in inventory\n";
            }
        } else {
            return "ERROR: " + item + " not in inventory \n";
        }
    }

    /**
     * The delete method takes a string and searched for the key in the HashMap, if it find a match then it will remove the key and
     * the values from the HashMap
     * @param item The String that is to be searched for in the HashMap
     * @return A string detailing the removal of the item or an error message because the item did not exist.
     */
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

    /**
     * The item method takes a String and test if it matches any keys in the HashMap and if it does then
     * it will return a detailed string with the item's name and the products information
     * @param item The string to be looked for in the HashMap
     * @return A String with either an error message or a string with all of the items information
     */
    public String item(String item) {
        if (products.containsKey(item)) {
            return "ID: " + item + "," + products.get(item).toString()+"\n";
        } else {
            return "ERROR: " + item + " not in inventory\n";

        }
    }

    /**
     * The report method returns a formated string detailing the cost and profit of the store
     * @return
     */
    public String report() {
        return String.format("Total cost: $%.2f, Total profit: $%.2f", cost, profit);

    }

}