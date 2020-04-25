package it.myalert.service;

import java.util.List;

import it.myalert.DTO.AgentDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Agent;
import it.myalert.exeption.AgentExeption;

public interface AgentService extends Converter<AgentDTO, Agent> {

	public List<Agent> getAll();
	public Agent addAgent(Agent agent, int idManager) throws AgentExeption;
}
