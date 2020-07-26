package it.myalert.adapterConverter;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.AssignDTO;
import it.myalert.entity.Assign;

public class AssignAdapter implements Converter<AssignDTO, Assign>{

	@Autowired
	private ManagerAdapter managerAdapter;
	@Autowired
	private AgentAdapter agentAdapter;
	@Autowired
	private InterventionAdapter interventionAdapter;
	
	@Override
	public AssignDTO convertToDTO(Assign assign) {

		AssignDTO assignDTO = new AssignDTO();
		assignDTO.setIdAssign(assign.getIdAssign());
		assignDTO.setManager(managerAdapter.convertToDTO(assign.getManager()));
		assignDTO.setAgent(agentAdapter.convertToDTO(assign.getAgent()));
		assignDTO.setConfirm(assign.getConfirm());
		assignDTO.setHasWritten(assign.getHasWritten());
		if(assign.getStartValidate() != null) assignDTO.setStartValidate(new Date(assign.getStartValidate().getTime()));
		if(assign.getEndValidate() != null) assignDTO.setEndValidate(new Date(assign.getEndValidate().getTime()));
		assignDTO.setIntervention(interventionAdapter.convertToDTO(assign.getIntervention()));
		return assignDTO;
	}

	@Override
	public Assign convertToEntity(AssignDTO assignDTO) {

		Assign assign = new Assign();
		assign.setIdAssign(assignDTO.getIdAssign());
		assign.setManager(managerAdapter.convertToEntity(assignDTO.getManager()));
		assign.setAgent(agentAdapter.convertToEntity(assignDTO.getAgent()));
		assign.setConfirm(assignDTO.getConfirm());
		assign.setHasWritten(assignDTO.getHasWritten());
		if(assignDTO.getStartValiDate() != null) assign.setStartValidate(new Timestamp(assignDTO.getStartValiDate().getTime()));
		if(assignDTO.getEndValidate() != null) assign.setEndValidate(new Timestamp(assignDTO.getEndValidate().getTime()));
		assign.setIntervention(interventionAdapter.convertToEntity(assignDTO.getIntervention()));
		return assign;
	}

}
