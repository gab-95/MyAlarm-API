package it.myalert.adapterConverter;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Manager;

public class ManagerAdapter implements Converter<ManagerDTO, Manager> {

	@Autowired
	UserAdapter convertUser;
	
	@Override
	public ManagerDTO convertToDTO(Manager manager) {
		
		ManagerDTO managerDTO = new ManagerDTO();
		//managerDTO.setIdManager(manager.getIdManager());
		managerDTO.setUser(convertUser.convertToDTO(manager.getUser()));
		managerDTO.setStartDate_task(manager.getStartDateTask());
		managerDTO.setEndDate_task(manager.getEndDateTask());
		return managerDTO;
	}

	@Override
	public Manager convertToEntity(ManagerDTO managerDTO) {

		Manager manager = new Manager();
		//manager.setIdManager(managerDTO.getIdManager());
		manager.setUser(convertUser.convertToEntity(managerDTO.getUser()));
		manager.setStartDateTask(new Timestamp(managerDTO.getStartDate_task().getTime()));
		manager.setEndDateTask(new Timestamp(managerDTO.getEndDate_task().getTime()));
		return manager;
	}

}