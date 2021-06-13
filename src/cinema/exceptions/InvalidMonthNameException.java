package cinema.exceptions;

public class InvalidMonthNameException extends RuntimeException{
    public InvalidMonthNameException(String monthName){
       super("Invalid Month Name " + monthName);
    }
}
