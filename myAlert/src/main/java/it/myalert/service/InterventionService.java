package it.myalert.service;

import java.util.List;

import it.myalert.DTO.InterventionDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Intervention;
import it.myalert.exeption.InterventionExeption;

public interface InterventionService extends Converter<InterventionDTO, Intervention> {
	
	public List<Intervention> getAll();
	public Intervention getById(int idIntervention) throws InterventionExeption;

}
