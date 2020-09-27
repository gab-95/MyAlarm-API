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

import it.myalert.entity.Manager;
import it.myalert.entity.User;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.serviceimpl.ManagerServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ManagerServiceUnitTest {
	
	@Autowired
	private ManagerServiceImpl managerServiceImpl;
	
	@Mock
	private ManagerServiceImpl managerServiceImplMock;
	
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
		manager.setIdManager(5);
		manager.setStartDateTask(new Timestamp(new Date().getTime()));
	}
	
	
	@Test
	void getAll() {
		assertThat(this.managerServiceImpl.getAll()).isNotNull();
	}
	
	
	@Test
	void getById() throws ManagerExeption {
		
		Exception exc = assertThrows(ManagerExeption.class, () -> this.managerServiceImpl.getById(100));
        assertTrue(exc.getMessage().contains("ERROR: No user found"));
		assertThat(this.managerServiceImpl.getById(5)).isNotNull();
	}
	
	@Test
	void addManager() throws ManagerExeption {
		when(this.managerServiceImplMock.addManager(manager)).thenReturn(manager);
		int id = this.managerServiceImplMock.addManager(manager).getIdManager();
		assertThat(id).isEqualTo(5);
	}

}
