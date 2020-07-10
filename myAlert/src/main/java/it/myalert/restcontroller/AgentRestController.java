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

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.ResponseBean;
import it.myalert.entity.Agent;
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
		
		//------------------GET AGENT FROM idAgent------------------------------------
		@GetMapping(value="/getAgent/{idAgent}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseBean getAgentById(@PathVariable("idAgent") int idAgent){
			
			try {
				Agent agent = this.agentService.getAgentById(idAgent);
				return ResponseBean.okResponse(agentService.convertToDTO(agent));	
			}catch (Exception e) {
				return ResponseBean.koResponseBean(null, e.getMessage());
			}
			
			
		}
				
		
		//-----------------ADD AGENT FROM idManager----------------------------------------
		@PostMapping(value="/addAgent/{idManager}", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseBean post(@RequestBody AgentDTO agentDTO, @PathVariable("idManager") int idManager) throws AgentExeption, ManagerExeption {
			ManagerDTO managerDTO = managerService.convertToDTO(managerService.getById(idManager));
			agentDTO.setManagerDTO(managerDTO);
			
			try {
				Agent agent = agentService.addAgent(agentService.convertToEntity(agentDTO), idManager);
				return ResponseBean.okResponse(agent);
			} catch (DataIntegrityViolationException e) {
				return ResponseBean.koResponseBean(null, "Errore nell'inserimento");
			}catch (Exception e) {
				return ResponseBean.koResponseBean(null, "Errore non gestito");
			} 
		}
		
		//-----------------UPDATE POSITION AGENT ----------------------------------------
		@PutMapping(value="/updatePosition/{idAgent}", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public AgentDTO updatePosition(@PathVariable("idAgent") int idAgent, @RequestParam("lat") String lat, @RequestParam("lon") String lon) throws AgentExeption {
				Agent agent = agentService.updatePosition(lat, lon, idAgent);
				return agentService.convertToDTO(agent);
		}
		
		//-----------------UPDATE  AGENT ----------------------------------------
		@PutMapping(value="/updateAgent/{idAgent}", produces = MediaType.APPLICATION_JSON_VALUE)
		public AgentDTO updateManager(@RequestBody AgentDTO agentDTO, @PathVariable("idAgent") int idAgent) throws AgentExeption {
			System.out.print("parameter:" + agentDTO.toString());
			
			Agent agent = agentService.updateAgent(agentService.convertToEntity(agentDTO), idAgent);
			return agentService.convertToDTO(agent);
		}

}
