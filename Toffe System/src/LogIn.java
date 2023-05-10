import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LogIn {
    private String userName;

    public LogIn() {
    } // Default constructor

    public LogIn(String userName) {
        this.userName = userName;
        retrieveData(); // Retrieve data from the file based on the userName
    }

    public void retrieveData() {
        String csvFilePath = "customers.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String csvUserName = data[0];

                if (csvUserName.equals(userName)) {     // Assign values to the attributes of the class
                    this.userName = csvUserName;
                    String password = data[1];
                    String firstName = data[2];
                    String lastName = data[3];
                    String email = data[4];
                    String address = data[5];
                    String phoneNumber = data[6];
                    break; // Exit the loop after filling the data.
                }
            }

            bufferedReader.close(); //// Close the reader.

            System.out.println("Data has been retrieved from the CSV file successfully!");
        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }
    }


    public String getUserName() {
        return this.userName;
    }
}
