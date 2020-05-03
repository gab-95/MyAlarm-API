package it.myalert.serviceimpl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.DTO.UserDTO;
import it.myalert.adapterConverter.AgentAdapter;
import it.myalert.adapterConverter.CitizenAdapter;
import it.myalert.adapterConverter.ManagerAdapter;
import it.myalert.adapterConverter.UserAdapter;
import it.myalert.entity.Agent;
import it.myalert.entity.Citizen;
import it.myalert.entity.Manager;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.UserExeption;
import it.myalert.repository.AgentRepository;
import it.myalert.repository.CitizenRepository;
import it.myalert.repository.ManagerRepository;
import it.myalert.repository.UserRepository;
import it.myalert.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends UserAdapter implements UserService  {
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private AgentAdapter agentConverter;
	@Autowired
	private ManagerAdapter managerConverter;
	@Autowired
	private CitizenAdapter citizenAdapter;

	
	@Override
	public List<User> getAll() {
		List<User> users = this.userRepository.findAll();
		return users;
	}


	@Override
	public User addUser(User user) {
		
		return this.userRepository.save(user);
	}


	@Override
	public User getUserByEmail(String email) throws UserExeption {
			
		try {
			return this.userRepository.findByEmail(email);
		}catch (Exception e) {
			throw new UserExeption("ERROR: No user found with email: "+ email);
		}
	}

	public Object convertToDTOcustom(User user) throws UserExeption, AgentExeption, ManagerExeption,CitizenExeption {
		if(user.getAgent() != null && user.getAgent().size() > 0) {
			Agent agent = (Agent)user.getAgent().toArray()[0];
			Agent agent2 = this.agentRepository.findById(agent.getIdAgent()).orElseThrow(()-> new AgentExeption("ERROR: No agent found with id: "+ agent.getIdAgent()));
			return this.agentConverter.convertToDTO(agent2);
		} else if(user.getManager() != null && user.getManager().size() > 0) {
			Manager manager = (Manager)user.getManager().toArray()[0];
			Manager manager2 = this.managerRepository.findById(manager.getIdManager()).orElseThrow(()-> new ManagerExeption("ERROR: No manager found with id: "+ manager.getIdManager()));
			return this.managerConverter.convertToDTO(manager2);
		} else if(user.getCitizen() != null && user.getCitizen().size() > 0) {
			Citizen citizen = (Citizen)user.getCitizen().toArray()[0];
			Citizen citizen2 = this.citizenRepository.findById(citizen.getIdCitizen()).orElseThrow(()-> new CitizenExeption("ERROR: No manager found with id: "+ citizen.getIdCitizen()));
			return this.citizenAdapter.convertToDTO(citizen2);
		}
		throw new UserExeption("ERROR: no user found!!");
	}
	

}
