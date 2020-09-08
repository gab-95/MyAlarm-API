package it.myalert.restcontroller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import it.myalert.entity.Agent;
import it.myalert.entity.User;
import it.myalert.service.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers= UserRestController.class)
public class UserRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRestController userRestController;
	
	@MockBean
	private UserService userServiceMock;
	
	
	@Test
	void getByIdUser() throws Exception {
		
		User user = new User();
		user.setName("Gabriele");
		user.setSurname("Panico");
		user.setEmail("gab@pan.it");
		
		int id = 1;
	    
	    when(userServiceMock.getUserById(id)).thenReturn(user);
	    
	    mockMvc.perform(get("/api/agent/getByIdUser/{id}", id)
	    		.contentType("application/json"))
	      		.andExpect(status().isOk())
	      		.andExpect(jsonPath("$.id", is(1)));
	      
	    
	    verify(userServiceMock, times(1)).getUserById(id);
	    verifyNoMoreInteractions(userServiceMock);
		
	}

}
