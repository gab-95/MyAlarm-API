package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.UserExeption;
import it.myalert.service.UserService;
import it.myalert.serviceimpl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceUnitTest {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserServiceImpl userServiceImplMock;
	
	private User user;
	
	@BeforeEach
	void setUpEnv() {
		
		user = new User();
		user.setIdUser(5);
		user.setName("Gabriele");
		user.setSurname("Test");
		user.setEmail("manager@manager.it");
		user.setBirthDate(new Timestamp(new Date().getTime()));
		user.setSex("M");
		user.setAdress("AddressTest");
		user.setCity("CityTest");
		user.setCountry("ITA");
	}
	
	@Test
	void getAllTest() {
		assertThat(userServiceImpl.getAll()).isNotNull();
	}
	
	@Test
	void saveUser() {
		
		when(this.userServiceImplMock.addUser(user)).thenReturn(user);
		
		System.out.print("id user -> " + user.toString());
		int id = this.userServiceImplMock.addUser(user).getIdUser();
		assertThat(id).isEqualTo(5);
	}
	
	@Test
	void getUserByEmail() throws UserExeption {
		
		User userGet = this.userServiceImpl.getUserByEmail(user.getEmail());
		String email = this.userServiceImpl.getUserByEmail(user.getEmail()).getEmail();
		assertThat(email).isEqualTo("manager@manager.it");
	}
	
	@Test
	void getUserById() throws UserExeption {
		
		Exception exc = assertThrows(UserExeption.class, () -> this.userServiceImpl.getUserById(100));
        assertTrue(exc.getMessage().contains("ERROR: No user found with id:"+ 100));
        assertThat(this.userServiceImpl.getUserById(3)).isNotNull();

	}
	
	
	
}
