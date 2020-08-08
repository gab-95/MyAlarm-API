package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.AssignAdapter;
import it.myalert.entity.Assign;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.AssignExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.repository.AssignRepository;
import it.myalert.service.AssignService;

@Service
@Transactional(rollbackOn = AssignExeption.class)
public class AssignServiceImpl extends AssignAdapter implements AssignService{

	@Autowired
	private AssignRepository assignRepository;
	@Override
	public List<Assign> getAll() {
		
		return this.assignRepository.findAll();
	}

	@Override
	public Assign getAssignById(int idAssign) throws AssignExeption {
		
		return this.assignRepository.findById(idAssign).orElseThrow(()-> new AssignExeption("ERROR: No assign found with id:"+ idAssign));
	}

	@Override
	public List<Assign> getAssignByIdAgent(int idAgent) throws AgentExeption {
		
		return this.assignRepository.findByAgent_idAgent(idAgent);
	}

	@Override
	public Assign assignAgentToIntervention(Assign assign) {
		assign.setIdAssign(null);
		return this.assignRepository.save(assign);
	}

	@Override
	public Assign updateAssign(Assign assign) throws AssignExeption {
		return this.assignRepository.save(assign);
	}

	@Override
	public List<Assign> getAllAssignAndOrderByFieldName(int idAgent, String fieldName)throws AgentExeption, AssignExeption {
		//List<Assign> list = this.assignRepository.getAssignAgentAndOrderByField(idAgent, fieldName);
		List<Assign> list = this.assignRepository.findByAgent_idAgentOrderByStartValidateDesc(idAgent);
		return list;
	}

}
