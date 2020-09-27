package it.myalert.service;

import java.util.List;

import it.myalert.DTO.AssignDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Alarm;
import it.myalert.entity.Assign;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.AssignExeption;
import it.myalert.exeption.InterventionExeption;

public interface AssignService extends Converter<AssignDTO, Assign>{

	public List<Assign> getAll();
	public Assign getAssignById(int idAssign) throws AssignExeption;
	public List<Assign> getAssignByIdAgent(int idAgent) throws AgentExeption;
	public List<Assign> getAssignByIdIntervention(int idIntervention) throws InterventionExeption;
	public Assign assignAgentToIntervention(Assign assign);
	public Assign updateAssign(Assign assign) throws AssignExeption;
	public List<Assign> getAllAssignAndOrderByFieldName(int idAgent, String fieldName) throws AgentExeption, AssignExeption;
}
