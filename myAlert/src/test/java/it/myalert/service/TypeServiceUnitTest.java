package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TypeServiceUnitTest {
	
	@Autowired
	private TypeService typeService;
	@Autowired
	private ManagerService managerService;
	
	@Mock
	private TypeService typeServiceMock;
	
	private Type type;
	private Manager manager;
	
	@BeforeEach
	void setUpEnv() throws ManagerExeption {
		
		type = new Type();
		type.setIdType(1);
		type.setName("Testo prova");
		type.setManager(managerService.getById(3));
	}
	
	@Test
	void getAllTest() {
		
		assertThat(typeService.getAll()).isNotNull();
	}
	
	
	@Test
	void getTypeById() throws TypeExeption {
		
		when(typeServiceMock.getTypeById(type.getIdType())).thenReturn(type);
		
		assertThat(type).isEqualTo(typeService.getTypeById(type.getIdType()));
	}
	
	@Test
	void addType() throws ManagerExeption {
		
		when(typeServiceMock.addType(type)).thenReturn(type);
		
		assertThat(type.getIdType()).isNotNull();
	}
	
	@Test
	void updateType() throws TypeExeption {
		
		type.setName("New type");
		when(typeServiceMock.updateType(type)).thenReturn(type);
		
		assertThat(type.getName()).isNotEqualTo("Testo prova");
	}
	
	@Test
	void deleteType() throws TypeExeption {
		
		boolean status = true; 
		when(typeServiceMock.deleteType(type.getIdType())).thenReturn(true);
		
		assertThat(status).isEqualTo(true);
		
	}

}
