package it.myalert.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import it.myalert.adapterConverter.CitizenAdapter;
import it.myalert.exeption.CitizenExeption;
import it.myalert.service.CitizenService;

@Service
@Transactional(rollbackOn = CitizenExeption.class)
public class CitizenServiceImpl  extends CitizenAdapter implements CitizenService{

}
