package it.myalert.service;

import java.util.List;

import org.omg.CORBA.UserException;

import it.myalert.DTO.UserDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.UserExeption;

public interface UserService extends Converter<UserDTO, User> {
	
	public List<User> getAll();
	public User addUser(User user);
	public User getUserByEmail(String email) throws UserExeption;
	public Object convertToDTOcustom(User user) throws UserExeption, AgentExeption, ManagerExeption, CitizenExeption;
	public User getUserById(int idUser) throws UserExeption;
	
}
