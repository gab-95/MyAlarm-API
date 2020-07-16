package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.CitizenAdapter;
import it.myalert.entity.Citizen;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.repository.CitizenRepository;
import it.myalert.service.CitizenService;

@Service
@Transactional(rollbackOn = CitizenExeption.class)
public class CitizenServiceImpl  extends CitizenAdapter implements CitizenService{

	@Autowired
	private CitizenRepository citizenRepository;
	
	@Override
	public List<Citizen> getAll() {
		return this.citizenRepository.findAll();
	}

	@Override
	public Citizen getCitizenById(int idCitizen) throws CitizenExeption {
		return this.citizenRepository.findById(idCitizen).orElseThrow(()-> new CitizenExeption("ERROR: No citizen found with id:"+ idCitizen));
	}

	@Override
	public Citizen addCitizen(Citizen citizen) throws CitizenExeption {
		citizen.getUser().setIdUser(null);
		citizen.setIdCitizen(null);
		return this.citizenRepository.save(citizen);
	}

	@Override
	public Citizen updatePosition(Double lat, Double lon, int idCitizen) throws CitizenExeption {
		Citizen updatedCitizen = this.citizenRepository.findById(idCitizen).orElseThrow(()-> new CitizenExeption("ERROR: No citizen found with id:"+ idCitizen));
		updatedCitizen.setLat(lat);
		updatedCitizen.setLon(lon);
		return this.citizenRepository.save(updatedCitizen);
	}

	@Override
	public Citizen updateCitizen(Citizen citizen, int idCitizen) throws CitizenExeption {
		//Citizen updatedCitizen = this.citizenRepository.findById(idCitizen).orElseThrow(()-> new CitizenExeption("ERROR: No citizen found with id:"+ idCitizen));
		return this.citizenRepository.save(citizen);
	}

}
