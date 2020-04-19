package it.myalert.adapterConverter;

import java.sql.Timestamp;
import java.util.Date;

import it.myalert.DTO.UserDTO;
import it.myalert.entity.User;

public class UserAdapter implements Converter<UserDTO, User> {

	@Override
	public UserDTO toDTO(User user) {
		// TODO Auto-generated method stub
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		userDTO.setBirthDate(new Date(user.getBirthDate().getTime()));
		userDTO.setSex(user.getSex());
		userDTO.setAddress(user.getAddress());
		userDTO.setCity(user.getCity());
		return userDTO;
	}

	@Override
	public User fromDTO(UserDTO userDTO) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setIdUser(userDTO.getIdUser());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setBirthDate(new Timestamp(userDTO.getBirthDate().getTime()));
		user.setSex(userDTO.getSex());
		user.setAddress(userDTO.getSex());
		user.setCity(userDTO.getCity());
		return user;
	}


}
