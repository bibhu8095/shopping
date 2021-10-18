package kart.shopping.orderservice.exception;

public class UserNotFoundException extends IllegalArgumentException{

	 public UserNotFoundException(String msg) {
	        super(msg);
	    }
}
