package it.myalert.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.myalert.DTO.AlarmDTO;
import it.myalert.DTO.AssignDTO;
import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.TypeDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Alarm;
import it.myalert.entity.Citizen;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlarmRestControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private AlarmRestController mockService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private AlarmDTO alarmDTO;
	
	@BeforeEach
	void setUpEnv() {
		
		alarmDTO = new AlarmDTO();
		CitizenDTO citizenDTO = new CitizenDTO();
		UserDTO userDTO = new UserDTO();
		InterventionDTO interventionDTO = new InterventionDTO();
		TypeDTO typeDTO = new TypeDTO();
		ManagerDTO managerDTO = new ManagerDTO();
		
		
		userDTO = new UserDTO();
		userDTO.setIdUser(5);
		userDTO.setName("Gabriele");
		userDTO.setSurname("Test");
		userDTO.setEmail("test@uni.it");
		userDTO.setBirthDate(new Date());
		userDTO.setSex("M");
		userDTO.setAddress("AddressTest");
		userDTO.setCity("CityTest");
		userDTO.setCountry("ITA");
		
		managerDTO.setIdManager(5);
		managerDTO.setStartDate_task(new Date());
		managerDTO.setUser(userDTO);
		
		
		typeDTO.setIdType(4);
		typeDTO.setName("type");
		typeDTO.setManagerDTO(managerDTO);
		
		interventionDTO.setType(typeDTO);
		interventionDTO.setIdIntervention(4);
		interventionDTO.setLat(10.0);
		interventionDTO.setLon(10.0);
		interventionDTO.setAddress("Address");
		interventionDTO.setCity("city");
		interventionDTO.setStatus("signaled");
		
		citizenDTO.setIdCitizen(6);
		citizenDTO.setUserDTO(userDTO);
		
		Date date = new Date();
		alarmDTO.setIntervention(interventionDTO);
		alarmDTO.setAlarmDate(new Date());
		alarmDTO.setCitizen(citizenDTO);
		alarmDTO.setIdAlarm(9);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void getAll() {
		
		try {
			this.mockMvc.perform(get("/alarm/getAllAlarm").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAlarmById() throws Exception {

	    this.mockMvc.perform(get("/alarm/getAlarmById/8")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	public void getAllAlarmByIdIntervention() throws Exception {

	    this.mockMvc.perform(get("/alarm/getAllAlarmByIdIntervention/4")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	public void getAllAlarmByIdCitizen() throws Exception {

	    this.mockMvc.perform(get("/alarm/getAllAlarmByIdCitizen/6")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void post() throws Exception {

	    this.mockMvc.perform(MockMvcRequestBuilders.post("/alarm/addAlarm")
	    		.param("idType", "4")
	    		.param("idCitizen", "6")
	    		.content(objectMapper.writeValueAsString(alarmDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void deleteAlarm() throws Exception {

	    this.mockMvc.perform(delete("/alarm/deleteAlarm/8")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
}
