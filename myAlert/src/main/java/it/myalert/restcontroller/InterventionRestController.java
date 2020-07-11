package it.myalert.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Citizen;
import it.myalert.entity.Intervention;
import it.myalert.entity.Manager;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.service.InterventionService;

@RestController
@RequestMapping("/intervention")
public class InterventionRestController {
	
	@Autowired
	private InterventionService interventionService;
	
	
	//-----------------GET ALL INTERVENTION -------------------------------
	@GetMapping(value="/getAllIntervention", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<InterventionDTO> getAll(){
		
		List<Intervention> list = interventionService.getAll();
		List<InterventionDTO> listDTO = new ArrayList<InterventionDTO>();
		
		Iterator<Intervention> interventionIT = list.iterator();
		
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
	public InterventionDTO getCitizenById(@PathVariable("id") int id) throws InterventionExeption{
		
		Intervention intervention = interventionService.getById(id);
		return interventionService.convertToDTO(intervention);
		
	}

}
