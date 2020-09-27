package it.myalert.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.AssignDTO;
import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.TypeDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Agent;
import it.myalert.entity.Assign;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssignRestControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private AssignRestController mockService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private AssignDTO assignDTO;
	
	@BeforeEach
	void setUpEnv() {
		
		assignDTO = new AssignDTO();
		ManagerDTO managerDTO = new ManagerDTO();
		AgentDTO agentDTO = new AgentDTO();
		UserDTO userDTO = new UserDTO();
		UserDTO userManagerDTO = new UserDTO();
		InterventionDTO interventionDTO = new InterventionDTO();
		TypeDTO typeDTO = new TypeDTO();
		
		
		userDTO = new UserDTO();
		userDTO.setIdUser(5);
		userDTO.setName("Gabriele");
		userDTO.setSurname("Test");
		userDTO.setEmail("test@uni.it");
		userDTO.setBirthDate(new Timestamp(new Date().getTime()));
		userDTO.setSex("M");
		userDTO.setAddress("AddressTest");
		userDTO.setCity("CityTest");
		userDTO.setCountry("ITA");
		
		userManagerDTO.setIdUser(5);
		userManagerDTO.setName("Manager");
		userManagerDTO.setSurname("Test manager");
		userManagerDTO.setEmail("manager@uni.it");
		userManagerDTO.setBirthDate(new Timestamp(new Date().getTime()));
		userManagerDTO.setSex("M");
		userManagerDTO.setAddress("AddressTest");
		userManagerDTO.setCity("CityTest");
		userManagerDTO.setCountry("ITA");
		
		managerDTO.setUser(userManagerDTO);
		managerDTO.setIdManager(5);
		managerDTO.setStartDate_task(new Date());
		
		agentDTO.setUserDTO(userDTO);
		agentDTO.setIdAgent(10);
		agentDTO.setDepartment("Department");
		agentDTO.setDepartment_Code("DEP1");
		agentDTO.setStartDate_task(new Date());
		agentDTO.setManagerDTO(managerDTO);
		
		typeDTO.setIdType(5);
		typeDTO.setName("type");
		typeDTO.setManagerDTO(managerDTO);
		
		interventionDTO.setType(typeDTO);
		interventionDTO.setIdIntervention(4);
		interventionDTO.setLat(10.0);
		interventionDTO.setLon(10.0);
		interventionDTO.setAddress("Address");
		interventionDTO.setCity("city");
		interventionDTO.setStatus("signaled");
		
		assignDTO.setAgent(agentDTO);
		assignDTO.setManager(managerDTO);
		assignDTO.setIntervention(interventionDTO);
		assignDTO.setIdAssign(1);
		assignDTO.setConfirm(false);
		assignDTO.setHasWritten(false);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	
	@Test
	public void getAll() {
		
		try {
			this.mockMvc.perform(get("/assign/getAllAssign").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAssignById() throws Exception {

	    this.mockMvc.perform(get("/assign/getAssignById/23")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	public void getAssignByIdIntervention() throws Exception {

	    this.mockMvc.perform(get("/assign/getAssignByIdIntervention/4")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	public void getAllAssignByIdAgent() throws Exception {

	    this.mockMvc.perform(get("/assign/getAllAssignByIdAgent/10")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	public void getAllAssignByIdAgentAndOrder() throws Exception {

	    this.mockMvc.perform(get("/assign/getAllAssignByIdAgentAndOrder/10")
	    		.param("field", "confirm")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void post() throws Exception {

	    this.mockMvc.perform(MockMvcRequestBuilders.post("/assign/assignAgentToIntervention/5")
	    		.param("idAgent", "10")
	    		.param("idIntervention", "4")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void updateAssign() throws Exception {

	    this.mockMvc.perform(put("/assign/updateAssign/23")
	    		.content(objectMapper.writeValueAsString(assignDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}

}
