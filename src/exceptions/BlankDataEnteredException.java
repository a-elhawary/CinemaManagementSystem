package exceptions;

public class BlankDataEnteredException extends Exception{
    public BlankDataEnteredException(){
        super("Fill in all Data");
    }
}
