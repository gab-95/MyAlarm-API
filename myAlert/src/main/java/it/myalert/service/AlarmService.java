package it.myalert.service;

import java.util.List;

import it.myalert.DTO.AlarmDTO;
import it.myalert.adapterConverter.Converter;
import it.myalert.entity.Alarm;
import it.myalert.exeption.AlarmExeption;

public interface AlarmService extends Converter<AlarmDTO, Alarm> {

	public List<Alarm> getAll();
	public Alarm getAlarmById(int idAlarm) throws AlarmExeption;
	public Alarm addAlarm(Alarm alarm) throws AlarmExeption;
}
