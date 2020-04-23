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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Manager;
import it.myalert.exeption.ManagerExeption;
import it.myalert.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerRestController {
	
	@Autowired
	ManagerService managerService;
	
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ManagerDTO> getAll(){
		
		List<Manager> list = managerService.getAll();
		List<ManagerDTO> listDTO = new ArrayList<ManagerDTO>();
		
		Iterator<Manager> managerIT = list.iterator();
		
		while(managerIT.hasNext()) {
			
			listDTO.add(managerService.convertToDTO(managerIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	
	@GetMapping(value="/getManagerById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ManagerDTO getManagerById(@PathVariable int id) throws ManagerExeption{
		
		Manager manager = managerService.getById(id);
		ManagerDTO managerDTO = managerService.convertToDTO(manager);
		return managerDTO;
		
	}
	
	@PostMapping(value="/addAgent", consumes = Media>Type.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public 

}
