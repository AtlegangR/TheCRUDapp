package poe.registration;


import javax.swing.JOptionPane;


public class Registration {
    // Declare instance variables
    private String firstName;
    private String userName; 
    private String surname; 
    private String password; 
    
   
    public Registration(String userName, String password, String john, String doe) {
        this.userName = userName;
        this.password = password;
    }
    
    // Method to input user details
    public void userDetails() {    
        JOptionPane.showMessageDialog(null, "Welcome to the CRUD program");
        JOptionPane.showMessageDialog(null, "Lets create a new account for you");
        firstName = JOptionPane.showInputDialog ("Please enter your First Name: \n");    
        surname =  JOptionPane.showInputDialog ("Please enter your Surname: \n");
        userName =  JOptionPane.showInputDialog ("Please enter your Username: \n");  
        password =  JOptionPane.showInputDialog ("Please enter your Password: \n");
    }
    
    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public static void main(String[] args) {
        Registration reg = new Registration("", "", "John", "Doe");
        reg.userDetails(); 
        Login logObject = new Login(reg); 

        logObject.registerUser(); 
        System.out.println(logObject.returnLoginStatus());
    }

    public void setPassword(String pass) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void setUserName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}