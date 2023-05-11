import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LogIn {
    private String userName,password,firstName,lastName,email,address,phoneNumber;

    public LogIn() // Default constructor
    {

    }

    // =================================================================================================================
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
                    this.password = AffineCipherD(data[1]);
                    this.firstName = data[2];
                    this.lastName = data[3];
                    this.email = data[4];
                    this.address = data[5];
                    this.phoneNumber = data[6];
                    break; // Exit the loop after filling the data.
                }
            }

            bufferedReader.close(); //// Close the reader.

        }
        catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file customers.");
            e.printStackTrace();
        }
    }

    // =================================================================================================================

    String getFirstName()   // returns user's first name.
    {

        return firstName;

    }

    // =================================================================================================================
    String getLastName()    // returns user's last name.
    {

        return lastName;

    }

    // =================================================================================================================
    String getEmail()   // returns user's email.
    {

        return email;

    }

    // =================================================================================================================
    String getNumber()  // returns user's mobile number.
    {

        return phoneNumber;

    }

    // =================================================================================================================

    String getAddress()
    {

        return address;

    }

    // =================================================================================================================

    public String getUserName() {   // returns user's username.

        return this.userName;

    }

// =====================================================================================================================

    public static String AffineCipherD(String encryptedPassword) {
        // A->0 .... Z->25
        // 21(y -8) % 26 The decryption Equation.

        StringBuilder password = new StringBuilder();
        int z, inverse = 0;
        for (int i = 0; i < 26; i++) {
            z = (5 * i) % 26;

            // Check if (a*i)%26 == 1,
            // then i will be the multiplicative inverse of a

            if (z == 1)
            {
                inverse = i;
            }
        }

        int length =encryptedPassword.length();

        for (int i = 0; i < length; i++) {

            char c = Character.toUpperCase(encryptedPassword.charAt(i));  // Turn the string to UpperCase

            if (c >= 'A' && c <= 'Z')
            {
                int y = c - 'A';
                password.append((char) (((inverse * (y - 8)) % 26) + 'A'));
            }

            else
            {
                password.append(c);
            }
        }
        return password.toString();
    }

// =====================================================================================================================
}