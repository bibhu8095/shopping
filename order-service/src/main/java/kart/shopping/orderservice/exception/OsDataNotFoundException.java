package kart.shopping.orderservice.exception;

public class OsDataNotFoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 5305053244125949223L;
	
	public OsDataNotFoundException(String msg) {
        super(msg);
    }

}
