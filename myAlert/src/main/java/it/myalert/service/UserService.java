package it.myalert.service;

import java.util.List;

import it.myalert.DTO.UserDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.User;

public interface UserService extends Converter<UserDTO, User> {
	
	public List<User> getAll();
	public User addUser(User user);
	
}
