package it.myalert.service;

import java.util.List;

import it.myalert.DTO.CitizenDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Citizen;
import it.myalert.exeption.CitizenExeption;

public interface CitizenService extends Converter<CitizenDTO, Citizen>{
	
	public List<Citizen> getAll();
	public Citizen getCitizenById(int idCitizen) throws CitizenExeption;
	public Citizen addCitizen(Citizen citizen) throws CitizenExeption;
	public Citizen updateCitizen(Citizen citizen, int idCitizen) throws CitizenExeption;
	public Citizen updatePosition(Double lat, Double lon, int idCitizen) throws CitizenExeption;

}
