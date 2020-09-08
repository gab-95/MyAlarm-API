package it.myalert.restcontroller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Agent;
import it.myalert.entity.Citizen;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.UserExeption;
import it.myalert.service.NotificationService;
import it.myalert.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	
	//------------------GET ALL USERS------------------------------------
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getAll(){
		
		this.notificationService.sendNotification("aaa", "message");
		
		List<User> list = userService.getAll();
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		System.out.println("Utenti:"+list);
		Iterator<User> utenteIT = list.iterator();
		
		while(utenteIT.hasNext()) {
			
			listDTO.add(userService.convertToDTO(utenteIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	//------------------GET USER BY EMAIL------------------------------------
	@GetMapping(value="/getByEmail/", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getUserByEmail(@RequestParam("email") String email) throws UserExeption, AgentExeption, ManagerExeption, CitizenExeption{
		System.out.print("parameter:" + email);
		return this.userService.convertToDTOcustom(this.userService.getUserByEmail(email));
	}
	
	//-----------------GET USER BY IDUSER ----------------------------------------
	@GetMapping(value="/getUserById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDTO getUserById(@PathVariable int id) throws UserExeption {
		
		User user = userService.getUserById(id);
		UserDTO userDTO = userService.convertToDTO(user);
		return userDTO;
		
	}
	

}
