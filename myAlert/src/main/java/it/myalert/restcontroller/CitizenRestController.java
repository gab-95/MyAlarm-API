package it.myalert.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.ResponseBean;
import it.myalert.entity.Citizen;
import it.myalert.entity.Manager;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.service.CitizenService;

@RestController
@RequestMapping("/citizen")
public class CitizenRestController {
	
	@Autowired
	CitizenService citizenService;
	
	// ---------------GET ALL CITIZEN ----------------------------
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CitizenDTO> getAll(){
		
		List<Citizen> list = citizenService.getAll();
		List<CitizenDTO> listDTO = new ArrayList<CitizenDTO>();
		
		Iterator<Citizen> citizenIT = list.iterator();
		
		while(citizenIT.hasNext()) {
			
			listDTO.add(citizenService.convertToDTO(citizenIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	
	//-----------------GET CITIZEN BY idCitizen ----------------------------------------
	@GetMapping(value="/getCitizenById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CitizenDTO getCitizenById(@PathVariable int id) throws CitizenExeption{
		
		Citizen citizen = citizenService.getCitizenById(id);
		CitizenDTO citizenDTO = citizenService.convertToDTO(citizen);
		return citizenDTO;
		
	}
	
	//-----------------ADD CITIZEN ----------------------------------------
	@PostMapping(value="/addCitizen", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CitizenDTO post(@RequestBody CitizenDTO citizenDTO) throws CitizenExeption {
		
			Citizen citizen = citizenService.addCitizen(citizenService.convertToEntity(citizenDTO));
			return citizenService.convertToDTO(citizen);
	}
	
	
	//-----------------UPDATE POSITION CITIZEN ----------------------------------------
	@PutMapping(value="/updatePosition/{idCitizen}", produces = MediaType.APPLICATION_JSON_VALUE)
	public CitizenDTO updatePosition(@PathVariable("idCitizen") int idCitizen, @RequestParam("lat") String lat, @RequestParam("lon") String lon) throws CitizenExeption {
			System.out.print("parameter:" + idCitizen + lat + lon);
			Citizen citizen = citizenService.updatePosition(lat, lon, idCitizen);
			return citizenService.convertToDTO(citizen);
	}
	
	
	//-----------------UPDATE  CITIZEN ----------------------------------------
		@PutMapping(value="/updateCitizen/{idCitizen}", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public CitizenDTO updateCitizen(@RequestBody CitizenDTO citizenDTO, @PathVariable("idCitizen") int idCitizen) throws CitizenExeption {
				System.out.print("parameter:" + citizenDTO.toString());
				Citizen citizen = citizenService.updateCitizen(citizenService.convertToEntity(citizenDTO), idCitizen);
				return citizenService.convertToDTO(citizen);
		}

}
