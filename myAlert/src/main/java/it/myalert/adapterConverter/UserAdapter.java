package it.myalert.adapterConverter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.UserDTO;
import it.myalert.entity.User;

public class UserAdapter implements Converter<UserDTO, User> {

	@Autowired
	AgentAdapter agentConverter;
	@Autowired
	ManagerAdapter managerConverter;
	@Autowired
	CitizenAdapter citizenConverter;
	@Override
	public UserDTO convertToDTO(User user) {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(user.getIdUser());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		userDTO.setEmail(user.getEmail());
		userDTO.setBirthDate(new Date(user.getBirthDate().getTime()));
		userDTO.setSex(user.getSex());
		userDTO.setAddress(user.getAdress());
		userDTO.setCity(user.getCity());
		userDTO.setCountry(user.getCountry());
		return userDTO;
	}

	@Override
	public User convertToEntity(UserDTO userDTO) {

		User user = new User();
		user.setIdUser(userDTO.getIdUser());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setEmail(userDTO.getEmail());
		user.setBirthDate(new Timestamp(userDTO.getBirthDate().getTime()));
		user.setSex(userDTO.getSex());
		user.setAdress(userDTO.getAddress());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		return user;
	}


}
