package it.myalert.restcontroller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.TypeDTO;
import it.myalert.entity.Alarm;
import it.myalert.entity.Citizen;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.entity.Type;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.service.AlarmService;
import it.myalert.service.CitizenService;
import it.myalert.service.InterventionService;
import it.myalert.service.TypeService;

@RestController
@RequestMapping("/intervention")
public class InterventionRestController {
	
	@Autowired
	private InterventionService interventionService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private CitizenService citizenService;
	@Autowired
	private AlarmService alarmService;
	
	//-----------------GET ALL INTERVENTION -------------------------------
	@GetMapping(value="/getAllIntervention", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InterventionDTO> getAll(){
		
		// List<Intervention> list = interventionService.getAll();
		List<InterventionDTO> listDTO = new ArrayList<InterventionDTO>();
		
		Iterator<Intervention> interventionIT = interventionService.getAll().iterator();
		
		while(interventionIT.hasNext()) {
			
			listDTO.add(interventionService.convertToDTO(interventionIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	
	//-----------------GET INTERVENTION BY idIntervention ----------------------------------------
	@GetMapping(value="/getInterventionById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public InterventionDTO getInterventionById(@PathVariable("id") int id) throws InterventionExeption{
		
		Intervention intervention = interventionService.getById(id);
		return interventionService.convertToDTO(intervention);
		
	}
	
	
	//-----------------ADD INTERVENTION ----------------------------------------
	@PostMapping(value="/addIntervention", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public InterventionDTO post(@RequestBody InterventionDTO interventionDTO, @RequestParam("idType") int idType, @RequestParam("idCitizen") int idCitizen) throws TypeExeption, InterventionExeption, CitizenExeption, AlarmExeption {
		
			
		int limit = 50; //mt
		interventionDTO.setType(typeService.convertToDTO(typeService.getTypeById(idType)));
		Intervention intervention = new Intervention();


		//get intervention by status & type
		Iterator<Intervention> listInterventionIT = interventionService.getAllInterventionByStatusAndType(idType, "signaled").iterator();
		
		while(listInterventionIT.hasNext()) {
			Intervention InterventionIT = listInterventionIT.next();
			//calc dist from current intervention and listInterventionIT already saved
			Double distance = this.distance(interventionDTO.getLat(), interventionDTO.getLon(), InterventionIT.getLat(), InterventionIT.getLon());
			if( distance > limit) {
				System.out.print("distace > 50 mt: "+ distance+ "for intervention with ID "+ InterventionIT.getIdIntervention());
				intervention = interventionService.addIntervention(interventionService.convertToEntity(interventionDTO));
			}else {
				intervention = InterventionIT;
			}

		}
		//add new alarm to DB
		Citizen citizen = citizenService.getCitizenById(idCitizen);
		Alarm alarm = new Alarm();
		alarm.setCitizen(citizen);
		alarm.setIntervention(intervention);
		alarm.setAlarmDate(new Timestamp(new Date().getTime()));
		alarmService.addAlarm(alarm);
		
		return interventionService.convertToDTO(intervention);
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
	
	//-----------------GET INTERVENTION BY status ----------------------------------------
	@GetMapping(value="/getInterventionByStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InterventionDTO> getByStatusOrderByStartdate(@PathVariable("status") String status) throws InterventionExeption{
		
		Iterator<Intervention> intervention = interventionService.getByStatusOrderByStartdate(status).iterator();
		List<InterventionDTO> listDTO = new ArrayList<InterventionDTO>();
		
		while(intervention.hasNext()) {
			
			listDTO.add(interventionService.convertToDTO(intervention.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
		
	}
			
	//-----------------UPDATE  INTERVENTION ----------------------------------------
	@PutMapping(value="/updateIntervention/{idIntervention}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public InterventionDTO updateIntervention(@PathVariable("idIntervention") int idIntervention, @RequestBody InterventionDTO interventionDTO, @RequestParam("idType")int idType) throws InterventionExeption, TypeExeption {	
		
		
		interventionDTO.setIdIntervention(idIntervention);
		interventionDTO.setType(typeService.convertToDTO(typeService.getTypeById(idType)));
		Intervention intervention = interventionService.convertToEntity(interventionDTO);
		
		return interventionService.convertToDTO(interventionService.updateIntervention(intervention));
	}

}
