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


    public Process(String dataFile){
        try {
            File file = new File(dataFile);
            Scanner infile = new Scanner(file);

            while(infile.hasNext()){
                System.out.print(infile.next());
            }
        } catch (Exception e) {
            System.out.println("Error file not found");

        }
    }

}