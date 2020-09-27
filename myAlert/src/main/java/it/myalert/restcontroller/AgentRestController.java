package it.myalert.restcontroller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import it.myalert.exeption.TypeExeption;
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
		public AgentDTO getAgentById(@PathVariable("idAgent") int idAgent) throws AgentExeption{
			
			Agent agent = this.agentService.getAgentById(idAgent);
			return this.agentService.convertToDTO(agent);	
	
		}
		
		//------------------GET AGENT FROM coords AND distance ------------------------------------
		@GetMapping(value="/getAgentByPosition", produces=MediaType.APPLICATION_JSON_VALUE)
		public List<AgentDTO> getAgentByPosition(@RequestParam("lat") double lat, @RequestParam("lon") double lon, @RequestParam("distance") int distance) throws AgentExeption{
			
			List<AgentDTO> listDTO = new ArrayList<AgentDTO>();
			Double dis = Double.valueOf(distance);
			Iterator<Agent> agentIT = this.agentService.getAll().iterator();
			while(agentIT.hasNext()) {
				Agent agent = agentIT.next();
				//calc distance between agent position and coords of intervention passed as argument
				Double distanceCalc = this.distance(agent.getLat(), agent.getLon(), lat, lon);
				Double distance2 = this.distance2(agent.getLat(), agent.getLon(), lat, lon, 'K');
				System.out.print("\n\ndistance= "+distance);
				System.out.print("\ndistance2= "+distance2);
				if(distance2 < dis) {
					//add agent to list
					listDTO.add(this.agentService.convertToDTO(agent));
				}
			}
			
			return listDTO;
	
		}
				
		
		//-----------------ADD AGENT FROM idManager----------------------------------------
		@PostMapping(value="/addAgent/{idManager}", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public AgentDTO post(@RequestBody AgentDTO agentDTO, @PathVariable("idManager") int idManager) throws AgentExeption, ManagerExeption {
			ManagerDTO managerDTO = managerService.convertToDTO(managerService.getById(idManager));
			agentDTO.setManagerDTO(managerDTO);
			
			Agent agent = this.agentService.addAgent(agentService.convertToEntity(agentDTO), idManager);
			return this.agentService.convertToDTO(agent);

		}
		
		//-----------------UPDATE POSITION AGENT ----------------------------------------
		@PutMapping(value="/updatePosition/{idAgent}", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public AgentDTO updatePosition(@PathVariable("idAgent") int idAgent, @RequestParam("lat") Double lat, @RequestParam("lon") Double lon) throws AgentExeption {
				Agent agent = this.agentService.updatePosition(lat, lon, idAgent);
				return this.agentService.convertToDTO(agent);
		}
		
		//-----------------UPDATE  AGENT ----------------------------------------
		@PutMapping(value="/updateAgent/{idAgent}", produces = MediaType.APPLICATION_JSON_VALUE)
		public AgentDTO updateAgent(@RequestBody AgentDTO agentDTO, @PathVariable("idAgent") int idAgent) throws AgentExeption {
			//agentDTO.setManagerDTO(this.managerService.convertToDTO(this.agentService.getAgentById(idAgent).getManager())); 
			return this.agentService.convertToDTO(agentService.updateAgent(agentService.convertToEntity(agentDTO), idAgent));
		}
		
		//-----------------DELETE  AGENT ----------------------------------------
		@DeleteMapping(value="/deleteAgent/{idAgent}", produces = MediaType.APPLICATION_JSON_VALUE)
		public AgentDTO deleteType(@PathVariable("idAgent") int idAgent) throws AgentExeption {	
			
			Agent agent = this.agentService.getAgentById(idAgent);
			agent.setEndDateTask(new Timestamp(new Date().getTime()));
			
			return this.agentService.convertToDTO(this.agentService.updateAgent(agent, idAgent));
			
		}
		
		
		
		// GET DISTANCE FROM 2 COORDS (1 from DTO, 1 from same intervention)
		private double distance(Double lat1_s, Double lon1_s, Double lat2_s, Double lon2_s) {
			

			if ((lat1_s.compareTo(lat2_s) == 0) && (lon1_s.compareTo(lon2_s)== 0)) {
				return 0;
			}else if(lat1_s == null || lon1_s == null) {
				return 1000000;
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
		
		
		private double distance2(Double lat1, Double lon1, Double lat2, Double lon2, char unit) {
			
			if ((lat1.compareTo(lat2) == 0) && (lon1.compareTo(lon2)== 0)) {
				return 0;
			} else if(lat1 == null || lon1 == null) {
				return 1000000;
			} else{
				double theta = lon1 - lon2;
			      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
			      dist = Math.acos(dist);
			      dist = rad2deg(dist);
			      dist = dist * 60 * 1.1515;
			      if (unit == 'K') {
			        dist = dist * 1.609344;
			      } else if (unit == 'N') {
			        dist = dist * 0.8684;
			      }
			      return (dist);
			}
		      
		      
		 }
		    
		    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		    /*::  This function converts decimal degrees to radians             :*/
		    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		    private double deg2rad(double deg) {
		      return (deg * Math.PI / 180.0);
		    }
		    
		    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		    /*::  This function converts radians to decimal degrees             :*/
		    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		    private double rad2deg(double rad) {
		      return (rad * 180.0 / Math.PI);
		    }

}
