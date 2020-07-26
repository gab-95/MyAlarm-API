package it.myalert.service;

import java.util.List;

import it.myalert.DTO.InterventionDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Intervention;
import it.myalert.exeption.ImageExeption;
import it.myalert.exeption.InterventionExeption;

public interface InterventionService extends Converter<InterventionDTO, Intervention> {
	
	public List<Intervention> getAll();
	public Intervention getById(int idIntervention) throws InterventionExeption;
	public Intervention addIntervention(Intervention intervention) throws InterventionExeption;
	public List<Intervention> getAllInterventionByStatusAndType(int idType, String status) throws InterventionExeption;
	public Intervention updateIntervention(Intervention intervention) throws InterventionExeption;
	public List<Intervention> getByStatusOrderByStartdate(String status) throws InterventionExeption;
	public Boolean deleteIntervention(int idIntervention) throws InterventionExeption;
}
