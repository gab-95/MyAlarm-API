package it.myalert.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import it.myalert.DTO.CitizenDTO;
import it.myalert.DTO.ManagerDTO;
import it.myalert.DTO.ResponseBean;
import it.myalert.DTO.TypeDTO;
import it.myalert.entity.Agent;
import it.myalert.entity.Citizen;
import it.myalert.entity.Type;
import it.myalert.exeption.CitizenExeption;
import it.myalert.exeption.ManagerExeption;
import it.myalert.exeption.TypeExeption;
import it.myalert.service.ManagerService;
import it.myalert.service.TypeService;

@RestController
@RequestMapping("/type")
public class TypeRestController {
	
	@Autowired
	private TypeService typeService;
	@Autowired
	private ManagerService managerService;
	
	//------------------GET ALL Type------------------------------------
	@GetMapping(value="/getAllType", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TypeDTO> getAll(){
		
		List<Type> list = typeService.getAllType();
		List<TypeDTO> listDTO = new ArrayList<TypeDTO>();
		Iterator<Type> typeIT = list.iterator();
		
		while(typeIT.hasNext()) {
			
			listDTO.add(typeService.convertToDTO(typeIT.next()));
		}
		
		if(listDTO.isEmpty()) {
			return Collections.emptyList();
		}
		
		return listDTO;
	}
	
	
	//-----------------GET CITIZEN BY idCitizen ----------------------------------------
	@GetMapping(value="/getType/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TypeDTO getTypeById(@PathVariable int id) throws TypeExeption{
		
		Type type = typeService.getTypeById(id);
		return typeService.convertToDTO(type);
		
	}
	
		
	
	//-----------------ADD CITIZEN ----------------------------------------
	@PostMapping(value="/addType/{idManager}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TypeDTO post(@PathVariable("idManager") int idManager, @RequestParam("name") String name) throws ManagerExeption {
		
		TypeDTO typeDTO = new TypeDTO();
		ManagerDTO managerDTO = managerService.convertToDTO(managerService.getById(idManager));
		typeDTO.setManagerDTO(managerDTO);
		typeDTO.setName(name);
		
		Type type = typeService.addType(typeService.convertToEntity(typeDTO));
		return typeService.convertToDTO(type);
			
	}
	
	
	//-----------------UPDATE  TYPE ----------------------------------------
	@PutMapping(value="/updateType/{idType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TypeDTO updateType(@PathVariable("idType") int idType, @RequestParam("idManager") Optional<Integer> idManager, @RequestParam("name")String name) throws TypeExeption, ManagerExeption {	
		

		TypeDTO typeDTO = new TypeDTO();
		if(idManager.isPresent() ) {
			ManagerDTO managerDTO = managerService.convertToDTO(managerService.getById(idManager.get()));
			typeDTO.setManagerDTO(managerDTO);
		}else {
			ManagerDTO managerDTO = this.getTypeById(idType).getManagerDTO();
			typeDTO.setManagerDTO(managerDTO);
		}
		typeDTO.setIdType(idType);
		typeDTO.setName(name);
		
		Type type = typeService.updateType(typeService.convertToEntity(typeDTO));
		return typeService.convertToDTO(type);
	}
	
	
	//-----------------DELETE  TYPE ----------------------------------------
	@DeleteMapping(value="/deleteType/{idType}")
	public ResponseBean deleteType(@PathVariable("idType") int idType) throws TypeExeption {	
		
		Boolean status= false;
		try {
			status = this.typeService.deleteType(idType);
			return ResponseBean.okResponse(status);	
		}catch (Exception e) {
			return ResponseBean.koResponseBean(status, e.getMessage());
		}
	}

}
