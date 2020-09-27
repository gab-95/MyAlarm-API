package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import it.myalert.entity.Agent;
import it.myalert.entity.Manager;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.serviceimpl.AgentServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AgentServiceUnitTest {
	
	@Autowired
	private AgentServiceImpl agentServiceImpl;
	
	@Mock
	private AgentServiceImpl agentServiceImplMock;
	
	private Agent agent;
	private User user;
	private Manager manager;
	
	@BeforeEach
	void setUpEnv() {
		
		
		agent = new Agent();
		user = new User();
		User userManager = new User();
		manager = new Manager();
		
		
		user.setIdUser(5);
		user.setName("Gabriele");
		user.setSurname("Test");
		user.setEmail("test@uni.it");
		user.setBirthDate(new Timestamp(new Date().getTime()));
		user.setSex("M");
		user.setAdress("AddressTest");
		user.setCity("CityTest");
		user.setCountry("ITA");
		
		userManager.setIdUser(5);
		userManager.setName("Manager");
		userManager.setSurname("Test manager");
		userManager.setEmail("manager@uni.it");
		userManager.setBirthDate(new Timestamp(new Date().getTime()));
		userManager.setSex("M");
		userManager.setAdress("AddressTest");
		userManager.setCity("CityTest");
		userManager.setCountry("ITA");
		
		manager.setIdManager(1);
		manager.setUser(userManager);
		manager.setStartDateTask(new Timestamp(new Date().getTime()));
		
		agent.setManager(manager);
		agent.setUser(user);
		agent.setIdAgent(1);
		agent.setLat(10.0);
		agent.setLon(10.0);
		agent.setDepartment("Department");
		agent.setDepartmentCode("DEP1");
		
	}
	@Test
	void getAll() {
		assertThat(this.agentServiceImpl.getAll()).isNotNull();
	}

	@Test
	void addAgent() throws AgentExeption {
		
		when(this.agentServiceImplMock.addAgent(agent, manager.getIdManager())).thenReturn(agent);
		int id = this.agentServiceImplMock.addAgent(agent, manager.getIdManager()).getIdAgent();
		assertThat(id).isEqualTo(1);
	}

	@Test
	void getAgentById() throws AgentExeption {
		
		Exception exc = assertThrows(AgentExeption.class, () -> this.agentServiceImpl.getAgentById(100));
        assertTrue(exc.getMessage().contains("ERROR: No agent found with id:"+ 100));
        assertThat(this.agentServiceImpl.getAgentById(10)).isNotNull();
	}

	@Test
	void updatePosition() throws AgentExeption {
		double lat_new = 20;
		double lon_new = 30;
		
		double lat_old = agent.getLat();
		double lon_old = agent.getLon();
		
		Exception exc = assertThrows(AgentExeption.class, () -> this.agentServiceImpl.getAgentById(100));
        assertTrue(exc.getMessage().contains("ERROR: No agent found with id:"+ 100));
		
		when(this.agentServiceImplMock.updatePosition(lat_new, lon_new, agent.getIdAgent())).thenReturn(agent);
		assertThat(agent.getLon()).isNotEqualTo(lon_new);
		
	}
	
	@Test
	void updateAgent() throws AgentExeption {
		
		Exception exc = assertThrows(AgentExeption.class, () -> this.agentServiceImpl.getAgentById(100));
        assertTrue(exc.getMessage().contains("ERROR: No agent found with id:"+ 100));
		
		agent.setDepartment("Department2");
		when(this.agentServiceImplMock.updateAgent(agent, agent.getIdAgent())).thenReturn(agent);
		assertThat(agent.getDepartment()).isNotEqualTo("Department");
	}

}
