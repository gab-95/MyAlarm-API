package it.myalert.adapterConverter;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Manager;

public class ManagerAdapter implements Converter<ManagerDTO, Manager> {

	@Autowired
	UserAdapter userAdapter;
	
	@Override
	public ManagerDTO convertToDTO(Manager manager) {
		
		ManagerDTO managerDTO = new ManagerDTO();
		managerDTO.setIdManager(manager.getIdManager());
		if(manager.getUser() != null) {			
			managerDTO.setUser(userAdapter.convertToDTO(manager.getUser()));
		}
		managerDTO.setStartDate_task(manager.getStartDateTask());
		if(manager.getEndDateTask() != null) {
			managerDTO.setEndDate_task(new Date(manager.getEndDateTask().getTime()));
		}
		return managerDTO;
	}

	@Override
	public Manager convertToEntity(ManagerDTO managerDTO) {

		Manager manager = new Manager();
		manager.setIdManager(managerDTO.getIdManager());
		manager.setUser(userAdapter.convertToEntity(managerDTO.getUser()));
		manager.setStartDateTask(new Timestamp(managerDTO.getStartDate_task().getTime()));
		if(managerDTO.getEndDate_task() != null) {
			manager.setEndDateTask(new Timestamp(managerDTO.getEndDate_task().getTime()));
		}
		return manager;
	}

}
