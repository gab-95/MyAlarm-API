package it.myalert.service;

import java.util.List;


import it.myalert.DTO.ManagerDTO;
import it.myalert.entity.Manager;
import it.myalert.exeption.ManagerExeption;
import it.myalert.adapterConverter.Converter;

public interface ManagerService extends Converter<ManagerDTO, Manager>{
	
	public List<Manager> getAll();
	public Manager getById(int id) throws ManagerExeption;

}
