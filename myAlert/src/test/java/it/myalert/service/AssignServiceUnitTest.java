package it.myalert.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.useRepresentation;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import it.myalert.entity.Agent;
import it.myalert.entity.Alarm;
import it.myalert.entity.Assign;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AssignExeption;
import it.myalert.serviceimpl.AlarmServiceImpl;
import it.myalert.serviceimpl.AssignServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssignServiceUnitTest {

	@Autowired
	private AssignServiceImpl assignServiceImpl;
	
	@Mock
	private AssignServiceImpl assignServiceImplMock;
	
	private Assign assign;
	
	@BeforeEach
	void setUpEnv() {
		
		assign = new Assign();
		Manager manager = new Manager();
		Agent agent = new Agent();
		User user = new User();
		User userManager = new User();
		Intervention intervention = new Intervention();
		Type type = new Type();
		
		
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
		
		userManager.setIdUser(5);
		userManager.setName("Manager");
		userManager.setSurname("Test manager");
		userManager.setEmail("manager@uni.it");
		userManager.setBirthDate(new Timestamp(new Date().getTime()));
		userManager.setSex("M");
		userManager.setAdress("AddressTest");
		userManager.setCity("CityTest");
		userManager.setCountry("ITA");
		
		manager.setUser(userManager);
		manager.setIdManager(1);
		manager.setStartDateTask(new Timestamp(new Date().getTime()));
		
		agent.setUser(user);
		agent.setIdAgent(1);
		agent.setDepartment("Department");
		agent.setDepartmentCode("DEP1");
		agent.setManager(manager);
		
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
		
		assign.setAgent(agent);
		assign.setManager(manager);
		assign.setIntervention(intervention);
		assign.setIdAssign(1);
		assign.setConfirm(false);
		assign.setHasWritten(false);
		
		
	}
	
	@Test
	void getAll() {
		assertThat(this.assignServiceImpl.getAll()).isNotNull();
	}

	@Test
	void getAssignById() throws AssignExeption {
		Exception exc = assertThrows(AssignExeption.class, () -> this.assignServiceImpl.getAssignById(100));
        assertTrue(exc.getMessage().contains("ERROR: No assign found with id:"+ 100));
        assertThat(this.assignServiceImpl.getAssignById(23)).isNotNull();
	}
	
	@Test
	void getAssignByIdAgent() throws AgentExeption {
		
		List<Assign> list = new ArrayList<>();
		list = this.assignServiceImpl.getAssignByIdAgent(assign.getAgent().getIdAgent());
		Iterator<Assign> listIT = list.iterator();
		while(listIT.hasNext()) {
			assertThat(listIT.next().getAgent().getIdAgent()).isEqualTo(assign.getAgent().getIdAgent());
		}
	}
	
	@Test
	void assignAgentToIntervention() {
		
		when(this.assignServiceImplMock.assignAgentToIntervention(assign)).thenReturn(assign);
		int id = this.assignServiceImplMock.assignAgentToIntervention(assign).getIdAssign();
		assertThat(id).isEqualTo(1);
	}
	
	@Test
	void updateAssign() throws AssignExeption {
		
		assign.setConfirm(true);
		when(this.assignServiceImplMock.updateAssign(assign)).thenReturn(assign);
		assertThat(assign.getConfirm()).isNotEqualTo(false);
	}

	
	@Test
	void getAllAssignAndOrderByFieldName() throws AgentExeption, AssignExeption {
		
		List<Assign> list = new ArrayList<>();
		list = this.assignServiceImpl.getAllAssignAndOrderByFieldName(assign.getAgent().getIdAgent(), "startValidate");
		Iterator<Assign> listIT = list.iterator();
		while(listIT.hasNext()) {
			assertThat(listIT.next().getAgent().getIdAgent()).isEqualTo(assign.getAgent().getIdAgent());
		}
	}
	
}
