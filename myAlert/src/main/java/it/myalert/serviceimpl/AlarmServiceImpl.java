package it.myalert.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.AlarmAdapter;
import it.myalert.entity.Alarm;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AlarmExeption;
import it.myalert.repository.AlarmRepository;
import it.myalert.service.AlarmService;
import it.myalert.service.CitizenService;

@Service
@Transactional(rollbackOn = AlarmExeption.class)
public class AlarmServiceImpl extends AlarmAdapter implements AlarmService {

	@Autowired
	private AlarmRepository alarmRepository;
	@Autowired
	private CitizenService citizenService;
	
	@Override
	public List<Alarm> getAll() {
		
		return alarmRepository.findAll();
	}

	@Override
	public Alarm getAlarmById(int idAlarm) throws AlarmExeption {
		
		return this.alarmRepository.findById(idAlarm).orElseThrow(()-> new AlarmExeption("ERROR: No alarm found with id:"+ idAlarm));
	}

	@Override
	public Alarm addAlarm(Alarm alarm) throws AlarmExeption {
		alarm.setIdAlarm(null);
		return alarmRepository.save(alarm);
	}

}