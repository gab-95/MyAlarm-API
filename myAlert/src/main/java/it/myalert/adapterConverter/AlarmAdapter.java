package it.myalert.adapterConverter;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import it.myalert.DTO.AlarmDTO;
import it.myalert.entity.Alarm;
import it.myalert.entity.Intervention;

public class AlarmAdapter implements Converter<AlarmDTO, Alarm> {

	@Autowired
	private CitizenAdapter citizenAdapter;
	@Autowired
	private InterventionAdapter interventionAdapter;
	
	@Override
	public AlarmDTO convertToDTO(Alarm alarm) {

		AlarmDTO alarmDTO = new AlarmDTO();
		alarmDTO.setIdAlarm(alarm.getIdAlarm());
		alarmDTO.setCitizen(citizenAdapter.convertToDTO(alarm.getCitizen()));
		alarmDTO.setIntervention(interventionAdapter.convertToDTO(alarm.getIntervention()));
		alarmDTO.setAlarmDate(new Date(alarm.getAlarmDate().getTime()));
		alarmDTO.setTitle(alarm.getTitle());
		alarmDTO.setDescription(alarm.getDescription());
		return alarmDTO;
	}

	@Override
	public Alarm convertToEntity(AlarmDTO alarmDTO) {

		Alarm alarm = new Alarm();
		alarm.setIdAlarm(alarmDTO.getIdAlarm());
		alarm.setCitizen(citizenAdapter.convertToEntity(alarmDTO.getCitizen()));
		alarm.setIntervention(interventionAdapter.convertToEntity(alarmDTO.getIntervention()));
		alarm.setAlarmDate(new Timestamp(alarmDTO.getAlarmDate().getTime()));
		alarm.setTitle(alarmDTO.getTitle());
		alarm.setDescription(alarmDTO.getDescription());
		return alarm;
	}

}
