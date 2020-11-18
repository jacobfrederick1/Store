import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Scanner;

public class Store{
    Map<String, Product> products;
 
    /**
     * Default Constructor to initialize data structures
     */
    public Store(){
        products = new HashMap<>();
    }


    public String process(String dataFile){
        try {
            File file = new File(dataFile);
            Scanner infile = new Scanner(file);

           while(infile.hasNext()){
               String process = infile.next();
               String item = infile.next();

               if(process.equals("new")){
                   if(!products.containsKey(item)){
                    products.put(item, new Product(Double.parseDouble(infile.next()),Double.parseDouble(infile.next())));
                   }
                   else{
                       System.out.print("Error Product already in Store");
                   }
               }
               else if(process.equals("delete")){
                        if(products.containsKey(item)){
                            products.remove(item);
                        }
                        else{
                            System.out.print("Error product could not be removed because it does not exist");
                        }
               }
           }
            
            infile.close();
        } catch (Exception e) {
            System.out.println("Error file not found");

        }
        return null;
    }
    

}