import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Interface {
    private LogIn LoggedInAccount = new LogIn();
    private Cart myCart= new Cart();

    public void Display(){
        System.out.println("Welcome to Toffee Online Store :)");
        int choice =1;
        boolean registeredUser = false; //Variable which will detect which menu appears for the user.

        while(choice != 0){ //If User choose 0 then the program Ends.
            Scanner input = new Scanner (System.in);
            if(registeredUser){ //If the user Logged in or register to the system then the program will view this menu
                System.out.println("Good to see You "+ LoggedInAccount.getUserName() + " :)");
                System.out.println("""
                Please enter a valid option from the list below:
                1. LogOut
                2. Catalog
                3. Cart
                0. Exit
                """
                );

                choice =input.nextInt();

                switch (choice) {
                    case 1 ->
                    {
                        logOut();   // Calling the logOut function.
                        registeredUser = false; //  To display the other menu the next time.
                    }
                    case 2 -> Catalog();    // Calling the catalog function.

                    case 3 -> Cart();   // Calling the cart function.
                }
            }

            else{   // The other menu when user doses not log in or register yet.
                System.out.println("""
                Please enter a valid option from the list below:
                1. Register.
                2. Login.
                3. Catalog
                0. Exit
                """
                );

                choice =input.nextInt();

                switch (choice) {
                    case 1 ->
                    {
                        Register(); // Calling the Register function.
                        registeredUser = true;
                    }
                    case 2 ->
                    {
                        Login();    // Calling the Login function
                        registeredUser = true;  // To display the other menu next time.

                    }
                    case 3 -> Catalog.DisplayMenu();    // Calling the Catalog static function DisplayMenu().
                }

            }
        }
        System.out.println("Thanks for using our application...");  // Will appear when the application closes.
    }
//======================================================================================================================

    public static boolean checkPassword(String password){   // Function that checks that the password is strong enough.
        String strongPasswordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";  // Regex of strong password.

        return (password.matches(strongPasswordRegex));   ///Compare the password to the strong password.
    }

//======================================================================================================================

    public boolean UniqueUserName (String userName){    // Function that checks that the username is unique.
        String csvFilePath = "customers.csv";

        try {
            FileReader fileReader = new FileReader(csvFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Opening the CSV file to be Read.

            String line;
            while ((line = bufferedReader.readLine()) != null) {    // Getting line by line from the file until the file ends.
                String[] data = line.split(",");    // Split the data in the line by ','.
                String csvUserName = data[0];   // Assigning first data in the line to a variable.
                if (userName.equals(csvUserName))   // Checking if the userName is equal to the variable.
                {
                    return false;
                }
            }

            bufferedReader.close(); // Close the reader.
        }
        catch (IOException e) { // exception if there was an error while reading the file.
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }

        return true;    // Executed if the username wasn't found
    }

    //==================================================================================================================

    public static boolean checkPhoneNumber(String phoneNumber){ //Check that the phone number is a valid phone number.
        String validNumber ="^01\\d{9}$";   // Regex of phone number.

        return !phoneNumber.matches(validNumber);    //Compare the phone number to the valid phone number.
    }

    //==================================================================================================================

    public static boolean CheckLogin(String userName, String password) {    //Check that the username and password are matching.
            String csvFilePath = "customers.csv";
            try {
                FileReader fileReader = new FileReader(csvFilePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                // Opening the CSV file to be Read.

                String line;
                while ((line = bufferedReader.readLine()) != null) {    // Getting line by line from the file until the file ends.
                    String[] data = line.split(",");    // Split the data in the line by ','.
                    String csvUserName = data[0];
                    String csvPassword = data[1];
                    // Get the  username and password from the CSV file.

                    if (csvUserName.equals(userName) && csvPassword.equals(password)) {
                        bufferedReader.close();
                        return true; // Found a matching userName and password
                    }
                }

            }
            catch (IOException e) {
                System.out.println("An error occurred while reading the CSV file.");
                e.printStackTrace();
            }

            return false; // No matching userName and password found
        }

    //==================================================================================================================

    public boolean FindUserName(String UserName)    // function that the username is existed.
    {

        return !(UniqueUserName(UserName)); // return the inverse of the return of UniqueUserName function.

    }

    //==================================================================================================================

    public static boolean CheckName(String Name)    // function that checks that the name contains letters only.
    {
        String validName = "^[a-zA-Z']+$";  // Regex of valid name.

        return (!Name.matches(validName));
    }

    //==================================================================================================================

    public static boolean CheckEmail(String email)  // function that checks that the email is valid.
    {
        String validEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";    // Regex of valid email.

        return(email.matches(validEmail));
    }

    //==================================================================================================================


    public void Register()
    {
        Scanner input1 = new Scanner (System.in);
        String userName , password , firstName , lastName , email , address , phoneNumber;

        // code and otp that will be used in verification.
        String code , otp=Register.OTP();

        System.out.print("Enter a user name:\t");
        userName = input1.next();   // gets the username.
        while(!UniqueUserName(userName)){   // Checks that the username is unique.
            System.out.println("User name is already taken by another user, enter another user name:\t");
            userName=input1.next();   // gets the username.
        }

        input1.nextLine();  // Consume the newline character left in the buffer

        System.out.print("Enter First Name:\t");
        firstName = input1.nextLine();  //gets the first name from the user.
        while(CheckName(firstName)){    // checks that the first name contains only letters.
            System.out.println("Enter a valid name:\t");
            firstName = input1.nextLine(); //gets the first name from the user.
        }

        System.out.println("Enter Last Name:\t");
        lastName = input1.nextLine();   //gets the last name from the user.
        while(CheckName(lastName)){ // checks that the last name contains only letters.
            System.out.println("Enter a valid name:\t");
            lastName = input1.nextLine(); //gets the last name from the user.
        }

        System.out.println("Enter Email:\t");
        email = input1.next();  // gets the email from the user.
        while(!CheckEmail(email))   // Checks the validity of the email.
        {
            System.out.println("Enter a valid email:\t");
            email = input1.next();  // gets the email from the user.
        }

        input1.nextLine();  // Consume the newline character left in the buffer

        System.out.println("""
                The password must contain:
                 1.at least 8 characters
                 2.at least one uppercase letter (A-Z)
                 3.at least one lowercase letter (a-z)
                 4.at least one digit (0-9)
                 5.at least one special character from the following set: @$!%*?&.""");
        System.out.println("Enter a password:\t");
        password = input1.nextLine();
        while(!checkPassword(password))
        {
            System.out.println("""
                Weak Password !!!
                The password must contain:
                 1.at least 8 characters
                 2.at least one uppercase letter (A-Z)
                 3.at least one lowercase letter (a-z)
                 4.at least one digit (0-9)
                 5.at least one special character from the following set: @$!%*?&.""");
            System.out.println("Enter a password:\t");
            password = input1.nextLine();

        }


        System.out.print("""
        Mobile Number must start with 01 and contains 11 number.
        Enter your Mobile Number:\t
        """);
        phoneNumber = input1.next();    // gets the number from the user.
        while(checkPhoneNumber(phoneNumber)){  // checks the validation of the phone number.
            System.out.print("""
        Invalid Number!!!
        Mobile Number must start with 01 and contains 11 number.
        Enter your Mobile Number again:\t
        """);
            phoneNumber = input1.next();    // gets the number from the user.
        }


        input1.nextLine();  // Consume the newline character left in the buffer


        System.out.println("Enter your Address:\t");
        address = input1.nextLine();    //gets the address from the user.

        // Validate the registration of the user.
        Register.sendEmail(email,otp);
        code = input1.next(); //gets the code from the user.
        while(!code.equals(otp))
        {
            System.out.println("Invalid code!!! Please enter a valid code:\t");
            code=input1.next();
        }

        System.out.println("User's Registration Completed!!!");

        new Register(userName, password, firstName, lastName, email, address, phoneNumber); // pass the data to the constructor of the Register class to be added to the file.
        LoggedInAccount = new LogIn (userName); // Get the account of the user.
        myCart = new Cart(LoggedInAccount.getUserName());   // Get the cart of the user.
    }

    //==================================================================================================================

    public void Login()
    {
        Scanner input1 = new Scanner (System.in);
        String userName , password;


        System.out.println("Enter your user name:\t");
        userName = input1.next();   //gets the username from the user.
        while(!FindUserName(userName)){   // Checks that the username is existed.
            System.out.println("Can't find the username, enter a valid user name:\t");
            userName = input1.next(); //gets the username from the user
        }


        System.out.println("Enter your password:\t");
        password = input1.next(); // gets the password from the user.
        while(!CheckLogin(userName, password)){ // Checks that username and password matches.
            System.out.println("Invalid password!!! please enter a valid password for " + userName + " account :\t");
            password = input1.next(); // gets the password from the user.
        }
        LoggedInAccount = new LogIn (userName); // gets the account of the user.
        myCart = new Cart(LoggedInAccount.getUserName());   // gets the cart of the user
    }

    //==================================================================================================================

    public void logOut()
    {
        System.out.println("It's sorry to see out :(");
    }

    //==================================================================================================================

    public void Catalog(){
        int choice =1;

        while(choice != 0 ) {   // if user choose 0 the function end.
            Scanner input = new Scanner (System.in);

            Catalog.DisplayMenu();  // call the DisplayMenu function in the catalog class.

            System.out.println("""
                    Please enter your choice:
                    0. Back to the previous menu
                    1. Add item to Your cart
                    """);

            choice = input.nextInt();   // get the choice from the user.

            if(choice == 1){
                int id,quantity;

                System.out.println("Enter the ID of the item you want to add to your cart:\t");
                id = input.nextInt();   // gets the id from the user.
                while(!Catalog.findItem(id)){   // Check if the item existed.
                    System.out.println("Couldn't find item :( , please enter a valid ID:\t");
                    id=input.nextInt();
                }


                System.out.println("Enter the Quantity of the item you want to add to your cart:\t");
                quantity = input.nextInt();     // gets the quantity from the user.
                    while(Catalog.getQuantity(id) < quantity){  // checks that the quantity in the inventory is enough.
                    System.out.println("Not enough Quantity :( , please Enter a valid quantity:\t");
                    quantity=input.nextInt();
                }
                while(quantity > 50)    // checks if the quantity is greater than 50.
                {
                    String type = myCart.getType(id);
                    System.out.println("Sorry, You are not allowed to add more than 50 "+type+" to your cart :(");
                    System.out.println("Enter a valid Quantity please:\t");
                    quantity=input.nextInt();
                }

                myCart.addItem(LoggedInAccount.getUserName(),id, quantity); // call function add item to add the item to the user's cart.
                myCart = new Cart(LoggedInAccount.getUserName());   // Update the cart of the user.
            }
        }
    }

    //==================================================================================================================

    public void Cart(){
        double totalPrice =myCart.getTotalPrice();  // get the total price of the user's cart.

        Scanner input = new Scanner(System.in);

        myCart.DisplayCart();   // Display the user's cart.

        System.out.println("Your Cart Total price is: " + totalPrice);
        System.out.println("""
                Go to check out?
                1.Yes
                2.NO
                """);
        int answer = input.nextInt();   // get the answer from the user.

        double cash;

        if (answer == 1){
            System.out.println("Is \" "+LoggedInAccount.getAddress()+" \" is the address you want to do the delivery to?");
            System.out.println("""
                    1.Yes
                    2.NO
                    """);
            answer = input.nextInt(); // get the answer from the user
            while (answer <1 || answer>2)
            {
                System.out.println("Invalid Choice!!!");
                System.out.println("Is \" "+LoggedInAccount.getAddress()+" \" is the address you want to do the delivery to?");
                System.out.println("""
                    1.Yes
                    2.NO
                    """);
                answer = input.nextInt(); // get the answer from the user
            }

            String address ;

            if(answer == 1)
            {
                address = LoggedInAccount.getAddress(); // get the original address of the user.
            }

            else {

                input.nextLine();  // Consume the newline character left in the buffer

                System.out.println("Enter the address you want to do the delivery to:\t");
                address = input.nextLine(); // get the address from the user.
            }

            System.out.println("Is \" "+LoggedInAccount.getNumber()+" \" is the phone number you want to use?");

            System.out.println("""
                    1.Yes
                    2.NO
                    """);

            answer = input.nextInt(); // get the answer from the user
            while (answer <1 || answer>2)
            {
                System.out.println("Invalid Choice!!!");
                System.out.println("Is \" "+LoggedInAccount.getNumber()+" \" is the phone number you want to use?");
                System.out.println("""
                    1.Yes
                    2.NO
                    """);

                answer = input.nextInt(); // get the answer from the user
            }

            String phoneNumber ;

            if(answer == 1)
            {
                phoneNumber = LoggedInAccount.getNumber(); // get the original mobile number of the user.
            }

            else {

                input.nextLine();  // Consume the newline character left in the buffer

                System.out.println("Enter he phone number you want to use:\t");
                phoneNumber = input.nextLine(); // get the address from the user.
                while(checkPhoneNumber(phoneNumber)){  // checks the validation of the phone number.
                    System.out.print("""
        Invalid Number!!!
        Mobile Number must start with 01 and contains 11 number.
        Enter your Mobile Number again:\t
        """);
                    phoneNumber = input.next();    // gets the number from the user.
                }
            }

            System.out.println("Please enter the cash amount:\t");
            cash = input.nextDouble();

            if(cash >= totalPrice){
                System.out.println("Congratulations!!! The payment done successfully");
                System.out.println("Customer Name: "+LoggedInAccount.getFirstName() + " " + LoggedInAccount.getLastName());
                System.out.println("Phone Number: "+phoneNumber);
                System.out.println("Customer user name: "+LoggedInAccount.getUserName());
                System.out.println("Customer Address: "+address);
                System.out.println("The amount required to pay :"+totalPrice);
                System.out.println("The amount paid :"+cash);
                System.out.println("________________________________________________________________");
                System.out.println("Cash left for the user: "+ (cash - totalPrice));

                myCart.updateCart(LoggedInAccount.getUserName());
                myCart = new Cart(LoggedInAccount.getUserName());
                // clear the cart of the user.
            }

            else {
                System.out.println("The payment failed :(");
            }
        }
        myCart = new Cart(LoggedInAccount.getUserName());   // Update the cart of the user.
    }
}

    //==================================================================================================================

