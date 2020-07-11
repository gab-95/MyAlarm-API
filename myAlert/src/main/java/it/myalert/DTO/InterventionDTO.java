package it.myalert.DTO;

import java.util.Date;

public class InterventionDTO {
	
	private int idIntervention;
	private TypeDTO type;
	private String lat;
	private String lon;
	private String address;
	private String city;
	private Date startDate;
	private Date endDate;
	private String shortReport;
	private String detailedReport;
	private String status;
	
	
	public int getIdIntervention() {
		return idIntervention;
	}
	public void setIdIntervention(int idIntervention) {
		this.idIntervention = idIntervention;
	}
	public TypeDTO getType() {
		return type;
	}
	public void setType(TypeDTO type) {
		this.type = type;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getShortReport() {
		return shortReport;
	}
	public void setShortReport(String shortReport) {
		this.shortReport = shortReport;
	}
	public String getDetailedReport() {
		return detailedReport;
	}
	public void setDetailedReport(String detailedReport) {
		this.detailedReport = detailedReport;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
