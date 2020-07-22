package it.myalert.DTO;

import java.util.Date;

public class ManagerDTO {
	
	private UserDTO user;
	private int idManager;
	private Date startDate_task;
	private Date endDate_task;
	
	
	public int getIdManager() {
		return idManager;
	}

	public void setIdManager(int idManager) {
		this.idManager = idManager;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public Date getStartDate_task() {
		return startDate_task;
	}

	public void setStartDate_task(Date startDate_task) {
		this.startDate_task = startDate_task;
	}

	public Date getEndDate_task() {
		return endDate_task;
	}

	public void setEndDate_task(Date endDate_task) {
		this.endDate_task = endDate_task;
	}



}
