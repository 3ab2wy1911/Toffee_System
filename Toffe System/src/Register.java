import java.io.BufferedWriter;
import java.io.FileWriter;
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
        this.password = AffineCipherE(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

        storeDataInFile();  // Function to store the data in a file
    }

    // =================================================================================================================

    public static String AffineCipherE(String password) {
        // A->0 .... Z->25
        // (5x +8) % 26 The encryption Equation.

        StringBuilder encryptedPassword = new StringBuilder();
        int length =password.length();

        for (int i = 0; i < length; i++) {
            char c = Character.toUpperCase(password.charAt(i));  // Turn the string to UpperCase
            if (c >= 'A' && c <= 'Z') {
                int x = c - 'A';
                encryptedPassword.append((char) (((5 * x + 8) % 26) + 'A'));
            }

            else {
                encryptedPassword.append(c);
            }
        }
        return encryptedPassword.toString();
    }

    // =================================================================================================================

    private void storeDataInFile() {    // Function to store the data in a file
        String csvFilePath = "customers.csv";

        try {
            FileWriter fileWriter = new FileWriter(csvFilePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            // Open the CSV file and write into it.

            String rowData = userName + "," + password + "," + firstName + "," + lastName + "," + email + "," + address + "," + phoneNumber;    // Create a string with the data separated by commas
            bufferedWriter.write(rowData);  // Write the data row to the CSV file
            bufferedWriter.newLine();

            bufferedWriter.close(); // Close the writer

        }

        catch (IOException e) { // Exception if there was an error while writing the data.
            System.out.println("An error occurred while writing to the CSV file customers.");
            e.printStackTrace();
        }
    }
    //==================================================================================================================
}