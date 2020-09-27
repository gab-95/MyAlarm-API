package it.myalert.restcontroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


import it.myalert.DTO.ImageDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.TypeDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Image;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageRestControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private ImageRestController mockService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private ImageDTO imageDTO;
	
	
	@BeforeEach
	void setUpEnv() {
		
		imageDTO = new ImageDTO();
		UserDTO userDTO = new UserDTO();
		UserDTO userManagerDTO = new UserDTO();
		InterventionDTO interventionDTO = new InterventionDTO();
		TypeDTO typeDTO = new TypeDTO();
		ManagerDTO managerDTO = new ManagerDTO();
		
		
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
		
		managerDTO.setIdManager(1);
		managerDTO.setUser(userManagerDTO);
		managerDTO.setStartDate_task(new Date());
		
		
		typeDTO.setIdType(1);
		typeDTO.setName("type");
		typeDTO.setManagerDTO(managerDTO);
		
		interventionDTO.setType(typeDTO);
		interventionDTO.setIdIntervention(1);
		interventionDTO.setLat(10.0);
		interventionDTO.setLon(10.0);
		interventionDTO.setAddress("Address");
		interventionDTO.setCity("city");
		interventionDTO.setStatus("signaled");
		
		imageDTO.setIdImage(1);
		imageDTO.setIntervention(interventionDTO);
		imageDTO.setUser(userDTO);
		imageDTO.setUrl("http:\\url.image.it");
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	@Test
	public void getAll() {
		
		try {
			this.mockMvc.perform(get("/image/getAll").contentType("application/json")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getAllImageByIdIntervention() throws Exception {

	    
	    this.mockMvc.perform(get("/image/getAllImageByIdIntervention/4")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	public void getImage() throws Exception {

	    
	    this.mockMvc.perform(get("/image/getImage")
	    		.param("idIntervention", "4")
	    		.param("idUser", "3")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void post() throws Exception {

		List<ImageDTO> newlistDTO = new ArrayList<ImageDTO>();
		newlistDTO.add(imageDTO);
		
	    this.mockMvc.perform(MockMvcRequestBuilders.post("/image/addImage")
	    		.param("idUser", "3")
	    		.param("idIntervention", "4")
	    		.content(objectMapper.writeValueAsString(newlistDTO))
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
	
	@Test
	@Transactional
	public void deleteType() throws Exception {

	    this.mockMvc.perform(delete("/image/deleteImage/11")
	    		.contentType(MediaType.APPLICATION_JSON_VALUE))
	      		.andExpect(status().isOk());
	}
	
}
