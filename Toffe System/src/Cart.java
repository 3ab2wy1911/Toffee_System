import java.io.*;
import java.util.Vector;

public class Cart {
    String userName;
    Catalog catalog;
    double totalPrice;
    private final Vector<Item> itemList = new Vector<>();
    private Vector<Item>CatalogList = new Vector<>();

    public Cart(String userName) {

        this.userName = userName;   // store username.
        catalog = new Catalog();    // initialize catalog.
        CatalogList = catalog.getMenu();    // initialize CatalogList and store in it the catalog menu.
        totalPrice = 0 ;    // initialize totalPrice.
        retrieveData(userName);     // retrieve the data from the file using username.
    }

    //==================================================================================================================
    public Cart()   // default empty constructor.
    {
    }

    //==================================================================================================================
    public void retrieveData(String userName) { // retrieve the data from the file using username.
        itemList.clear(); // clear the cart list.
        String csvFilePath = "Cart.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // open the CSV file to read the data.

            String line;
            while ((line = bufferedReader.readLine()) != null) {    // read line by line until the file ends.
                String[] data = line.split(",");        // split line by ','.
                String csvUserName = data[0];   // getting the username.
                if(userName.equals(csvUserName))    // compare between the username in the file and the username sent to the function.
                {
                    for (Item item : CatalogList) { // looping through the items in the catalog list.
                        if (item.getId()==(Integer.parseInt(data[1]))) {
                            int id = Integer.parseInt(data[1]); // get the id.
                            String name = item.getName();   // get the name.
                            String type = item.getType();   // get the type.
                            double price = item.getPrice(); // get the price.
                            int quantity = Integer.parseInt(data[2]);   // get the quantity "in the file not in the menu".
                            Item newItem = new Item(id, name, type, price, quantity);   // make an item with these attributes.
                            itemList.add(newItem);  // add the new item to the cart list.
                        }
                    }
                }
            }

            bufferedReader.close(); // Close the reader.
        }
        catch (IOException e) { // exception if there was an error in reading the file.
            System.out.println("An error occurred while reading the CSV file Cart.");
            e.printStackTrace();
        }
    }

    //==================================================================================================================

    public double getTotalPrice() {
        for (Item item : itemList){
            totalPrice += (item.getPrice() * item.getQuantity());  // Looping through the list and assigning the total price.
        }

        return totalPrice;
    }

    //==================================================================================================================

    public void addItem (String userName, int id, int quantity) // function to update the file "cart" with a new item.
    {
        String csvFilePath = "Cart.csv";

        try {
            FileWriter fileWriter = new FileWriter(csvFilePath, true); // true to append data to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String rowData = userName + "," + id + "," + quantity;    // Create a string with the data separated by commas
            bufferedWriter.write(rowData);  // Write the data row to the CSV file
            bufferedWriter.newLine();

            bufferedWriter.close(); // Close the writer

        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file Cart.");
            e.printStackTrace();
        }
        for(Item item : CatalogList)    // Loop through the catalog till the id is found.
        {
            if(item.getId() == id)
            {
                item.setQuantity(quantity); // Minus the quantity user buys from the catalog quantity.
                break;
            }
        }
        catalog.setMenu(CatalogList);
        catalog.updateFile();
    }

    //==================================================================================================================

    public void DisplayCart() { // Function to display the cart.
        System.out.println("================================Your Cart========================================");
        for(Item data : itemList) {
            int id = data.getId();
            String name = data.getName();
            String type = data.getType();
            double price = data.getPrice();
            int quantity = data.getQuantity();

            // Getting the attributes from the cart list.

            System.out.println("Product ID: " + id);
            System.out.println("Product Name: " + name);
            System.out.println("Product Type: " + type);
            System.out.println("Product Price: " + price);
            System.out.println("Product Quantity: " + quantity);
            System.out.println("================================================================================");
        }
    }

    //==================================================================================================================

    String getType(int id) {    // function to get the type of item.
        String type = "";
        for (Item item : CatalogList)
        {
            if (item.getId() == id)
            {
                type = item.getType();  // Looping throw the catalog menu to get the type.
            }
        }

        if(type.equals("Loose"))
        {

            return "Kg";
        }

        return "packet";
    }

    //==================================================================================================================

    public static void clearFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(""); // Write an empty string to truncate the file
            fileWriter.close();
            System.out.println("File cleared successfully.");
        }
        catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
            e.printStackTrace();
        }
    }

    //==================================================================================================================
    public void updateCart(String userName)
    {
        Vector<String> tempList = new Vector<>();
        String csvFilePath1 = "Cart.csv";
        String csvFilePath2 = "Cart.csv";


        try {
            FileReader fileReader = new FileReader(csvFilePath2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // open the CSV file to read the data.

            String line;
            while ((line = bufferedReader.readLine()) != null) {    // read line by line until the file ends.
                String[] data = line.split(",");        // split line by ','.
                String csvUserName = data[0];   // getting the username.
                if(!(userName.equals(csvUserName)))    // compare between the username in the file and the username sent to the function.
                {
                    tempList.add(line);
                }
            }

            bufferedReader.close(); // Close the reader.
        }
        catch (IOException e) { // exception if there was an error in reading the file.
            System.out.println("An error occurred while reading the CSV file Cart.");
            e.printStackTrace();
        }

        clearFile("Cart.csv");

        try {
            FileWriter fileWriter = new FileWriter(csvFilePath1, true); // true to append data to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(String row : tempList)
            {
                bufferedWriter.write(row);  // Write the data row to the CSV file
                bufferedWriter.newLine();
            }

            bufferedWriter.close(); // Close the writer

        }
        catch (IOException e) { //
            System.out.println("An error occurred while writing to the CSV file Cart.");
            e.printStackTrace();
        }
    }
    //==================================================================================================================
}
