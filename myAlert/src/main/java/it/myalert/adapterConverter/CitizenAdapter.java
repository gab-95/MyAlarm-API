package it.myalert.adapterConverter;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.CitizenDTO;
import it.myalert.entity.Citizen;

public class CitizenAdapter implements Converter<CitizenDTO, Citizen>{

	@Autowired
	UserAdapter userAdapter;
	
	@Override
	public CitizenDTO convertToDTO(Citizen citizen) {

		CitizenDTO citizenDTO = new CitizenDTO();
		citizenDTO.setIdCitizen(citizen.getIdCitizen());
		if(citizen.getUser() != null) {
			citizenDTO.setUserDTO(userAdapter.convertToDTO(citizen.getUser()));
		}
		citizenDTO.setLat(citizen.getLat());
		citizenDTO.setLon(citizen.getLon());
		return citizenDTO;
	}

	@Override
	public Citizen convertToEntity(CitizenDTO citizenDTO) {

		Citizen citizen = new Citizen();
		citizen.setIdCitizen(citizenDTO.getIdCitizen());
		citizen.setUser(userAdapter.convertToEntity(citizenDTO.getUserDTO()));
		citizen.setLat(citizenDTO.getLat());
		citizen.setLon(citizenDTO.getLon());
		return citizen;
	}

}
