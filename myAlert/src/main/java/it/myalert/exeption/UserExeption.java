package it.myalert.exeption;

public class UserExeption extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExeption(String errorMessage) {
		super (errorMessage);
	}

}