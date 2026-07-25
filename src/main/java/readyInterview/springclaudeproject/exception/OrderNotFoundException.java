package readyInterview.springclaudeproject.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id){
        super("Order not Found with id "+id);
    }


}
