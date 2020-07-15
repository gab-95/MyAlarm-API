package it.myalert.DTO;

import java.sql.Date;

public class AlarmDTO {
	
	private Integer idAlarm;
	private CitizenDTO citizen;
	private InterventionDTO intervention;
	private Date alarmDate;
	
	
	public Integer getIdAlarm() {
		return idAlarm;
	}
	public void setIdAlarm(Integer idAlarm) {
		this.idAlarm = idAlarm;
	}
	public CitizenDTO getCitizen() {
		return citizen;
	}
	public void setCitizen(CitizenDTO citizen) {
		this.citizen = citizen;
	}
	public InterventionDTO getIntervention() {
		return intervention;
	}
	public void setIntervention(InterventionDTO intervention) {
		this.intervention = intervention;
	}
	public Date getAlarmDate() {
		return alarmDate;
	}
	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}
	

}
