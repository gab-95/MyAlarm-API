package it.myalert.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import it.myalert.DTO.AgentDTO;
import it.myalert.DTO.AlarmDTO;
import it.myalert.DTO.AssignDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Alarm;
import it.myalert.entity.Assign;
import it.myalert.entity.Intervention;
import it.myalert.exeption.AgentExeption;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.AssignExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.service.AgentService;
import it.myalert.service.AssignService;
import it.myalert.service.InterventionService;
import it.myalert.service.ManagerService;

@RestController
@RequestMapping("/assign")
public class AssignRestController {

	@Autowired
	private AssignService assignService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private InterventionService interventionService;
	
	//------------------GET ALL ASSIGN ------------------------------------
	@GetMapping(value="/getAllAssign", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<AssignDTO> getAll(){
		
		List<AssignDTO> listDTO = new ArrayList<AssignDTO>();
		Iterator<Assign> alarmIT = this.assignService.getAll().iterator();
		
		while(alarmIT.hasNext()) {
			
			listDTO.add(this.assignService.convertToDTO(alarmIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	//-----------------GET ASSIGN BY idAssign ----------------------------------------
	@GetMapping(value="/getAssignById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignDTO getAssignById(@PathVariable("id") int id) throws AssignExeption{
		
		Assign assign = this.assignService.getAssignById(id);
		return this.assignService.convertToDTO(assign);
		
	}
	
	//-----------------GET ALL ASSIGN BY idAgent ----------------------------------------
	@GetMapping(value="/getAllAssignByIdAgent/{idAgent}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AssignDTO> getAllAlarmByIdIntervention(@PathVariable("idAgent") int idAgent) throws AgentExeption{
		
		Iterator<Assign> listIT = this.assignService.getAssignByIdAgent(idAgent).iterator();
		List<AssignDTO> listDTO = new ArrayList<AssignDTO>();
		
		while(listIT.hasNext()) {
			listDTO.add(this.assignService.convertToDTO(listIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
		
	}
	
	//-----------------ASSIGN AGENT TO INTERVENTION ----------------------------------------
	@PostMapping(value="/assignAgentToIntervention/{idManager}", consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignDTO getAllAlarmByIdIntervention(@RequestBody AssignDTO assignDTO,@PathVariable("idManager") int idManager ,@RequestParam("idAgent") int idAgent, @RequestParam("idIntervention") int idIntervention) throws AgentExeption, ManagerExeption, InterventionExeption{
		
		ManagerDTO managerDTO = this.managerService.convertToDTO(this.managerService.getById(idManager));
		AgentDTO agentDTO = this.agentService.convertToDTO(this.agentService.getAgentById(idAgent));
		InterventionDTO interventionDTO = this.interventionService.convertToDTO(this.interventionService.getById(idIntervention));
		assignDTO.setManager(managerDTO);
		assignDTO.setAgent(agentDTO);
		interventionDTO.setStatus("assigned");
		interventionDTO.setStartDate(new Date());
		assignDTO.setIntervention(interventionDTO);
		Assign assign = this.assignService.convertToEntity(assignDTO);
		//update intervention status & start date
		this.interventionService.updateIntervention(this.interventionService.convertToEntity(interventionDTO));
		
		
		return this.assignService.convertToDTO(this.assignService.assignAgentToIntervention(assign));
		
	}
	
	//-----------------UPDATE  ASSIGN ----------------------------------------
	@PutMapping(value="/updateAssign/{idAssign}", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignDTO updateIntervention(@PathVariable("idAssign") int idIntervention, @RequestBody AssignDTO assignDTO) throws AssignExeption {	

		Assign assign = this.assignService.convertToEntity(assignDTO);
		
		return this.assignService.convertToDTO(this.assignService.updateAssign(assign));
	}
}
