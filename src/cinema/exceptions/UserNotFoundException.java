package cinema.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("Incorrect Username or Password");
    }
}
