package subsetsum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class GroceriesFileReader {

    // TODO: Define the class GroceriesFileReader
    // TODO: Define the readFile() method which reads the CSV (Comma Seperated Value) file
    // of groceries and creates a specified ArrayList of grocery prices.
    //
    // NOTE: Catch all exceptions in the GroceriesFileReader readFile() method.
    //       That means readFile() method should not throw an exception.

    //Constructor that provides a Object for shoppingBag to create.
    public GroceriesFileReader() { }

    //read prices of the groceries.txt into ArrayList of Double
    public static ArrayList<Double> readFile(String filepath) {

        ArrayList<Double> priceslist = new ArrayList<Double>();

        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            while ((line = reader.readLine()) != null) {
                String[] lineAfterSplit = line.split(",");
                double singlePrice = Double.parseDouble(lineAfterSplit[1]);

                priceslist.add(singlePrice);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return priceslist;
    }
}

