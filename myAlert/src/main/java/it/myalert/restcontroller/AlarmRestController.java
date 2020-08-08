package it.myalert.restcontroller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.aspectj.weaver.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.AlarmDTO;
import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ResponseBean;
import it.myalert.entity.Agent;
import it.myalert.entity.Alarm;
import it.myalert.entity.Citizen;
import it.myalert.entity.Intervention;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ImageExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.repository.ImageRepository;
import it.myalert.service.AlarmService;
import it.myalert.service.CitizenService;
import it.myalert.service.ImageService;
import it.myalert.service.InterventionService;
import it.myalert.service.TypeService;

@RestController
@RequestMapping("/alarm")
public class AlarmRestController {

	@Autowired
	private AlarmService alarmService;
	@Autowired
	private InterventionService interventionService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private ImageService imageService;
	
	//------------------GET ALL ALARM------------------------------------
	@GetMapping(value="/getAllAlarm", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AlarmDTO> getAll(){
		
		List<AlarmDTO> listDTO = new ArrayList<AlarmDTO>();
		Iterator<Alarm> alarmIT = alarmService.getAll().iterator();
		
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
	
	//-----------------GET ALL ALARM BY idIntervention ----------------------------------------
	@GetMapping(value="/getAllAlarmByIdIntervention/{idIntervention}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlarmDTO> getAllAlarmByIdIntervention(@PathVariable("idIntervention") int idIntervention) throws InterventionExeption{
		
		Iterator<Alarm> listIT = alarmService.getAllAlarmByIdIntervention(idIntervention).iterator();
		List<AlarmDTO> listDTO = new ArrayList<AlarmDTO>();
		
		while(listIT.hasNext()) {
			listDTO.add(alarmService.convertToDTO(listIT.next()));
		}
		return listDTO;
		
	}
	
	//-----------------GET ALL ALARM BY idCitizen ----------------------------------------
	@GetMapping(value="/getAllAlarmByIdCitizen/{idCitizen}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AlarmDTO> getAllAlarmByIdCitizen(@PathVariable("idCitizen") int idCitizen) throws CitizenExeption{
		
		Iterator<Alarm> listIT = alarmService.getAllAlarmByIdCitizen(idCitizen).iterator();
		List<AlarmDTO> listDTO = new ArrayList<AlarmDTO>();
		
		while(listIT.hasNext()) {
			listDTO.add(alarmService.convertToDTO(listIT.next()));
		}
		return listDTO;
		
	}
	
	//-----------------ADD ALARM ----------------------------------------
	@PostMapping(value="/addAlarm", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AlarmDTO post(@RequestBody AlarmDTO alarmDTO, @RequestParam("idType") int idType, @RequestParam("idCitizen") int idCitizen) throws TypeExeption, InterventionExeption, CitizenExeption, AlarmExeption {
		
		InterventionDTO interventionDTO = alarmDTO.getIntervention();	
		int limit = 50; //mt
		interventionDTO.setType(typeService.convertToDTO(typeService.getTypeById(idType)));
		Intervention intervention = new Intervention();


		//get intervention by status & type
		List<Intervention> list= interventionService.getAllInterventionByStatusAndType(idType, "signaled");
		//check if not exist other intervention
		if(list.size() == 0) {
			interventionDTO.setCount(1);
			intervention = interventionService.addIntervention(interventionService.convertToEntity(interventionDTO));
		}
		else {
			Iterator<Intervention> listInterventionIT = list.iterator();
			while(listInterventionIT.hasNext()) {
				Intervention InterventionIT = listInterventionIT.next();
				//calc dist from current intervention and listInterventionIT already saved
				Double distance = this.distance(interventionDTO.getLat(), interventionDTO.getLon(), InterventionIT.getLat(), InterventionIT.getLon());
				if( distance > limit) {
					System.out.print("distace > 50 mt: "+ distance+ "for intervention with ID "+ InterventionIT.getIdIntervention());
					interventionDTO.setCount(1);
					intervention = interventionService.addIntervention(interventionService.convertToEntity(interventionDTO));
				}else {
					InterventionIT.setCount(InterventionIT.getCount()+1);
					intervention = this.interventionService.updateIntervention(InterventionIT);
				}

			}
		}
		//add new alarm to DB
		alarmDTO.setCitizen(citizenService.convertToDTO(citizenService.getCitizenById(idCitizen)));
		alarmDTO.setIntervention(interventionService.convertToDTO(intervention));
		Alarm alarm = alarmService.addAlarm(alarmService.convertToEntity(alarmDTO));
		
		return alarmService.convertToDTO(alarm);
	}
	
	//-----------------DELETE ALARM ----------------------------------------
	@DeleteMapping(value="/deleteAlarm/{idAlarm}")
	public ResponseBean deleteAlarm(@PathVariable("idAlarm") int idAlarm) throws AlarmExeption {	
		AlarmDTO alarmDTO = this.getAlarmById(idAlarm);
		Iterator<AlarmDTO> listAlarmIT = this.getAll().iterator();
		
		Boolean canDeleteIntervention = true;
		while(listAlarmIT.hasNext()) {
			AlarmDTO alarmDTOIT = listAlarmIT.next();
			if(alarmDTO.getIntervention().getIdIntervention().equals(alarmDTOIT.getIntervention().getIdIntervention())) {
				canDeleteIntervention= false;
			}
		}
			
		
		Boolean status= false;
		try {
			if(canDeleteIntervention) {
				status = this.interventionService.deleteIntervention(alarmDTO.getIntervention().getIdIntervention());
			}
			// decrement count of intervention and update it
			alarmDTO.getIntervention().setCount(alarmDTO.getIntervention().getCount()-1);
			this.interventionService.updateIntervention(this.interventionService.convertToEntity(alarmDTO.getIntervention()));
			// delete alarm signaled by user
			status= this.alarmService.deleteAlarm(idAlarm);
			status = this.imageService.deleteImageByIdCitizen(alarmDTO.getCitizen().getUserDTO().getIdUser());
			return ResponseBean.okResponse(status);	
		}catch (Exception e) {
			return ResponseBean.koResponseBean(status, e.getMessage());
		}
	}
	
	// GET DISTANCE FROM 2 COORDS (1 from DTO, 1 from same intervention)
	private double distance(Double lat1_s, Double lon1_s, Double lat2_s, Double lon2_s) {
		

		if ((lat1_s.compareTo(lat2_s) == 0) && (lon1_s.compareTo(lon2_s)== 0)) {
			return 0;
		}
		else {
			double theta = lon1_s - lon2_s;
			double dist = Math.sin(Math.toRadians(lat1_s)) * Math.sin(Math.toRadians(lat2_s)) + Math.cos(Math.toRadians(lat1_s)) * Math.cos(Math.toRadians(lat2_s)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return (dist); //in mt
		}
	}
	
}
