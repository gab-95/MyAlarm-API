package it.myalert.adapterConverter;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.CitizenDTO;
import it.myalert.entity.Citizen;

public class CitizenAdapter implements Converter<CitizenDTO, Citizen>{

	@Autowired
	UserAdapter convertUser;
	
	@Override
	public CitizenDTO convertToDTO(Citizen citizen) {

		CitizenDTO citizenDTO = new CitizenDTO();
		citizenDTO.setIdCitizen(citizen.getIdCitizen());
		citizenDTO.setUserDTO(convertUser.convertToDTO(citizen.getUser()));
		citizenDTO.setLat(citizen.getLat());
		citizenDTO.setLon(citizen.getLon());
		return citizenDTO;
	}

	@Override
	public Citizen convertToEntity(CitizenDTO citizenDTO) {

		Citizen citizen = new Citizen();
		citizen.setIdCitizen(citizen.getIdCitizen());
		citizen.setUser(convertUser.convertToEntity(citizenDTO.getUserDTO()));
		citizen.setLat(citizen.getLat());
		citizen.setLon(citizen.getLon());
		return citizen;
	}

}
