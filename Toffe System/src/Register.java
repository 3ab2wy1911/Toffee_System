import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class Register {
    private final String userName;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;

    public Register(String userName , String password , String firstName , String lastName , String email , String address , String phoneNumber )
    {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        storeDataInFile();  // Function to store the data in a file
    }

    private void storeDataInFile() {
        String csvFilePath = "customers.csv";

        try {
            FileWriter fileWriter = new FileWriter(csvFilePath, true); // true to append data to the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String rowData = userName + "," + password + "," + firstName + "," + lastName + "," + email + "," + address + "," + phoneNumber;    // Create a string with the data separated by commas
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
}