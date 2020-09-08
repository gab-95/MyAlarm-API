package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.myalert.entity.Manager;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ManagerServiceUnitTest {
	
	@Autowired
	private ManagerService managerService;
	
	@Mock
	private ManagerService managerServiceMock;
	
	private Manager manager;
	private User user;
	
	@BeforeEach
	void setUpEnv() {
		
		manager = new Manager();
		
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
		
		manager.setUser(user);
		manager.setStartDateTask(new Timestamp(new Date().getTime()));
	}
	
	
	@Test
	void getAll() {
		assertThat(managerService.getAll()).isNotNull();
	}
	
	
	@Test
	void getById() {
		
	}
	
	@Test
	void addManager() {
		
	}

}
