package it.myalert.DTO;



public class ImageDTO {

	private int idImage;
	private String url;
	private Double lat;
	private Double lon;
	private UserDTO user;
	private InterventionDTO intervention;
	
	
	public int getIdImage() {
		return idImage;
	}
	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public InterventionDTO getIntervention() {
		return intervention;
	}
	public void setIntervention(InterventionDTO intervention) {
		this.intervention = intervention;
	}
	
	
	
}
