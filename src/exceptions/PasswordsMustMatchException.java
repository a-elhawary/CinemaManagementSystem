package exceptions;

public class PasswordsMustMatchException extends Exception{
    public PasswordsMustMatchException(){
        super("Passwords don't Match");
    }
}
