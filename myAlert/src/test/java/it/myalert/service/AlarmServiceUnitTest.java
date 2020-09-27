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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jayway.jsonpath.internal.path.ArraySliceOperation.Operation;

import it.myalert.entity.Alarm;
import it.myalert.entity.Citizen;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.serviceimpl.AlarmServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AlarmServiceUnitTest {
	
	@Autowired
	private AlarmServiceImpl alarmServiceImpl;
	
	@Mock
	private AlarmServiceImpl alarmServiceImplMock;
	
	private Alarm alarm;
	
	@BeforeEach
	void setUpEnv() {
		
		alarm = new Alarm();
		Citizen citizen = new Citizen();
		User user = new User();
		Intervention intervention = new Intervention();
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
		
		citizen.setIdCitizen(1);
		citizen.setUser(user);
		
		alarm.setIntervention(intervention);
		alarm.setCitizen(citizen);
		alarm.setIdAlarm(1);
	}
	
	@Test
	void getAll() {
		assertThat(this.alarmServiceImpl.getAll()).isNotNull();
	}

	@Test
	void getAlarmById() throws AlarmExeption {
		
		Exception exc = assertThrows(AlarmExeption.class, () -> this.alarmServiceImpl.getAlarmById(100));
        assertTrue(exc.getMessage().contains("ERROR: No alarm found with id:"+ 100));
        assertThat(this.alarmServiceImpl.getAlarmById(8)).isNotNull();
	}
	
	@Test
	void addAlarm() throws AlarmExeption {
		when(this.alarmServiceImplMock.addAlarm(alarm)).thenReturn(alarm);
		int id = this.alarmServiceImplMock.addAlarm(alarm).getIdAlarm();
		assertThat(id).isEqualTo(1);
	}

	@Test
	void getAllAlarmByIdIntervention() throws InterventionExeption {
		
		List<Alarm> list = new ArrayList<>();
		list = this.alarmServiceImpl.getAllAlarmByIdIntervention(alarm.getIntervention().getIdIntervention());
		Iterator<Alarm> listIT = list.iterator();
		while(listIT.hasNext()) {
			assertThat(listIT.next().getIntervention().getIdIntervention()).isEqualTo(alarm.getIntervention().getIdIntervention());
		}
		
	}

	@Test
	void getAllAlarmByIdCitizen() throws CitizenExeption {
		
		List<Alarm> list = new ArrayList<>();
		list = this.alarmServiceImpl.getAllAlarmByIdCitizen(alarm.getCitizen().getIdCitizen());
		Iterator<Alarm> listIT = list.iterator();
		while(listIT.hasNext()) {
			assertThat(listIT.next().getCitizen().getIdCitizen()).isEqualTo(alarm.getCitizen().getIdCitizen());
		}
	}
	
	@Test
	void deleteAlarm() throws AlarmExeption {
		Exception exc = assertThrows(AlarmExeption.class, () -> {
            this.alarmServiceImpl.deleteAlarm(100);
            verify(this.alarmServiceImplMock).deleteAlarm(100);
        });
        assertTrue(exc.getMessage().contains("ERROR: No alarm found with id:"+ 100));
	};

}
