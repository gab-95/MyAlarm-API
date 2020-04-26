package it.myalert.serviceimpl;

import java.awt.CardLayout;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.AgentAdapter;
import it.myalert.entity.Agent;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.repository.AgentRepository;
import it.myalert.repository.UserRepository;
import it.myalert.service.AgentService;
import net.bytebuddy.asm.Advice.This;

@Service
@Transactional(rollbackOn = AgentExeption.class)
public class AgentServiceImpl extends AgentAdapter implements AgentService {

	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<Agent> getAll() {
		
		return this.agentRepository.findAll();
	}

	@Override
	public Agent addAgent(Agent agent, int idManager) throws AgentExeption, Exception {
		User user = agent.getUser();
		user.setIdUser(null);
		//User nuovoUser = this.userRepository.save(user);
		//this.agentRepository.addAgent(agent, idManager, nuovoUser.getIdUser());
		//agent.setUser(nuovoUser);		
		return this.agentRepository.save(agent);
		
	}

}
