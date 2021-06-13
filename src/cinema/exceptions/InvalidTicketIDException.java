package cinema.exceptions;

public class InvalidTicketIDException  extends Exception{
    public InvalidTicketIDException(){
        super("Invalid Ticket ID");
    }
}
