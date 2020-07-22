package it.myalert.DTO;

public class CitizenDTO {

	private UserDTO user;
	private int idCitizen;
	private Double lat;
	private Double lon;
	
	
	public UserDTO getUserDTO() {
		return user;
	}
	public void setUserDTO(UserDTO user) {
		this.user = user;
	}
	public int getIdCitizen() {
		return idCitizen;
	}
	public void setIdCitizen(int idCitizen) {
		this.idCitizen = idCitizen;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}

	
	
	
	
}
