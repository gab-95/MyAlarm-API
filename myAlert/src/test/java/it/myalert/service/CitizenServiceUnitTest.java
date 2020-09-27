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

import it.myalert.entity.Citizen;
import it.myalert.entity.User;
import it.myalert.exeption.AssignExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.serviceimpl.CitizenServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CitizenServiceUnitTest {

	@Autowired
	private CitizenServiceImpl citizenServiceImpl;
	
	@Mock
	private CitizenServiceImpl citizenServiceImplMock;
	
	private Citizen citizen;
	private User user;
	
	@BeforeEach
	void setUpEnv() {
		
		citizen = new Citizen();
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
		
		citizen.setUser(user);
		citizen.setIdCitizen(1);
		citizen.setLat(40.0);
		citizen.setLon(18.0);
		
	}
	
	@Test
	void getAllTest() {
		assertThat(this.citizenServiceImpl.getAll()).isNotNull();
	}
	
	
	@Test
	void getCitizenById() throws CitizenExeption {
		
		Exception exc = assertThrows(CitizenExeption.class, () -> this.citizenServiceImpl.getCitizenById(100));
        assertTrue(exc.getMessage().contains("ERROR: No citizen found with id:"+ 100));
        assertThat(this.citizenServiceImpl.getCitizenById(6)).isNotNull();
	}
	
	@Test
	void addCitizen() throws CitizenExeption {
		
		when(this.citizenServiceImplMock.addCitizen(citizen)).thenReturn(citizen);
		int id = this.citizenServiceImplMock.addCitizen(citizen).getIdCitizen();
		assertThat(id).isEqualTo(1);
	}
	
	@Test
	void updateCitizen() throws CitizenExeption {
		
		citizen.getUser().setAdress("address test2");
		when(this.citizenServiceImplMock.updateCitizen(citizen)).thenReturn(citizen);
		assertThat(citizen.getUser().getAdress()).isNotEqualTo("AddressTest");
	}
	
	@Test
	void updatePosition() throws CitizenExeption {
		double lat_new = 20;
		double lon_new = 30;
		
		double lat_old = citizen.getLat();
		double lon_old = citizen.getLon();
		
		when(this.citizenServiceImplMock.updatePosition(lat_new, lon_new, citizen.getIdCitizen())).thenReturn(citizen);
		assertThat(citizen.getLon()).isNotEqualTo(lon_new);
		
	}
}
