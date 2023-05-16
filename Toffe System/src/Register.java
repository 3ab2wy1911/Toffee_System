import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

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

    public static void sendEmail(String email , String otp) {

        // email ID of  Sender.
        String sender = "AMAtoffesystem@gmail.com";

        // password of Sender.
        String password = "sfeajlzbvncixhqv";

        // Email Subject
        String subject = "Registration Verification";

        // Email Body
        String body = "Your code is: " + otp;

        // SMTP server configuration
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        // Create properties for the SMTP session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host",smtpHost);
        properties.put("mail.smtp.port",smtpPort);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Create a session with the sender's email credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });


        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set the email subject and body
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Please enter the code sent to : "+ email);
        }
        catch (MessagingException e) {
            System.out.println("Failed to send OTP. Error: " + e.getMessage());
        }
    }

    //==================================================================================================================
    public static String OTP()
    {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        return String.valueOf(otp);

    }

    //==================================================================================================================

}