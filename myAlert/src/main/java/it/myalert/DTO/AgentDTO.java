package it.myalert.DTO;

import java.util.Date;

public class AgentDTO{

	private Integer idAgent;
	private UserDTO user;
	private String department;
	private String department_Code;
	private String lat;
	private String lon;
	private Date startDate_task;
	private Date endDate_task;
	private ManagerDTO manager;
	
	
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
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
	public Integer getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}
	public UserDTO getUserDTO() {
		return user;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.user = userDTO;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartment_Code() {
		return department_Code;
	}
	public void setDepartment_Code(String department_Code) {
		this.department_Code = department_Code;
	}
	public ManagerDTO getManagerDTO() {
		return manager;
	}
	public void setManagerDTO(ManagerDTO managerDTO) {
		this.manager = managerDTO;
	}
	
	
	
	
}
