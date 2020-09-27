package it.myalert.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.ImageDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Citizen;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CitizenRestControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private CitizenRestController mockService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private CitizenDTO citizenDTO;
	private UserDTO userDTO;
	
	@BeforeEach
	void setUpEnv() {
		
		citizenDTO = new CitizenDTO();
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
		
		citizenDTO.setUserDTO(userDTO);
		citizenDTO.setIdCitizen(1);
		citizenDTO.setLat(40.0);
		citizenDTO.setLon(18.0);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	
	
	@Test
	public void getAll() {
		
		try {
			this.mockMvc.perform(get("/citizen/getAll").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getCitizenById() throws Exception {
	    
	    this.mockMvc.perform(get("/citizen/getCitizenById/6")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void post() throws Exception {

	    this.mockMvc.perform(MockMvcRequestBuilders.post("/citizen/addCitizen")
	    		.content(objectMapper.writeValueAsString(citizenDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	public void updatePosition() throws Exception {

	    this.mockMvc.perform(put("/citizen/updatePosition/6")
	    		.param("lat", "10.0")
	    		.param("lon", "14.0")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void updateCitizen() throws Exception {

	    this.mockMvc.perform(put("/citizen/updateCitizen")
	    		.content(objectMapper.writeValueAsString(citizenDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
}
