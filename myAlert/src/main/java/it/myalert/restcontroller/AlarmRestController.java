package it.myalert.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.AlarmDTO;
import it.myalert.DTO.CitizenDTO;
import it.myalert.entity.Agent;
import it.myalert.entity.Alarm;
import it.myalert.entity.Citizen;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.service.AlarmService;

@RestController
@RequestMapping("/alarm")
public class AlarmRestController {

	@Autowired
	private AlarmService alarmService;
	
	//------------------GET ALL ALARM------------------------------------
	@GetMapping(value="/getAllAlarm", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AlarmDTO> getAll(){
		
		List<Alarm> list = alarmService.getAll();
		List<AlarmDTO> listDTO = new ArrayList<AlarmDTO>();
		Iterator<Alarm> alarmIT = list.iterator();
		
		while(alarmIT.hasNext()) {
			
			listDTO.add(alarmService.convertToDTO(alarmIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	//-----------------GET ALARM BY idAlarm ----------------------------------------
	@GetMapping(value="/getAlarmById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AlarmDTO getAlarmById(@PathVariable("id") int id) throws AlarmExeption{
		
		Alarm alarm = alarmService.getAlarmById(id);
		return alarmService.convertToDTO(alarm);
		
	}
	
}
