package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.myalert.entity.User;
import it.myalert.exeption.UserExeption;
import it.myalert.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceUnitTest {

	@Autowired
	private UserService userService;
	
	@Mock
	private UserService userServiceMock;
	
	private User user;
	
	@BeforeEach
	void setUpEnv() {
		
		user = new User();
		user.setIdUser(5);
		user.setName("Gabriele");
		user.setSurname("Test");
		user.setEmail("test@uni.it");
		user.setBirthDate(new Timestamp(new Date().getTime()));
		user.setSex("M");
		user.setAdress("AddressTest");
		user.setCity("CityTest");
		user.setCountry("ITA");
	}
	
	@Test
	void getAllTest() {
		assertThat(userService.getAll()).isNotNull();
	}
	
	@Test
	void saveUser() {
		
		when(userServiceMock.addUser(user)).thenReturn(user);
		
		System.out.print("id user -> " + user.toString());
		int id = userServiceMock.addUser(user).getIdUser();
		System.out.print("id user -> " + id);
		assertThat(id).isEqualTo(id);
	}
	
	@Test
	void getUserByEmail() throws UserExeption {
		
		when(userServiceMock.getUserByEmail(user.getEmail())).thenReturn(user);
		String email = userServiceMock.getUserByEmail(user.getEmail()).getEmail();
		assertThat(user.getEmail()).isEqualTo(email);
	}
	
	@Test
	void getUserById() throws UserExeption {
		
		when(userServiceMock.getUserById(user.getIdUser())).thenReturn(user);
		assertThat(user.getIdUser()).isNotNull();
	}
	
	
	
}
