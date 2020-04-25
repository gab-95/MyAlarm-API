package it.myalert.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.ManagerAdapter;
import it.myalert.entity.Manager;
import it.myalert.exeption.ManagerExeption;
import it.myalert.repository.ManagerRepository;
import it.myalert.service.ManagerService;

@Service
@Transactional
public class ManagerServiceImpl extends ManagerAdapter implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	
	@Override
	public List<Manager> getAll() {
		return this.managerRepository.findAll();
	}


	@Override
	public Manager getById(int id)  throws ManagerExeption {
		return managerRepository.findById(id).orElseThrow(()-> new ManagerExeption("ERROR: No user found"));
	}


}
