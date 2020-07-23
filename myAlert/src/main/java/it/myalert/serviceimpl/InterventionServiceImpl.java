package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.myalert.adapterConverter.InterventionAdapter;
import it.myalert.entity.Intervention;
import it.myalert.entity.Type;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.repository.InterventionRepository;
import it.myalert.service.InterventionService;

@Service
@Transactional(rollbackOn = InterventionExeption.class)
public class InterventionServiceImpl extends InterventionAdapter implements InterventionService{

	@Autowired
	private InterventionRepository interventionRepository;
	
	@Override
	public List<Intervention> getAll() {
		return this.interventionRepository.findAll();
	}

	@Override
	public Intervention getById(int idIntervention) throws InterventionExeption {
		return this.interventionRepository.findById(idIntervention).orElseThrow(()-> new InterventionExeption("ERROR: No intervention found with id:"+ idIntervention));
	}

	@Override
	public Intervention addIntervention(Intervention intervention) throws InterventionExeption {
		intervention.setIdIntervention(null);
		return this.interventionRepository.save(intervention);
	}

	@Override
	public List<Intervention> getAllInterventionByStatusAndType(int idType, String status) throws InterventionExeption {
		return interventionRepository.findByStatusAndType_idType(status, idType);
	}
	
	@Override
	public Intervention updateIntervention(Intervention intervention) throws InterventionExeption {
		
		return this.interventionRepository.save(intervention);
	}

	@Override
	public List<Intervention> getByStatusOrderByStartdate(String status) throws InterventionExeption {
		return this.interventionRepository.findByStatusOrderByStartDate(status);
	}

}
