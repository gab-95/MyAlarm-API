package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.spi.ValidationProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.myalert.entity.Alarm;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.serviceimpl.ImageServiceImpl;
import it.myalert.serviceimpl.InterventionServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class InterventionServiceUnitTest {
	
	@Autowired
	private InterventionServiceImpl interventionServiceImpl;
	
	@Mock
	private InterventionServiceImpl interventionServiceImplMock;
	
	private Intervention intervention;
	
	@BeforeEach
	void setUpEnv() {
		
		intervention = new Intervention();
		
		User user = new User();
		Type type = new Type();
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
		
		
		type.setIdType(1);
		type.setName("type");
		type.setManager(manager);
		
		intervention.setType(type);
		intervention.setIdIntervention(1);
		intervention.setLat(10.0);
		intervention.setLon(10.0);
		intervention.setAddress("Address");
		intervention.setCity("city");
		intervention.setStatus("signaled");
	}
	
	@Test
	void getAll() {
		assertThat(this.interventionServiceImpl.getAll()).isNotNull();
	}
	
	@Test
	void getById() throws InterventionExeption {
		
		Exception exc = assertThrows(InterventionExeption.class, () -> this.interventionServiceImpl.getById(100));
        assertTrue(exc.getMessage().contains("ERROR: No intervention found with id:"+ 100));
		assertThat(this.interventionServiceImpl.getById(4)).isNotNull();
	}
	
	@Test
	void addIntervention() throws InterventionExeption {
		
		when(this.interventionServiceImplMock.addIntervention(intervention)).thenReturn(intervention);
		int id = this.interventionServiceImplMock.addIntervention(intervention).getIdIntervention();
		assertThat(id).isEqualTo(1);
	}
	
	@Test
	void getAllInterventionByStatusAndType() throws InterventionExeption {
		
		List<Intervention> list = new ArrayList<>();
		list = this.interventionServiceImpl.getAllInterventionByStatusAndType(intervention.getType().getIdType(), intervention.getStatus());
		Iterator<Intervention> listIT = list.iterator();
		while(listIT.hasNext()) {
			assertThat(listIT.next().getType().getIdType()).isEqualTo(intervention.getType().getIdType());
		}
		
	}

	@Test
	void updateIntervention() throws InterventionExeption {
		
		double lat_old = intervention.getLat();
		double lon_old = intervention.getLon();
		
		double lat_new = 20;
		double lon_new = 30;
		
		intervention.setLat(lat_new);
		intervention.setLon(lon_new);
		
		
		when(this.interventionServiceImplMock.updateIntervention(intervention)).thenReturn(intervention);
		assertThat(intervention.getLon()).isNotEqualTo(lon_old);
	}

	@Test
	void getByStatusOrderByStartdate() throws InterventionExeption {
		
		List<Intervention> list = new ArrayList<>();
		list = this.interventionServiceImpl.getByStatusOrderByStartdate("signaled");
		Iterator<Intervention> listIT = list.iterator();
		while(listIT.hasNext()) {
			assertThat(listIT.next().getStatus()).isEqualTo("signaled");
		}
		
	}

	@Test
	void deleteIntervention() throws InterventionExeption {
		Exception exc = assertThrows(InterventionExeption.class, () -> {
            this.interventionServiceImpl.deleteIntervention(100);
            verify(this.interventionServiceImplMock).deleteIntervention(100);
        });
        assertTrue(exc.getMessage().contains("ERROR: No intervention found with id:"+ 100));
	}


	

}
