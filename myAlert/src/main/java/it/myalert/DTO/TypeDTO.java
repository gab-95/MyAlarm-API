package it.myalert.DTO;

public class TypeDTO {
	
	private Integer idType;
	private String name;
	private ManagerDTO managerDTO;
	
	
	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ManagerDTO getManagerDTO() {
		return managerDTO;
	}
	public void setManagerDTO(ManagerDTO managerDTO) {
		this.managerDTO = managerDTO;
	}
	

}
