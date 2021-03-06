package it.myalert.adapterConverter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.AgentDTO;
import it.myalert.entity.Agent;

public class AgentAdapter implements Converter<AgentDTO, Agent>{

	@Autowired
	UserAdapter userAdapter;
	
	@Autowired
	ManagerAdapter managerAdapter;
	
	@Override
	public AgentDTO convertToDTO(Agent agent) {
		
		AgentDTO agentDTO = new AgentDTO();
		agentDTO.setIdAgent(agent.getIdAgent());
		if(agent.getUser() != null) {			
			agentDTO.setUserDTO(userAdapter.convertToDTO(agent.getUser()));
		}
		if(agent.getManager() != null) {			
			agentDTO.setManagerDTO(managerAdapter.convertToDTO(agent.getManager()));
		}
		agentDTO.setDepartment(agent.getDepartment());
		agentDTO.setDepartment_Code(agent.getDepartmentCode());
		agentDTO.setLat(agent.getLat());
		agentDTO.setLon(agent.getLon());
		agentDTO.setStartDate_task(new Date(agent.getStartDateTask().getTime()));
		if(agent.getEndDateTask() != null) {
			agentDTO.setEndDate_task(new Date(agent.getEndDateTask().getTime()));
		}
		
		return agentDTO;
	}

	@Override
	public Agent convertToEntity(AgentDTO agentDTO) {

		Agent agent = new Agent();
		agent.setIdAgent(agentDTO.getIdAgent());
		agent.setUser(userAdapter.convertToEntity(agentDTO.getUserDTO()));
		if(agentDTO.getManagerDTO() != null) {			
			agent.setManager(managerAdapter.convertToEntity(agentDTO.getManagerDTO()));
		}
		agent.setDepartment(agentDTO.getDepartment());
		agent.setDepartmentCode(agentDTO.getDepartment_Code());
		agent.setLat(agentDTO.getLat());
		agent.setLon(agentDTO.getLon());
		agent.setStartDateTask(new Timestamp(agentDTO.getStartDate_task().getTime()));
		if(agentDTO.getEndDate_task() != null) {
			agent.setEndDateTask(new Timestamp(agentDTO.getEndDate_task().getTime()));
		}
		
		return agent;
	}

}
