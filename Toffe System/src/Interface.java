import java.util.Scanner;

public class Interface {
    private LogIn LoggedInAccount = new LogIn();
    public void Display(){
        System.out.println("Welcome to Toffee Online Store :)");
        int choice =1;
        boolean registeredUser = false;


        while(choice != 0){
            Scanner input = new Scanner (System.in);
            if(registeredUser){

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
                        Logout();
                        registeredUser = false;
                    }
                    case 2 -> Catalog();
                    case 3 -> Cart();
                }
            }
            else{
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
                        Register();
                        registeredUser = true;
                    }
                    case 2 ->
                    {
                        Login();
                        registeredUser = true;

                    }
                    case 3 -> Catalog();
                }

            }
        }
    }


    public static boolean checkPassword(String password){
        int length = password.length();
        String strongPasswordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";  // Regex of strong password.
        return (length >=8 && password.matches(strongPasswordRegex));   ///Compare the password to the strong password.
    }

    public boolean UniqueUserName (String userName){
        return true;  // a dummy return to be implemented "check the username occurrence in the file"
    }

    public static boolean checkPhoneNumber(String phoneNumber){
        String validNumber ="^01\\d{9}$";   // Regex of phone number.
        return phoneNumber.matches(validNumber);    //Compare the phone number to the valid phone number.
    }

    public static boolean CheckLogin(String UserName, String password){
        return true; // a dummy return to be implemented "check"
    }
    public static boolean FindUserName(String UserName){
        return true; // a dummy return to be implemented "check"
    }
    public void Register()
    {
        Scanner input1 = new Scanner (System.in);
        String userName , password , firstName , lastName , email , address , phoneNumber;

        System.out.print("Enter a user name:\t");
        userName = input1.next();   // gets the username.
        while(!UniqueUserName(userName)){
            System.out.println("User name is already taken by another user, enter another user name:\t");
            userName="input1.next()";   // Checks that the username is unique.
        }


        System.out.print("Enter First Name:\t");
        firstName = input1.nextLine();  //gets the first name from the user.

        input1.nextLine();  // Consume the newline character left in the buffer

        System.out.println("Enter Last Name:\t");
        lastName = input1.nextLine();

        System.out.println("Enter Email:\t");
        email = input1.next();  //function checkEmail to be added.

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
        while(!checkPhoneNumber(phoneNumber)){  // checks the validation of the phone number.
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
        Register NewAccount = new Register (userName,password,firstName,lastName,email,address,phoneNumber);
        LoggedInAccount = new LogIn (userName,password);
    }
    public void Login()
    {
        Scanner input1 = new Scanner (System.in);
        String userName , password;

        System.out.println("Enter your user name:\t");
        userName = input1.next();   //gets the username from the user.
        while(!FindUserName(userName)){   // Checks that the username is unique.
            System.out.println("Can't find the username, enter a valid user name:\t");
        }

        System.out.println("Enter your password:\t");
        password = input1.next(); //gets the password from the user.
        while(!CheckLogin(userName, password)){ // Checks that username and password matches.
            System.out.println("Invalid password!!! please enter a valid password for " + userName + " account :\t");
            password = input1.next(); //gets the password from the user.
        }
        LoggedInAccount = new LogIn(userName, password);
    }
    public void Logout(){
        System.out.println("It's sorry to see out :(");
    }
    public void Catalog(){
        Catalog.DisplayMenu();
    }
    public void Cart(){
        Cart myCart = new Cart(LoggedInAccount.getUserName());
        myCart.DisplayCart();

    }
}