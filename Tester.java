import java.util.*;
import java.io.*;

public class Tester {
    public static void main(String[] args)
    {
         Store myStore = new Store();
       String output = myStore.process("trans0.txt");
       System.out.println(output);
    }
}
