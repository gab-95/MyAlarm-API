package it.myalert.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import it.myalert.DTO.AlarmDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Agent;
import it.myalert.entity.Manager;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgentRestControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private AgentRestController mockService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private AgentDTO agentDTO;
	private UserDTO userDTO;
	private ManagerDTO managerDTO;
	
	@BeforeEach
	void setUpEnv() {
		
		
		agentDTO = new AgentDTO();
		userDTO = new UserDTO();
		UserDTO userManagerDTO = new UserDTO();
		managerDTO = new ManagerDTO();
		
		
		userDTO.setIdUser(5);
		userDTO.setName("Gabriele");
		userDTO.setSurname("Test");
		userDTO.setEmail("test@uni.it");
		userDTO.setBirthDate(new Date());
		userDTO.setSex("M");
		userDTO.setAddress("AddressTest");
		userDTO.setCity("CityTest");
		userDTO.setCountry("ITA");
		
		userManagerDTO.setIdUser(5);
		userManagerDTO.setName("Manager");
		userManagerDTO.setSurname("Test manager");
		userManagerDTO.setEmail("manager@uni.it");
		userManagerDTO.setBirthDate(new Date());
		userManagerDTO.setSex("M");
		userManagerDTO.setAddress("AddressTest");
		userManagerDTO.setCity("CityTest");
		userManagerDTO.setCountry("ITA");
		
		managerDTO.setIdManager(1);
		managerDTO.setUser(userManagerDTO);
		managerDTO.setStartDate_task(new Date());
		
		//agentDTO.setManagerDTO(managerDTO);
		agentDTO.setUserDTO(userDTO);
		agentDTO.setIdAgent(1);
		agentDTO.setLat(10.0);
		agentDTO.setLon(10.0);
		agentDTO.setDepartment("Department");
		agentDTO.setDepartment_Code("DEP1");
		agentDTO.setStartDate_task(new Date());
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	
	
	@Test
	public void getAll() {
		
		try {
			this.mockMvc.perform(get("/agent/getAllAgent").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAgentById() throws Exception {

	    this.mockMvc.perform(get("/agent/getAgent/10")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	public void getAgentByPosition() throws Exception {

	    this.mockMvc.perform(get("/agent/getAgentByPosition")
	    		.param("lat", "10.0")
	    		.param("lon", "14.0")
	    		.param("distance", "10")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void post() throws Exception {

	    this.mockMvc.perform(MockMvcRequestBuilders.post("/agent/addAgent/5")
	    		.content(objectMapper.writeValueAsString(agentDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void updatePosition() throws Exception {

	    this.mockMvc.perform(put("/agent/updatePosition/10")
	    		.param("lat", "10.0")
	    		.param("lon", "14.0")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void updateAgent() throws Exception {

	    this.mockMvc.perform(put("/agent/updateAgent/10")
	    		.content(objectMapper.writeValueAsString(agentDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void deleteAgent() throws Exception {

	    this.mockMvc.perform(delete("/agent/deleteAgent/10")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
}
