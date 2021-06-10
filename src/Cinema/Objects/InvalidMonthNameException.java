package Cinema.Objects;

public class InvalidMonthNameException extends RuntimeException{
    public InvalidMonthNameException(String monthName){
       super("Invalid Month Name " + monthName);
    }
}
