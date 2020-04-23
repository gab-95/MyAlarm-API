package it.myalert.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.adapterConverter.AgentAdapter;
import it.myalert.adapterConverter.UserAdapter;
import it.myalert.entity.Agent;
import it.myalert.exeption.AgentExeption;
import it.myalert.repository.AgentRepository;
import it.myalert.service.AgentService;
import it.myalert.service.UserService;

public class AgentServiceImpl extends AgentAdapter implements AgentService {

	@Autowired
	private AgentRepository agentRepository;
	
	@Override
	public List<Agent> getAll() {
		
		return this.agentRepository.findAll();
	}

	@Override
	public Agent addAgent(Agent agent, int idManager) throws AgentExeption {
		
		return this.agentRepository.addAgent(agent, idManager);
	}

}