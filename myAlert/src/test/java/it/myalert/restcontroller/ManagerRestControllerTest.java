package it.myalert.restcontroller;

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

import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.TypeDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Manager;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ManagerRestControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private ManagerRestController mockService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private ManagerDTO managerDTO;
	
	@BeforeEach
	public void setUpEnv() {
		
		managerDTO = new ManagerDTO();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setIdUser(5);
		userDTO.setName("Gabriele");
		userDTO.setSurname("Test");
		userDTO.setEmail("test@uni.it");
		userDTO.setBirthDate(new Timestamp(new Date().getTime()));
		userDTO.setSex("M");
		userDTO.setAddress("AddressTest");
		userDTO.setCity("CityTest");
		userDTO.setCountry("ITA");
		
		managerDTO.setUser(userDTO);
		managerDTO.setIdManager(5);
		managerDTO.setStartDate_task(new Date());
		

		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}
	
	
	@Test
	public void getAll() {
		
		try {
			this.mockMvc.perform(get("/manager/getAll").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getManagerById() throws Exception {

	    
	    this.mockMvc.perform(get("/manager/getManagerById/5")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void post() throws Exception {

	    this.mockMvc.perform(MockMvcRequestBuilders.post("/manager/addManager")
	    		.content(objectMapper.writeValueAsString(managerDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}

}
