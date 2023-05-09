public class LogIn {
    private String userName , password;
    public LogIn()
    {}; // Default constructor
    public LogIn(String userName , String password) //Parameterized constructor.
    {
        this.userName = userName;
        this.password = password;
    }
    public String getUserName(){
        return this.userName;
    }
}
