package it.myalert.restcontroller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.myalert.DTO.AlarmDTO;
import it.myalert.DTO.ImageDTO;
import it.myalert.DTO.InterventionDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.ResponseBean;
import it.myalert.DTO.TypeDTO;
import it.myalert.DTO.UserDTO;
import it.myalert.entity.Alarm;
import it.myalert.entity.Citizen;
import it.myalert.entity.Image;
import it.myalert.entity.Intervention;
import it.myalert.entity.Type;
import it.myalert.entity.User;
import it.myalert.exeption.AlarmExeption;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ImageExeption;
import it.myalert.exeption.InterventionExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.exeption.UserExeption;
import it.myalert.service.ImageService;
import it.myalert.service.InterventionService;
import it.myalert.service.UserService;

@RestController
@RequestMapping("/image")
public class ImageRestController {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	InterventionService interventionService;
	
	//------------------GET ALL IMAGE------------------------------------
	@GetMapping(value="/getAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ImageDTO> getAll(){
		
		List<Image> list = imageService.getAll();
		List<ImageDTO> listDTO = new ArrayList<ImageDTO>();
		System.out.println("Utenti:"+list);
		Iterator<Image> imageIT = list.iterator();
		
		while(imageIT.hasNext()) {
			
			listDTO.add(imageService.convertToDTO(imageIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	//-----------------GET ALL IMAGE BY idIntervention ----------------------------------------
		@GetMapping(value="/getAllImageByIdIntervention/{idIntervention}", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<ImageDTO> getAllImageByIdIntervention(@PathVariable("idIntervention") int idIntervention) throws InterventionExeption{
			
			Iterator<Image> listIT = imageService.getAllImageByIdIntervention(idIntervention).iterator();
			List<ImageDTO> listDTO = new ArrayList<ImageDTO>();
			
			while(listIT.hasNext()) {
				listDTO.add(imageService.convertToDTO(listIT.next()));
			}
			return listDTO;
			
		}
		
	//----------------- GET IMAGE BY IDINTERVENTION AND IDUSER ----------------------------------------
	@GetMapping(value="/getImage", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ImageDTO> getImage(@RequestParam("idIntervention") int idIntervention, @RequestParam("idUser") int idUser) throws InterventionExeption, UserException {

		//get image by idIntervention & idUser
		List<ImageDTO> listDTO = new ArrayList<ImageDTO>();
		
		Iterator<Image> imageIT = imageService.findByIntervention_idInterventionAndUser_idUser(idIntervention, idUser).iterator();
		
		while(imageIT.hasNext()) {
			
			listDTO.add(imageService.convertToDTO(imageIT.next())); //chiamiamo il service per convertire da entities a DTO
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	//-----------------ADD IMAGE ----------------------------------------
	@PostMapping(value="/addImage", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ImageDTO> postImage(@RequestParam("idUser") int idUser, @RequestParam("idIntervention") int idIntervention, @RequestBody List<ImageDTO> listDTO) throws InterventionExeption, UserExeption {
		
		Iterator<ImageDTO> listDTOIterator = listDTO.iterator();
		List<ImageDTO> newlistDTO = new ArrayList<ImageDTO>();
		
		while(listDTOIterator.hasNext()) {
			
			ImageDTO imageDTO = listDTOIterator.next();
			UserDTO userDTO = userService.convertToDTO(userService.getUserById(idUser));
			InterventionDTO interventionDTO = interventionService.convertToDTO(interventionService.getById(idIntervention));
			imageDTO.setUser(userDTO);
			imageDTO.setIntervention(interventionDTO);
			
			Image image = imageService.addImage(imageService.convertToEntity(imageDTO));
			newlistDTO.add(imageService.convertToDTO(image));
	
		}

		return newlistDTO;
	}
	
	//-----------------DELETE  IMAGE ----------------------------------------
	@DeleteMapping(value="/deleteImage/{idImage}")
	public ResponseBean deleteImage(@PathVariable("idImage") int idImage) throws ImageExeption {	
		
		Boolean status= false;
		try {
			status = this.imageService.deleteImage(idImage);
			return ResponseBean.okResponse(status);	
		}catch (Exception e) {
			return ResponseBean.koResponseBean(status, e.getMessage());
		}
	}
}
