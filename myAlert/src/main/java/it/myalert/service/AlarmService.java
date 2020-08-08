package it.myalert.service;

import java.util.List;

import it.myalert.DTO.AlarmDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Alarm;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.InterventionExeption;

public interface AlarmService extends Converter<AlarmDTO, Alarm> {

	public List<Alarm> getAll();
	public Alarm getAlarmById(int idAlarm) throws AlarmExeption;
	public Alarm addAlarm(Alarm alarm) throws AlarmExeption;
	public List<Alarm> getAllAlarmByIdIntervention(int idIntervention) throws InterventionExeption;
	public List<Alarm> getAllAlarmByIdCitizen(int idCitizen) throws CitizenExeption;
	public Boolean deleteAlarm(int idAlarm) throws AlarmExeption;
	
}
