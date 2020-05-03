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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.a.TextRowFactory;

import it.myalert.DTO.UserDTO;
import it.myalert.entity.User;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.UserExeption;
import it.myalert.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	//------------------GET ALL USERS------------------------------------
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserDTO> getAll(){
		
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
	
	//------------------GET ALL USERS------------------------------------
	@GetMapping(value="/getByEmail/", produces=MediaType.APPLICATION_JSON_VALUE)
	public Object getUserByEmail(@RequestParam("email") String email) throws UserExeption, AgentExeption, ManagerExeption, CitizenExeption{
		System.out.print("parameter:" + email);
		return this.userService.convertToDTOcustom(this.userService.getUserByEmail(email));
	}
	

}
