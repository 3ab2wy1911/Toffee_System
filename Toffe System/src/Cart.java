import java.io.*;
import java.util.Vector;

public class Cart {
    String userName;
    Catalog catalog;
    double totalPrice;
    private Vector<Item> itemList = new Vector<Item>();

    public Cart(String userName) {
        this.userName = userName;
        catalog = new Catalog();
        retrieveData(userName);
        totalPrice = 0 ;
    }
    public Cart()
    {
    }
    public void retrieveData(String userName) {
        String csvFilePath2 = "Cart.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath2);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String csvUserName = data[0];
                Vector<Item> menu = new Vector<Item>(catalog.getMenu());
                if(userName.equals(data[0]))
                {
                    for (Item item : menu) {
                        if (item.getId()==(Integer.valueOf(data[1]))) {
                            int id = Integer.valueOf(data[1]);
                            String name = item.getName();
                            String type = item.getType();
                            double price = item.getPrice();
                            int quantity = Integer.valueOf(data[2]);
                            Item newItem = new Item(id, name, type, price, quantity);
                            itemList.add(newItem);
                        }
                    }
                }
            }

            bufferedReader.close(); //// Close the reader.

            System.out.println("Data has been retrieved from the CSV file successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }
    }

    public double getTotalPrice() {
        for (Item item : itemList){
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    public void addItem (String userName, int id, int quantity)
    {
        updateCatalog(userName,id, quantity);
        String csvFilePath = "Cart.csv";

        try {
            FileWriter fileWriter = new FileWriter(csvFilePath, true); // true to append data to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String rowData = userName + "," + id + "," + quantity;    // Create a string with the data separated by commas
            bufferedWriter.write(rowData);  // Write the data row to the CSV file
            bufferedWriter.newLine();

            bufferedWriter.close(); // Close the writer

            System.out.println("Data has been written to the CSV file successfully!");
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }
    public void updateCatalog(String userName, int id, int quantity) {
        String csvFilePath = "customers.csv";
        String tempCsvFilePath = "temp.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(tempCsvFilePath, true);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (String.valueOf(id).equals(data[0])) {
                    int currentQuantity = Integer.parseInt(data[5]);
                    data[5] = String.valueOf(currentQuantity - quantity);
                }
                fileWriter.write(String.join(",", data));
                fileWriter.write("\n");
            }

            bufferedReader.close();
            fileWriter.close();

            // Rename the temporary file to the original file
            java.io.File oldFile = new java.io.File(csvFilePath);
            java.io.File newFile = new java.io.File(tempCsvFilePath);
            if (oldFile.delete()) {
                newFile.renameTo(oldFile);
            } else {
                throw new IOException("Failed to replace the original file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading/writing the CSV file.");
            e.printStackTrace();
        }
    }

    public void DisplayCart() {
        System.out.println("================================Your Cart========================================");
        for(Item data : itemList) {
            int id = data.getId();
            String name = data.getName();
            String type = data.getType();
            double price = data.getPrice();
            int quantity = data.getQuantity();
            System.out.println("Product ID: " + id);
            System.out.println("Product Name: " + name);
            System.out.println("Product Type: " + type);
            System.out.println("Product Price: " + price);
            System.out.println("Product Quantity: " + quantity);
            System.out.println("================================================================================");
        }
    }
    public void clearFile() {
        String filePath = "Cart.csv";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(""); // Write an empty string to truncate the file
            fileWriter.close();
            System.out.println("File cleared successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
            e.printStackTrace();
        }
    }
}
