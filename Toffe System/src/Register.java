public class Register {
    private String userName , password , firstName , lastName , email , address , phoneNumber;
    static int count;   // to be initialized using the file.
    public Register(String userName , String password , String firstName , String lastName , String email , String address , String phoneNumber )
    {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        // function to store the data in a file.
    }
}
