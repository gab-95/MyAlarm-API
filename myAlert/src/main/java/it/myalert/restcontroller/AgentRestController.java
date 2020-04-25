package it.myalert.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.xdevapi.JsonArray;

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Agent;
import it.myalert.entity.Manager;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.service.AgentService;
import it.myalert.service.ManagerService;

@RestController
@RequestMapping("/agent")
public class AgentRestController {

	
		@Autowired
		private AgentService agentService;
		@Autowired
		private ManagerService managerService;
	
		//------------------GET ALL AGENT------------------------------------
		@GetMapping(value="/getAllAgent", produces=MediaType.APPLICATION_JSON_VALUE)
		public List<AgentDTO> getAll(){
			
			List<Agent> list = agentService.getAll();
			List<AgentDTO> listDTO = new ArrayList<AgentDTO>();
			Iterator<Agent> utenteIT = list.iterator();
			
			while(utenteIT.hasNext()) {
				
				listDTO.add(agentService.convertToDTO(utenteIT.next()));
			}
			
			if(listDTO.isEmpty()) {
				return Collections.emptyList();
			}
			
			return listDTO;
		}
		
		
		//-----------------ADD AGENT----------------------------------------
		@PostMapping(value="/addAgent", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public Agent post(@RequestBody AgentDTO agentDTO, @RequestParam("id") int idManager) throws AgentExeption, ManagerExeption {
			System.out.println("Agent"+agentDTO.toString());
			ManagerDTO managerDTO = managerService.convertToDTO(managerService.getById(idManager));
			agentDTO.setManagerDTO(managerDTO);
			agentService.addAgent(agentService.convertToEntity(agentDTO), idManager);
			return null;
			//return agentService.addAgent(agentService.convertToEntity(agentDTO), idManager);
		}
}