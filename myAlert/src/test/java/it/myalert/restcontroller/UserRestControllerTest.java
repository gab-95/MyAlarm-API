package it.myalert.restcontroller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.myalert.DTO.UserDTO;
import it.myalert.entity.User;
import it.myalert.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRestControllerTest {
		
	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private UserRestController mockService;

	private MockMvc mockMvc;
	private UserDTO userDTO;
	
	@BeforeEach
	public void setUpEnv() {
		
		this.userDTO = new UserDTO();
		this.userDTO.setName("Gabriele");
		this.userDTO.setSurname("Panico");
		this.userDTO.setEmail("gab@pan.it");
		this.userDTO.setBirthDate(new Timestamp(new Date().getTime()));
		this.userDTO.setSex("M");
		this.userDTO.setAddress("Address");
		this.userDTO.setCity("CityTest");
		this.userDTO.setCountry("ITA");
		
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
		
	}
	
	
	
	@Test
	public void getAllTest() {
		try {
			this.mockMvc.perform(get("/user/getAll").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void getByIdUserTest() throws Exception {

	    
	    this.mockMvc.perform(get("/user/getUserById/3")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	

	@Test
	public void getByEmailTest() throws Exception {

	    
	    this.mockMvc.perform(get("/user/getByEmail/")
	    		.param("email", "manager@manager.it")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}

}
