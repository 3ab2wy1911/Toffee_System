import java.io.*;
import java.util.Vector;

public class Catalog {
    private Vector<Item> menu = new Vector<>();

    public Catalog()
    {
        retrieveData(); // Get the data from the file.
    }

    //==================================================================================================================

    public void retrieveData()  // function to get the data from the file.
    {
        menu.clear();
        String csvFilePath = "Catalog.csv";
        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Open file to read the data in it.

            String line;
            while ((line = bufferedReader.readLine()) != null) {    // Read line by line from the file till the file ends.
                String[] data = line.split(",");    // Split the file by ',';

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String type = data[2];
                double price = Double.parseDouble(data[3]);
                int quantity = Integer.parseInt(data[4]);
                // get the attributes of the item from the file.

                Item item = new Item(id, name, type,price,quantity);    // add the attributes to a new item.
                menu.add(item); // add the item to the catalog menu.

            }

            bufferedReader.close(); // Close the reader.
        }

        catch (IOException e) { // Exception if an error occurred while reading the file.
            System.out.println("An error occurred while reading the CSV file Catalog.");
            e.printStackTrace();
        }

    }

    //==================================================================================================================

    public Vector<Item> getMenu()   // get the catalog menu to be assigned to another menu.
    {
        return menu;
    }

    //==================================================================================================================

    public static void DisplayMenu()    // function that displays the catalog menu "from the file".
    {
        System.out.println("================================Catalog Menu================================");
        String csvFilePath = "Catalog.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Open the file to be read.

            String line;
            while ((line = bufferedReader.readLine()) != null) {    // read line by line from the file till the end of the file.
                String[] data = line.split(",");    // split the line by ','.

                String id = data[0];
                String name = data[1];
                String type = data[2];
                String price = data[3];
                String quantity = data[4];

                if(Integer.parseInt(quantity) <= 0)  // check if the item ended we won't display it.
                {
                    continue;
                }

                // Get the attributes for each item to be displayed.

                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + name);
                System.out.println("Product Type: " + type);
                System.out.println("Product Price: " + price);
                System.out.println("Product Quantity: " + quantity);
                System.out.println("================================================================================");
            }

            bufferedReader.close(); // Close the reader.
        }

        catch (IOException e) { // exception if there was an error in reading the file.
            System.out.println("An error occurred while reading the CSV file Catalog.");
            e.printStackTrace();
        }

    }

    //==================================================================================================================

    public void setMenu (Vector<Item> newMenu)  // function to set the catalog menu using another menu.
    {
        this.menu = newMenu ;
    }

    //==================================================================================================================

    public void updateFile ()   // function to update the file.
    {
        Cart.clearFile("Catalog.csv");  // clear the current file.
        String csvFilePath = "Catalog.csv";

        try {
            FileWriter fileWriter = new FileWriter(csvFilePath, true); // true to append data to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Open the CSV file to be written into.

           for (Item item : menu)
           {

               // Put the item attributes in the data row.
               String rowData = item.getId() + "," + item.getName() + "," + item.getType() + "," + item.getPrice() + "," + item.getQuantity() ;
               bufferedWriter.write(rowData);  // Write the data row to the CSV file
               bufferedWriter.newLine();
           }

            bufferedWriter.close(); // Close the writer
        }
        catch (IOException e) { // Exception if there was error in writing to the csv file.
            System.out.println("An error occurred while writing to the CSV file Catalog.");
            e.printStackTrace();
        }
    }

    //==================================================================================================================

    public static boolean findItem(int id)  // function to check if the item exists or not.
    {
        Catalog catalog = new Catalog();
        for(Item item : catalog.menu)
        {
            if(item.getId() == id)
            {
                return (item.getQuantity()>0);    // Loop through the items and return true if the item exists.
            }
        }
        return false;
    }

    //==================================================================================================================

    public static int getQuantity(int id)
    {
        Catalog catalog = new Catalog();
        for(Item item : catalog.menu)
        {
            if(item.getId() == id)
            {
                return item.getQuantity();    // Loop through the items and return the quantity of the item.
            }
        }
        return -1;
    }

    //==================================================================================================================
}
