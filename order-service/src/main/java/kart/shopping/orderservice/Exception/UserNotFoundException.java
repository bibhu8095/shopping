package kart.shopping.orderservice.Exception;

public class UserNotFoundException extends IllegalArgumentException{

	 public UserNotFoundException(String msg) {
	        super(msg);
	    }
}
