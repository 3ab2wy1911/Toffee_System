import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Catalog {
    private Vector<Item> menu = new Vector<Item>();

    public Catalog()
    {
        String csvFilePath = "Catalog.csv";
        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                int id = Integer.valueOf(data[0]);
                String name = data[1];
                String type = data[2];
                double price = Double.valueOf(data[3]);
                int quantity = Integer.valueOf(data[4]);

                Item item = new Item(id, name, type,price,quantity);
                menu.add(item);

            }
            bufferedReader.close(); //// Close the reader.
        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }
    }

    public Vector<Item> getMenu() {
        return menu;
    }

    public static void DisplayMenu()
    {
        System.out.println("================================Catalog Menu================================");
        String csvFilePath = "Catalog.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                String id = data[0];
                String name = data[1];
                String type = data[2];
                String price = data[3];
                String quantity = data[4];

                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + name);
                System.out.println("Product Type: " + type);
                System.out.println("Product Price: " + price);
                System.out.println("Product Quantity: " + quantity);
                System.out.println("================================================================================");
            }

            bufferedReader.close(); //// Close the reader.
        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }
    };
}
