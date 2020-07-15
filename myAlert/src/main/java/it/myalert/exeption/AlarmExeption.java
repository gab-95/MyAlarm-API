package it.myalert.exeption;

public class AlarmExeption extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlarmExeption(String errorMessage) {
		super (errorMessage);
	}

}
