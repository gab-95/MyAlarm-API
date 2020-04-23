package it.myalert.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ManagerExeption extends Exception{

	public ManagerExeption(String errorMessage) {
		super (errorMessage);
	}
}
