package it.myalert.DTO;

public class CitizenDTO {

	private UserDTO user;
	private int idCitizen;
	private String lat;
	private String lon;
	
	
	public UserDTO getUserDTO() {
		return user;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.user = userDTO;
	}
	public int getIdCitizen() {
		return idCitizen;
	}
	public void setIdCitizen(int idCitizen) {
		this.idCitizen = idCitizen;
	}
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

	
	
	
	
}
