package nytro.exceptions;

import javax.servlet.ServletException;

public class MyException extends ServletException {

	private static final long serialVersionUID = -5995336735427981643L;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

//	SE NON UTILIZZATE LE CANCELLIAMO
//	
//	public CustomException(String message, Throwable rootCause) {
//		super(message, rootCause);
//	}
//
//	public CustomException(Throwable rootCause) {
//		super(rootCause);
//	}

}
