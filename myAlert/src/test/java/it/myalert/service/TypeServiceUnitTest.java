package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.channels.NonWritableChannelException;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.UnexpectedTypeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.serviceimpl.TypeServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TypeServiceUnitTest {
	
	@Autowired
	private TypeServiceImpl typeServiceImpl;
	
	@Mock
	private TypeServiceImpl typeServiceImplMock;
	
	private Type type;
	private Manager manager;
	
	@BeforeEach
	void setUpEnv() throws ManagerExeption {
		
		type = new Type();
		
		User user = new User();
		Manager manager = new Manager();
		
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
		
		manager.setIdManager(1);
		manager.setStartDateTask(new Timestamp(new Date().getTime()));
		manager.setUser(user);
		
		type.setIdType(3);
		type.setName("Testo prova");
		type.setManager(manager);
	}
	
	@Test
	void getAllTest() {
		
		assertThat(this.typeServiceImpl.getAll()).isNotNull();
	}
	
	
	@Test
	void getTypeById() throws TypeExeption {
		
		Exception exc = assertThrows(TypeExeption.class, () -> this.typeServiceImpl.getTypeById(100));
        assertTrue(exc.getMessage().contains("ERROR: No type found with id:"+ 100));
		assertThat(this.typeServiceImpl.getTypeById(type.getIdType())).isNotNull();
		
	}
	
	@Test
	void addType() throws ManagerExeption {
		
		when(this.typeServiceImplMock.addType(type)).thenReturn(type);
		assertThat(type.getIdType()).isNotNull();
	}
	
	@Test
	void updateType() throws TypeExeption {
		
		type.setName("New type");
		when(this.typeServiceImplMock.updateType(type)).thenReturn(type);
		assertThat(type.getName()).isNotEqualTo("Testo prova");
	}
	
	@Test
	void deleteType() throws TypeExeption {
		
		Exception exc = assertThrows(TypeExeption.class, () -> {
            this.typeServiceImpl.deleteType(100);
            verify(this.typeServiceImplMock).deleteType(100);
        });
        assertTrue(exc.getMessage().contains("ERROR: No type found with id:"+ 100));
		
	}

}
