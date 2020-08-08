package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.AgentAdapter;
import it.myalert.entity.Agent;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.repository.AgentRepository;
import it.myalert.repository.ManagerRepository;
import it.myalert.repository.UserRepository;
import it.myalert.service.AgentService;

@Service
@Transactional(rollbackOn = AgentExeption.class)
public class AgentServiceImpl extends AgentAdapter implements AgentService {

	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public List<Agent> getAll() {
		
		return this.agentRepository.findAll();
	}

	@Override
	public Agent addAgent(Agent agent, int idManager) throws AgentExeption {
		User user = agent.getUser();
		user.setIdUser(null);
		//User nuovoUser = this.userRepository.save(user);
		//this.agentRepository.addAgent(agent, idManager, nuovoUser.getIdUser());
		//agent.setUser(nuovoUser);		
		return this.agentRepository.save(agent);
		
	}

	@Override
	public Agent getAgentById(int idAgent) throws AgentExeption {

		return this.agentRepository.findById(idAgent).orElseThrow(()-> new AgentExeption("ERROR: No agent found with id:"+ idAgent));
	}

	@Override
	public Agent updatePosition(Double lat, Double lon, int idAgent) throws AgentExeption {
		Agent updatedAgent = this.agentRepository.findById(idAgent).orElseThrow(()-> new AgentExeption("ERROR: No agent found with id:"+ idAgent));
		updatedAgent.setLat(lat);
		updatedAgent.setLon(lon);
		return this.agentRepository.save(updatedAgent);
	}

	@Override
	public Agent updateAgent(Agent agent, int idAgent) throws AgentExeption {
		Agent agent2 = this.agentRepository.findById(idAgent).orElseThrow(()-> new AgentExeption("ERROR: No agent found with id:"+ idAgent));

		agent.setUser(this.userRepository.save(agent.getUser()));
		agent.setManager(agent2.getManager());
		return this.agentRepository.save(agent);
	}

	
	

}
